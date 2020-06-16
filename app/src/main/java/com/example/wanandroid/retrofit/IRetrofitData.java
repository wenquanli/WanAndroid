package com.example.wanandroid.retrofit;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.bean.WXArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetrofitData {
    /**
     *首页相关接口
     */
    @GET("banner/json")
    Observable<BannerBean> loadBanner();
    @GET("article/list/{number}/json")
    Observable<MainArticleBean> loadArticle(@Path("number") int number);
    @GET("article/list/0/json")
    Observable<MainArticleBean> refreshMainArticle();
    /**
     *公众号相关接口
     */
    @GET("wxarticle/chapters/json")
    Observable<OffAccountBean> loadOffAccount();
    @GET("wxarticle/list/{chapter}/{page}/json")
    Observable<WXArticleBean> loadWXArticle(@Path("chapter") int chapter, @Path("page") int page);
    @GET("wxarticle/list/{chapter}/0/json")
    Observable<WXArticleBean> refreshWXArticle(@Path("chapter") int chapter);

}
