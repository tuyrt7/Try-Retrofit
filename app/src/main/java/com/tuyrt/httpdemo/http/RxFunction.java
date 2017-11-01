package com.tuyrt.httpdemo.http;


import com.tuyrt.httpdemo.http.entity.BaseEntity;
import com.tuyrt.httpdemo.http.exception.ApiException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * 错误信息统一处理,异常会在RxObserver的onError中catch
 */
public class RxFunction<T> implements Function<BaseEntity<T>, T> {

    @Override
    public T apply(@NonNull BaseEntity<T> t) throws RuntimeException {
        int retCode = t.getCode();
        if (retCode != 200) {
            throw new ApiException(t.getCode(),t.getMessage());
        }
        return t.getData();
    }

 /*   public abstract Observable getObservable(ApiRestService retrofitService);

    public abstract Subscriber getSubscriber();*/

}
