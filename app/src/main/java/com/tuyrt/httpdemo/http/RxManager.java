package com.tuyrt.httpdemo.http;

import com.tuyrt.httpdemo.http.api.ApiService;
import com.tuyrt.httpdemo.http.api.ExpandApiRestService;
import com.tuyrt.httpdemo.http.api.UploadFileService;
import com.tuyrt.httpdemo.http.config.HttpConfig;
import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.entity.QCloudResult;
import com.tuyrt.httpdemo.http.entity.RobotChildVo;
import com.tuyrt.httpdemo.http.interceptor.InterceptorUtil;
import com.tuyrt.httpdemo.http.progress.ProgressListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lytcom on 2017/5/7.
 */

public class RxManager {

    private ApiService mApiService;
    private UploadFileService mUploadFileService;
    private ExpandApiRestService mExpandApiRestService;
    private Map<String, CompositeDisposable> map;

    private volatile static RxManager ourInstance;

    private RxManager() {
        setupRestServices();
        map = new HashMap<>();
    }

    public static RxManager getInstance() {
        if (ourInstance == null) {
            synchronized (RxManager.class) {
                if (ourInstance == null) {
                    ourInstance = new RxManager();
                }
            }
        }
        return ourInstance;
    }

    private <T> T createRestQueue(Class<T> restClass,
                                  String endpoint,
                                  Collection<Interceptor> interceptors,
                                  boolean followRedirects) {
        OkHttpClient okHttpClient = RestServiceUtils.buildOkHttpClient(followRedirects, interceptors);

        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(restClass);
    }

    private void setupRestServices() {
        setupApiRestService();
        setupExpandApiRestService();
        setupOtherService();
    }

    public ApiService getApiService() {
        return mApiService;
    }

    private void setupApiRestService() {
        String endpoint = HttpConfig.BASE_URL;
        mApiService = createRestQueue(ApiService.class, endpoint, RestServiceUtils.getApiInterceptors(), false);
    }

    //for example
    private void setupExpandApiRestService() {
        String endpoint = "https://api.github.com";
        //mExpandApiRestService = createRestQueue(ExpandApiRestService.class, endpoint, null, false);
    }

    private void setupOtherService() {
        //other api
        //mUploadFileService = createRestQueue(UploadFileService.class, HttpConfig.BASE_URL, RestServiceUtils.getApiInterceptors(), false);
    }


    public void add(String key, Disposable disposable) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key, compositeDisposable);
        }
    }

    public void clear(String key) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }

    // *--------------------------------------------------------------------
    public Observable<RobotChildVo> getRobotChildInfo() {
        return mApiService.getRobotChildInfo()
                .map(new Function<BaseEntity<RobotChildVo>, RobotChildVo>() {
                    @Override
                    public RobotChildVo apply(@NonNull BaseEntity<RobotChildVo> robotChildVoBaseEntity) throws Exception {
                        return robotChildVoBaseEntity.getData();
                    }
                });
    }


    public Observable<BaseEntity<QCloudResult>> uploadFile(String filePath, ProgressListener listener) {
        Collection<Interceptor> apiInterceptors = RestServiceUtils.getApiInterceptors();
        apiInterceptors.add(InterceptorUtil.ProgressInterceptor(listener));
        mUploadFileService = createRestQueue(UploadFileService.class, HttpConfig.BASE_URL, apiInterceptors, false);
        MultipartBody.Part uploadFile = RestServiceUtils.buildUploadFilePart("uploadFile", filePath);
        if (uploadFile == null) {
            throw new RuntimeException("文件路径不存在!");
        }
        return mUploadFileService.uploadOneFile(uploadFile);
    }

  /*  public Observable<User> getUerByName(String name) {
        return mExpandApiRestService.getUserByName(name)
            .filter(new Predicate<User>() {
                @Override
                public boolean test(User user) throws Exception {
                    return user != null;
                }
            })
            .doOnNext(new Consumer<User>() {
                @Override
                public void accept(User user) throws Exception {
                    Timber.i("getUserByName: " + Thread.currentThread().getName());
                    dbManager.setUser(user);
                }
            });
    }

    public Observable<GitHub> getGitHub() {
        return mApiService.getGitHub().doOnNext(new Consumer<GitHub>() {
            @Override
            public void accept(@NonNull GitHub gitHub) throws Exception {
                Timber.i("getGitHub: " + gitHub.getEmailsUrl());
            }
        }).filter(new Predicate<GitHub>() {
            @Override
            public boolean test(@NonNull GitHub gitHub) throws Exception {
                return !TextUtils.isEmpty(gitHub.getEmailsUrl());
            }
        });
    }*/
}
