package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/10/27.
 */

public class TokenVo {

    /**
     * accesstoken : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2Y2Y0NTNmODI3MGQ0NzYzYWI1ODVmZGRjZmYxMzQyZCIsImlhdCI6MTUwOTA5MjgyNiwic3ViIjoiMTIzNDU2NzhhYiIsImV4cCI6MTUwOTY5NzYyNn0.W5TRTxZxBwHS0BOffQagTsAbgt-_HBehVsmQfaPXgdw
     * expaire_at : 1509697626131
     */

    private String accesstoken;
    private long expaire_at;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public long getExpaire_at() {
        return expaire_at;
    }

    public void setExpaire_at(long expaire_at) {
        this.expaire_at = expaire_at;
    }
}
