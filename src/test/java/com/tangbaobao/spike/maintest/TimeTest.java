package com.tangbaobao.spike.maintest;

import java.time.Duration;

/**
 * @author tangxuejun
 * @version 2018-11-25 13:04
 */
public class TimeTest {
    Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public static void main(String[] args) {
        TimeTest timeTest = new TimeTest();
        timeTest.setDuration(Duration.ofSeconds(10000));
    }
}
