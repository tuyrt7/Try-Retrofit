package com.tuyrt.httpdemo.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.interceptor.InterceptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

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


}
