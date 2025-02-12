package com.xujie.domain.task.notify;

import com.xujie.domain.service.SiteDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通知任务线程池
 */
@Component
public class NotifyTaskThreadPool {
    @Resource
    private SiteDomainService siteDomainService;
    private static final ThreadPoolExecutor threadPoolExecutor;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(10
                , 20
                , 60 * 10
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(1000)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public void notifySite(String openNo) {

    }
}
