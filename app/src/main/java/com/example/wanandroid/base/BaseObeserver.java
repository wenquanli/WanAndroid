package com.example.wanandroid.base;

import android.content.Intent;

import com.example.wanandroid.MyApplication;
import com.example.wanandroid.activity.LoginActivity;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.constant.Constant;
import com.example.wanandroid.util.CommonUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObeserver<T> implements Observer<BaseResponse<T>> {
    private static final String tag = BaseObeserver.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if(tBaseResponse.isSuccess()){
            try {
                onSuccess(tBaseResponse.data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            if(tBaseResponse.errorCode == Constant.LOGIN_EXPIRED){
                startLoginActivity();
            }
            onErrorCode(tBaseResponse.errorCode);
//            onError(new Throwable(tBaseResponse.getMsg()));
            onError(new BaseException(tBaseResponse.errorCode,tBaseResponse.errorMsg));
        }

    }

    @Override
    public void onError(Throwable e) {
        BaseException exception;
        if(e != null) {
            if(e instanceof BaseException) {
                exception = (BaseException)e;
            }else {
                if (e instanceof HttpException) {
                    //HTTP错误
                    exception = new BaseException(BaseException.BAD_NETWORK_MSG, e);
                } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
                    //连接错误
                    exception = new BaseException(BaseException.CONNECT_ERROR_MSG, e);
                } else if (e instanceof InterruptedIOException) {
                    //连接超时
                    exception = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e);
                } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
                    //解析错误
                    exception = new BaseException(BaseException.PARSE_ERROR_MSG, e);
                } else {
                    exception = new BaseException(BaseException.OTHER_MSG, e);
                }
            }
        }else {
            exception = new BaseException(BaseException.OTHER_MSG);
        }
        try {
            CommonUtil.showToast(exception.getErrorMsg());
            onFailure(exception.getErrorMsg(), false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }
    /**
     * 返回成功
     * @param  o 参数
     */
    protected abstract void onSuccess(T o) throws Exception;
    protected void onErrorCode(int error){};
    /**
     * 返回失败
     * @param error
     * @param isNetworkError 是否是网络错误
     */
    protected abstract void onFailure(String error, boolean isNetworkError) throws Exception;
    public static void startLoginActivity() {
        Intent intent=new Intent(MyApplication.getContext(), LoginActivity.class);
        MyApplication.getContext().startActivity(intent);
    }
}
