package com.github.berrouz.common;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Thread Executor is an executor class for all threads
 */
public class ThreadExecutor implements Executor{

    private final Executor executor = Executors.newCachedThreadPool();

    @Override
    public void execute(final Runnable command) {
        executor.execute(command);
    }
}
