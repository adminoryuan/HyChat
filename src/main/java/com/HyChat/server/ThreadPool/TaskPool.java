package com.HyChat.server.ThreadPool;

import com.HyChat.server.untity.LoggerUntity;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 线程池
 */
public class TaskPool {
    static class ThenThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            LoggerUntity.LogInfo("创建一个线程");

            return new Thread(r);
        }
    }

    private static ThreadPoolExecutor service ;

    private static final int MAXThread=1024;
    static {
        //使用ArrayBlockingQueue 防止线程快速增长导致内存溢出
       service= new ThreadPoolExecutor(
                20, //即使没有也会执行
                1000,
                20,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(MAXThread),
                new ThenThreadFactory());
    }

    /**
     * 执行任务
     * @param runnable
     */
    public static void Sumit(Runnable runnable){
       service.execute(runnable);
    }
}
