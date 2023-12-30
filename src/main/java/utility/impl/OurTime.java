package main.java.utility.impl;

import main.java.utility.abstracts.IOurTime;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OurTime implements IOurTime {
    private long elapsedTime;
    private final ScheduledExecutorService executorService;
    private final long timeRate;

    public OurTime(long timeRate) {
        this.timeRate = timeRate;
        this.elapsedTime = 0;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void start() {
        executorService.scheduleAtFixedRate(() -> elapsedTime += 1, 5, timeRate, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        executorService.shutdown();
    }

    @Override
    public long getTime() {
        return elapsedTime;
    }
}
