package com.xujie.domain.task.notify;

import com.xujie.common.enums.NotifyStatus;
import com.xujie.common.enums.NotifyType;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.entity.NotifyRequest;
import com.xujie.domain.entity.NotifySiteLog;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.NotifyLogDomainService;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.domain.strategy.notify.NotifyContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * 通知任务线程池
 */
@Slf4j
@Component
public class NotifyTaskThreadPool {
    @Resource
    private SiteDomainService siteDomainService;
    @Resource
    private NotifySiteTaskFactory taskFactory;
    @Resource
    private NotifyLogDomainService notifyLogDomainService;
    @Resource
    private NotifyContext notifyContext;
    private final ThreadPoolExecutor threadPoolExecutor;

    {
        threadPoolExecutor = new ThreadPoolExecutor(10
                , 20
                , 60 * 10
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(1000)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    /**
     * 通过异步任务方式执行通知任务
     * 1、查询站点信息
     * 2、构建通知请求
     * 3、发送通知请求
     * 4、日志记录
     * 5、容错处理
     *
     * @param openNo    订单openNo
     * @param siteAppid 站点appid
     */
    public void notifySite(String openNo, String siteAppid) {
        log.info("任务池开始执行站点通知任务：{}，{}", openNo, siteAppid);

        Supplier<Site> siteSupplier = taskFactory.buildSiteQueryTask(siteAppid);
        // 查询站点信息
        CompletableFuture<Site> siteCompletableFuture = CompletableFuture.supplyAsync(siteSupplier, this.threadPoolExecutor);
        // 构建请求任务
        AtomicReference<String> url = new AtomicReference<>();
        AtomicReference<NotifyType> type = new AtomicReference<>();
        CompletableFuture<String> requestFuture = startNotifySite(openNo, siteCompletableFuture, url, type);
        afterNotifySite(openNo, siteAppid, requestFuture, url, type);

    }

    private CompletableFuture<String> startNotifySite(String openNo, CompletableFuture<Site> siteCompletableFuture, AtomicReference<String> url, AtomicReference<NotifyType> type) {
        CompletableFuture<String> requestFuture = siteCompletableFuture.thenComposeAsync(site -> {
            // 构建请求
            String notifyUrl = site.getNotifyUrl();
            url.set(notifyUrl);

            String siteSecret = site.getSiteSecret();
            NotifyType notifyType = site.getNotifyType();
            type.set(notifyType);
            if (ObjectUtils.allNull(notifyUrl, siteSecret, notifyType)) {
                throw new CustomException("站点信息不完整！");
            }
            NotifyRequest notifyRequest = NotifyRequest.builder()
                    .notifyUrl(notifyUrl)
                    .notifyType(notifyType)
                    .siteSecret(siteSecret)
                    .openNo(openNo)
                    .build();

            return CompletableFuture
                    .supplyAsync(() -> notifyContext.notifyByType(notifyType, notifyRequest)
                            , threadPoolExecutor);
        });
        return requestFuture;
    }

    private void afterNotifySite(String openNo, String siteAppid, CompletableFuture<String> requestFuture, AtomicReference<String> url, AtomicReference<NotifyType> type) {
        requestFuture.thenAcceptAsync((res) -> {
            NotifySiteLog.NotifySiteLogBuilder notifySiteLogBuilder = NotifySiteLog.builder()
                    .notifyStatus(NotifyStatus.SUCCESS)
                    .siteAppid(siteAppid)
                    .resBody(res)
                    .notifuUrl(url.get())
                    .currentCount(1)
                    .openNo(openNo)
                    .notifyType(type.get());

            if (StringUtils.compare("error", res) == 0) {
                // TODO错误处理
                log.error("通知错误处理......");
                NotifySiteLog siteLog = notifySiteLogBuilder.notifyStatus(NotifyStatus.FAILED).build();
                notifyLogDomainService.addOneLog(siteLog);
            } else {
                // 插入数据库
                log.info("通知成功：{}", res);
                NotifySiteLog siteLog = notifySiteLogBuilder.notifyStatus(NotifyStatus.SUCCESS).build();
                notifyLogDomainService.addOneLog(siteLog);
            }
        }, threadPoolExecutor);
    }
}
