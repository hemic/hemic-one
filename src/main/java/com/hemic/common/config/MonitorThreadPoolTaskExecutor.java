package com.hemic.common.config;

import com.hemic.one.utils.ContextUtil;
import java.io.Serial;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

/**
 * @Author jor
 * @create 2021/10/29 15:22
 */
public class MonitorThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private final Logger log = LoggerFactory.getLogger(MonitorThreadPoolTaskExecutor.class);

    @Serial
    private static final long serialVersionUID = -3922275641669380906L;

    private int threshold = 80;

    private AtomicInteger rejectCount = new AtomicInteger(0);


    public MonitorThreadPoolTaskExecutor() {
        super();
    }

    public MonitorThreadPoolTaskExecutor(int threshold) {
        super();
        this.threshold = threshold;
    }


    @Override
    public void execute(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();
        try {
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            executor.execute(task);
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }
    }

    @Override
    public void execute(Runnable task, long addTimeout) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();
        try {
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            this.execute(task);
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }


    }

    @Override
    public Future<?> submit(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            ListenableFutureTask<Object> future = new ListenableFutureTask(task, (Object) null);
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            executor.execute(future);
            return future;
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            ListenableFutureTask<T> future = new ListenableFutureTask(task);
            log.debug("traceId= {}, threadPool ={}; add a new task.", ContextUtil.getTraceId(), this.getThreadNamePrefix());
            executor.execute(future);
            return future;
        } catch (RejectedExecutionException var4) {
            rejectCount.incrementAndGet();
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        } finally {
            monitor();
        }
    }

    private final static int HUNDRED = 100;

    private void monitor() {
        if ((this.getActiveCount() / this.getMaxPoolSize()) * HUNDRED >= threshold) {
            log.warn("this ThreadPool: {}, activeCount is {};maxPoolSize is{}; threshold is {}; rejectCount is {}", this.getThreadNamePrefix(), this.getActiveCount(), this.getMaxPoolSize(),
                this.threshold, this.rejectCount.intValue());
        } else {
            log.debug("this ThreadPool: {}, activeCount is {};maxPoolSize is{}; threshold is {}; rejectCount is {}", this.getThreadNamePrefix(), this.getActiveCount(), this.getMaxPoolSize(),
                this.threshold, this.rejectCount.intValue());
        }
    }

}
