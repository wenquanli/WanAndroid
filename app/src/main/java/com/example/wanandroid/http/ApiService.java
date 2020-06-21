package com.example.wanandroid.http;

import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CoinBean;
import com.example.wanandroid.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    String BASE_URL = "https://www.wanandroid.com/";

    //网络请求时长
    int HTTP_TIME =0;

    /***
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<UserBean.DataBean>> login(@Field("username") String username, @Field("password") String password);

    /***
     * 登出
     * @return
     */
    @GET("user/logout/json")
    Observable<BaseResponse<BaseBean.DataBean>> loginOut();
    /***
     * 积分信息
     * @return
     */
    @GET("lg/coin/userinfo/json")
    Observable<BaseResponse<CoinBean.DataBean>> loadCoinInfo();
}
