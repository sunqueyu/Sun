package com.example.administrator.loginmvp.presenter;

import com.example.administrator.loginmvp.bean.UserBean;
import com.example.administrator.loginmvp.biz.UserBiz;
import com.example.administrator.loginmvp.biz.UserBizImpl;
import com.example.administrator.loginmvp.listener.OnLoginListener;
import com.example.administrator.loginmvp.view.LoginView;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/5/8 19:04
 */

public class LoginPresenterImpl implements LoginPresenter,OnLoginListener {
    private LoginView loginView;
    private UserBiz userBiz;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.userBiz=new UserBizImpl(this);
    }

    @Override
    public void login() {
        UserBean login = new UserBean();
        login.setUsername(loginView.getUsername());
        login.setPassword(loginView.getPasswored());
        userBiz.login(login);
    }

    @Override
    public void clear() {
        loginView.clearPassword();
        loginView.clearUsername();
    }

    @Override
    public void listenerStatus(boolean status) {
        String msg;
        if(status){
            msg="login successed";
        }else{
            msg="login failed";
        }
        loginView.showMsg(msg);
    }
}
