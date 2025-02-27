package com.xujie.executors.order.notify;

import com.aizuda.snailjob.client.job.core.annotation.JobExecutor;
import com.aizuda.snailjob.client.job.core.dto.JobArgs;
import com.aizuda.snailjob.client.model.ExecuteResult;
import org.springframework.stereotype.Component;

/**
 * @author Xujie
 * @since 2025/2/27 13:56
 * 订单回调兜底处理
 **/
@Component
@JobExecutor(name = "orderNotify")
public class OrderNotifyJob {

    public ExecuteResult jobExecute(JobArgs jobArgs) {
        return ExecuteResult.success("测试成功");
    }
}
