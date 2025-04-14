package com.xujie.site.domain.strategy.notify;

import com.xujie.site.domain.entity.NotifyRequest;

public abstract class NotifyStrategy {
    protected abstract String notify(NotifyRequest request);
}
