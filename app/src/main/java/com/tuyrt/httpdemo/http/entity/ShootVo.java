package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/11/15.
 */

public class ShootVo {
    /**
     * id : 8aeca3bd-b8a2-4661-bae5-b64c624dec36
     * shotType : 图片
     * sourceUrl : http://xfcpb01-10052768.cos.myqcloud.com/avic/robot/abc.jpg
     * shotName : abc
     * uploadTime : 2017-10-20 15:14:40
     * mac : 8888888888
     */

    private String id;
    private String shotType;
    private String sourceUrl;
    private String shotName;
    private String uploadTime;
    private String mac;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShotType() {
        return shotType;
    }

    public void setShotType(String shotType) {
        this.shotType = shotType;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getShotName() {
        return shotName;
    }

    public void setShotName(String shotName) {
        this.shotName = shotName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "ShootVo{" +
                "id='" + id + '\'' +
                ", shotType='" + shotType + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", shotName='" + shotName + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
