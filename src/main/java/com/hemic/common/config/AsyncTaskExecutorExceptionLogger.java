package com.hemic.common.config;

import com.hemic.one.utils.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import tech.jhipster.async.ExceptionHandlingAsyncTaskExecutor;

/**
 * @Author jor
 * @create 2021/10/29 15:03
 * ExceptionHandling for AsyncTaskExecutor
 * 异步线程池创建报错处理
 */
public class AsyncTaskExecutorExceptionLogger extends ExceptionHandlingAsyncTaskExecutor {

    private final Logger log = LoggerFactory.getLogger(AsyncTaskExecutorExceptionLogger.class);

    public AsyncTaskExecutorExceptionLogger(AsyncTaskExecutor executor) {
        super(executor);
        this.executor = executor;
    }

    private final AsyncTaskExecutor executor;

    @Override
    protected void handle(Exception e) {
        this.log.error("traceId={},Caught async exception", ContextUtil.getTraceId(), e);
    }


}
