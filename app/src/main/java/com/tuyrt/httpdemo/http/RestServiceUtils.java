package com.tuyrt.httpdemo.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.interceptor.InterceptorUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by lytcom on 2017/5/7.
 */

public class RestServiceUtils {

    public static OkHttpClient buildOkHttpClient(boolean followRedirects, Collection<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(HttpConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .followRedirects(followRedirects);

        if (HttpConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        if (interceptors != null && !interceptors.isEmpty()) {
            builder.interceptors().addAll(interceptors);
        }
        return builder.build();
    }

    public static Collection<Interceptor> getApiInterceptors() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(InterceptorUtil.LogInterceptor());
        interceptors.add(InterceptorUtil.HeaderInterceptor());
        return interceptors;
    }

    public static MultipartBody.Part buildUploadFilePart(String key, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }
}
