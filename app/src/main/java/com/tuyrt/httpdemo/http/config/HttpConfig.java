package com.tuyrt.httpdemo.http.config;

import okhttp3.MediaType;

/**
 * Created by futao on 2017/10/27.
 */

public class HttpConfig {

    public static final String BASE_URL = "http://14.23.109.228:9091/robot/";
    public static boolean DEBUG = true;
    public static final int NETWORK_TIMEOUT = 5;



    public static final String GET_ROBOT_INFO = BASE_URL + "/body/getRobotChild";  //机器人信息
    public static final String UPLOAD_STH_URL = BASE_URL + "/qcloud/uploadOneFile"; // 上传文件
    public static final String ALL_COMMODITY_URL = BASE_URL + "/commodity/all/list";// 商品全部列表
    public static final String ALL_COMMODITY_CAN_BUY = BASE_URL + "/commodity/list";// 已经完全解锁的全部商品列表
    public static final String GET_COMMODITY_INFO = BASE_URL + "/robot/getTaskRecords"; // 商品详情
    public static final String BUY_COMMODITY_URL = BASE_URL + "/commodity/buy";   //购买商品
    public static final String GET_STORAGE_URL = BASE_URL + "/commodityStorage/list"; // 储藏的所有商品列表
    public static final String USE_COMMODITY_URL = BASE_URL + "/commodity/consume"; //使用商品 吃

    public static final String GET_TOKEN = BASE_URL + "/body/getAccessToken"; // 得到token
    public static final String GET_BASE_DATA_POINT = BASE_URL + "/body/baseDataPoint"; // 得到 基础设置
    public static final String Robot_Medal = BASE_URL + "/body/getRobotMedal"; // 获取勋章
    public static final String MESSAGE_LIST = BASE_URL + "/body/message/list"; // 消息列表
    public static final String UPGRADE_PACK = BASE_URL + "/body/upgradePack/newest"; // 最新升级包


    public static final String GET_ALL_CONVENTION = BASE_URL + "/convention/schedule/allConvention"; //获取所有的惯例表
    public static final String GET_CONVENTION_DETAIL = BASE_URL + "/convention/schedule/getConvention"; //获取惯例表详情
    public static final String DELETE_CONVENTION = BASE_URL + "/convention/schedule/deleteConvention?id=%s"; //删除惯例表
    public static final String GET_ALL_SCHEDULE = BASE_URL + "/convention/schedule/allSchedule"; //获取所有的日程提醒
    public static final String DELETE_SCHEDULE = BASE_URL + "/convention/schedule/deleteSchedule?id=%s"; //删除日程提醒

    public static final String ALL_SHOOT = BASE_URL + "/shoot/all"; //获取全部的拍摄
    public static final String DELETE_SHOOT = BASE_URL + "/shoot/delete?id=%s"; //删除拍摄
    public static final String SHOOT_INFO = BASE_URL + "/shoot/getinfo"; //拍摄详情
    public static final String SHOOT_INPUT = BASE_URL + "/shoot/input"; //拍摄录入
    public static final String SHOOT_LIST = BASE_URL + "/shoot/list"; //拍摄记录


    public static String TASK_TYPE = "/task/taskType";  //获取任务类型
    public static final String TASK_ALL = BASE_URL + "/task/allTasks"; //所有的任务列表
    public static final String Task_type = BASE_URL + "/task/taskType"; //任务类型, 现在基本上的是乐音类型
    public static final String TASK_REPORT = BASE_URL + "/task/click"; //上报完成的任务
    public static final String TASK_EVERYDAY = BASE_URL + "/task/complete/everyday"; //上报每日任务
    public static final String TASK_REPORT_OEDINARY = BASE_URL + "/task/complete/ordinary"; //上报完成普通任务

    public static final String TASK_SAVA = BASE_URL + "/task/getCollects"; //获取收藏的音视频和游戏
    public static final String MUSIC_All = BASE_URL + "/task/allTasks"; //获取音频
    public static final String REPORT_CLICK = BASE_URL + "/task/click"; //上报点击事件
    public static final String ADD_COLLECT = BASE_URL + "/task/addCollect"; //收藏音乐
    public static final String DELEDT_COLLECT = BASE_URL + "/task/delCollect?taskId=%s"; //删除收藏音乐
    public static final String GROWVALUE =BASE_URL +"/body/growthValue/statistics";//成长树里面的成长值


    public static final MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
}
