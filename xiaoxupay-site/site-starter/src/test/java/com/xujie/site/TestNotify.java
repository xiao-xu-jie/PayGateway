package com.xujie.site;

import com.xujie.site.domain.task.notify.NotifyTaskThreadPool;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SiteApplication.class)
public class TestNotify {
    @Resource
    private NotifyTaskThreadPool notifyTaskThreadPool;

    @Test
    public void testNotify() {
        notifyTaskThreadPool.notifySite(String.valueOf(702473579236687875L), "ZX3KbRpny05LndAk");
    }
}
