package com.tuyrt.httpdemo.http.api;


import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.QCloudResult;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by futao on 2017/11/15.
 */

public interface UploadFileService {

    @Multipart
    @POST(HttpConfig.UPLOAD_ONE_FILE)
    Observable<BaseEntity<QCloudResult>> uploadOneFile(@Part MultipartBody.Part file);
}
