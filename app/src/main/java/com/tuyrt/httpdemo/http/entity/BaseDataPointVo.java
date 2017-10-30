package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/10/30.
 */

public class BaseDataPointVo {

    /**
     * sleeptime : 7200
     * sleepLooptime : 1800
     * sickLooptime : 1800
     * hungryLoopTime : 1800
     * healthDownTime : 2
     * hungryDownTime : 2
     */

    private int sleeptime;
    private int sleepLooptime;
    private int sickLooptime;
    private int hungryLoopTime;
    private int healthDownTime;
    private int hungryDownTime;

    public int getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }

    public int getSleepLooptime() {
        return sleepLooptime;
    }

    public void setSleepLooptime(int sleepLooptime) {
        this.sleepLooptime = sleepLooptime;
    }

    public int getSickLooptime() {
        return sickLooptime;
    }

    public void setSickLooptime(int sickLooptime) {
        this.sickLooptime = sickLooptime;
    }

    public int getHungryLoopTime() {
        return hungryLoopTime;
    }

    public void setHungryLoopTime(int hungryLoopTime) {
        this.hungryLoopTime = hungryLoopTime;
    }

    public int getHealthDownTime() {
        return healthDownTime;
    }

    public void setHealthDownTime(int healthDownTime) {
        this.healthDownTime = healthDownTime;
    }

    public int getHungryDownTime() {
        return hungryDownTime;
    }

    public void setHungryDownTime(int hungryDownTime) {
        this.hungryDownTime = hungryDownTime;
    }

    @Override
    public String toString() {
        return "BaseDataPointVo{" +
                "sleeptime=" + sleeptime +
                ", sleepLooptime=" + sleepLooptime +
                ", sickLooptime=" + sickLooptime +
                ", hungryLoopTime=" + hungryLoopTime +
                ", healthDownTime=" + healthDownTime +
                ", hungryDownTime=" + hungryDownTime +
                '}';
    }
}
