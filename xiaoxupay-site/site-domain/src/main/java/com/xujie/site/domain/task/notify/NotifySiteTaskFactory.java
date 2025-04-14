package com.xujie.site.domain.task.notify;

import com.xujie.common.exception.CustomException;
import com.xujie.site.domain.entity.Site;
import com.xujie.site.domain.service.SiteDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class NotifySiteTaskFactory {
    @Resource
    private SiteDomainService siteDomainService;

    public Supplier<Site> buildSiteQueryTask(String siteAppid) {
        return () -> {
            Site siteByAppId = siteDomainService.getSiteByAppId(siteAppid);
            if (ObjectUtils.isEmpty(siteByAppId)) {
                throw new CustomException("站点不存在");
            }
            return siteByAppId;
        };
    }
}
