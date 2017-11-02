package com.tuyrt.httpdemo.mvp.view;

/**
 * Created by futao on 2017/11/2.
 */

/**
 * 描述：视图基类
 */
public interface IBaseView<T> {
    /**
     * 请求数据成功
     * @param t
     */
    void loadDateSuccess(T t);
    /**
     *    请求数据错误
     */
    void onError(int whichRequest ,Throwable e);
    /**
     * 请求前加载 progress
     */
    void onStartLoading(int whichRequest);

    /**
     * 请求结束之后隐藏progress
     */
    void onEndLoading(int whichRequest);
}
