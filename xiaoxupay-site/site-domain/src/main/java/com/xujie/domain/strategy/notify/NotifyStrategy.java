package com.xujie.domain.strategy.notify;

import com.xujie.domain.entity.NotifyRequest;

public abstract class NotifyStrategy {
    protected abstract String notify(NotifyRequest request);
}
