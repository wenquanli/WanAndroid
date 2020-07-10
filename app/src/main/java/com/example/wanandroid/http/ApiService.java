package com.example.wanandroid.http;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CoinBean;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.bean.NavgationBean;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.bean.WXArticleBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_URL = "https://www.wanandroid.com/";

    //网络请求时长
    int HTTP_TIME =0;
    /**
     *我的相关接口
     */
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
    /**
     *首页相关接口
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean.DataBean>>> loadBanner();
    @GET("article/list/{number}/json")
    Observable<BaseResponse<MainArticleBean.DataBean>> loadArticle(@Path("number") int number);
    @GET("article/list/0/json")
    Observable<BaseResponse<MainArticleBean.DataBean>> refreshMainArticle();
    /**
     *公众号相关接口
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<OffAccountBean.DataBean>>> loadOffAccount();
    @GET("wxarticle/list/{chapter}/{page}/json")
    Observable<BaseResponse<WXArticleBean.DataBean>> loadWXArticle(@Path("chapter") int chapter, @Path("page") int page);
    @GET("wxarticle/list/{chapter}/0/json")
    Observable<BaseResponse<WXArticleBean.DataBean>> refreshWXArticle(@Path("chapter") int chapter);
    /**
     *体系相关接口
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KonwledgeHireBean.DataBean>>> loadKonwledgeHeir();
    /**
     *导航相关接口
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavgationBean.DataBean>>> loadNavgation();
}
