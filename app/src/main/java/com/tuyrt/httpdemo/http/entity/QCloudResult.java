package com.tuyrt.httpdemo.http.entity;

/**
 * Created by futao on 2017/11/15.
 */

public class QCloudResult {
    /**
     * access_url : http://xfcpb01-10052768.file.myqcloud.com/avic/robot/%E5%8F%8B%E5%96%84%E4%B9%8B%E8%87%82.jpg
     * resource_path : /avic/robot/%E5%8F%8B%E5%96%84%E4%B9%8B%E8%87%82.jpg
     * source_url : http://xfcpb01-10052768.cos.myqcloud.com/avic/robot/%E5%8F%8B%E5%96%84%E4%B9%8B%E8%87%82.jpg
     * url : http://web.file.myqcloud.com/files/v1/avic/robot/%E5%8F%8B%E5%96%84%E4%B9%8B%E8%87%82.jpg
     */

    private String access_url;
    private String resource_path;
    private String source_url;
    private String url;

    public String getAccess_url() {
        return access_url;
    }

    public void setAccess_url(String access_url) {
        this.access_url = access_url;
    }

    public String getResource_path() {
        return resource_path;
    }

    public void setResource_path(String resource_path) {
        this.resource_path = resource_path;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "QCloudResult{" +
                "access_url='" + access_url + '\'' +
                ", resource_path='" + resource_path + '\'' +
                ", source_url='" + source_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
