package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/11/1.
 */

public class MedalVo {

    private String medalDesc;
    private String medal;

    public String getMedalDesc() {
        return medalDesc;
    }

    public void setMedalDesc(String medalDesc) {
        this.medalDesc = medalDesc;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    @Override
    public String toString() {
        return "MedalVo{" +
                "medalDesc='" + medalDesc + '\'' +
                ", medal='" + medal + '\'' +
                '}';
    }
}
