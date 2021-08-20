package com.example.common.thread;

import ohos.app.dispatcher.TaskDispatcher;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Run {
    static {
        eventRunner = EventRunner.create(true);
    }

    private static final EventRunner eventRunner;

    public static void runOnUIThread(Runnable task) {
        EventRunner mainEventRunner = EventRunner.getMainEventRunner();
        EventHandler eventHandler = new EventHandler(mainEventRunner);
        eventHandler.postTask(task);
    }

    public static void runAsync(Runnable task) {
        EventHandler handler = new EventHandler(eventRunner);
        handler.postTask(task);
    }
}
