package com.tuyrt.httpdemo.http;


import com.squareup.haha.guava.base.Function;
import com.tuyrt.httpdemo.http.Api.ApiRestService;
import com.tuyrt.httpdemo.http.entity.ResponseResult;
import com.tuyrt.httpdemo.http.exception.ResultErrorException;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;

/**
 * 所有基本数据请求实体类基类，必须继承自该类
 * Created by：hcs on 2016/10/19 10:44
 * e_mail：aaron1539@163.com
 */
public abstract class HttpResultFunc<T> implements Function<ResponseResult<T>, T> {

    @Override
    public T apply(ResponseResult<T> tResponseResult) {
        if (tResponseResult.getCode() != 200 && tResponseResult.getCode() != 0) {
            throw new ResultErrorException(tResponseResult.getMessage());
        }
        return tResponseResult.getData();
    }

    public abstract Observable getObservable(ApiRestService retrofitService);

    public abstract Subscriber getSubscriber();

}

