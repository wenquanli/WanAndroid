package com.example.wanandroid.contract;

import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.UserBean;

public class LoginContract {
    public interface ILoginView{
        /***
         * 登录成功
         */
       public void loginSuccess();

        /***
         * 登录失败
         */
        public void loginFail();


        /***
         * 获取用户名
         */
        public String  getUser();

        /***
         * 获取密码
         * @return
         */
        public String  getPassword();
    }
    public interface ILoginModel {
        public void handleLogin(String username, String  password, OnLoadDatasListener<UserBean.DataBean> onLoadDatasListener);
    }
    public interface ILoginPresenter {
        public void handleLogin();
    }
}
