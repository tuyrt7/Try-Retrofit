package com.tuyrt.httpdemo.http.interceptor;

import android.util.Log;

import com.tuyrt.httpdemo.App;
import com.tuyrt.httpdemo.http.RxManager;
import com.tuyrt.httpdemo.http.entity.TokenVo;
import com.tuyrt.httpdemo.util.SharedPrefs;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by futao on 2017/10/27.
 */

public class InterceptorUtil {
    public static String TAG = "----";

    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_APPLICATION_JSON = "application/json";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    private static final Charset UTF8 = Charset.forName("UTF-8");

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }


    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            /**
             * 根据Response，判断Token是否失效
             *
             * @param response
             * @return
             */
            private boolean isTokenExpired(Response response) {
                if (response.code() == 401) {
                    return true;
                }
                return false;
            }

            /**
             * 同步请求方式，获取最新的Token
             *
             * @return
             */
            private String getNewToken() throws IOException {
                //取出本地的refreshToken
                String pk = "6cf453f8270d4763ab585fddcff1342d";
                String mac = "8888888888";

                TokenVo tokenVo = RxManager.getInstance().getApiService()
                        .getToken(pk, mac).execute().body().getData();
                String newToken = tokenVo.getAccesstoken();
                SharedPrefs.putString(App.getContext(), "token", newToken);
                return newToken;
            }

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                boolean isGetToken = request.url().toString().contains("getAccessToken");
                Request.Builder builder = request.newBuilder();
                builder.header("Accept", "application/json");
                if (!isGetToken) {
                    String token = SharedPrefs.getString(App.getContext(), "token", "");
                    Log.i(TAG, "isGetToken: -----------0---------:" + token);
                    builder.header("Authorization", "bearer " + token);
                }
                Response response = chain.proceed(builder.build());

                if (!isGetToken && isTokenExpired(response)) {//根据和服务端的约定判断token过期
                    //同步请求方式，获取最新的Token
                    String token = getNewToken();
                    //使用新的Token，创建新的请求
                    Request newRequest = chain.request()
                            .newBuilder()
                            .header("Authorization", "bearer " + token)
                            .build();
                    //重新请求
                    return chain.proceed(newRequest);
                }
                return response;
            }
        };

    }
}
