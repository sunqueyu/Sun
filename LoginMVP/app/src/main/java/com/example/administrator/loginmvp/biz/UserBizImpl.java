package com.example.administrator.loginmvp.biz;

import com.example.administrator.loginmvp.bean.UserBean;
import com.example.administrator.loginmvp.listener.OnLoginListener;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/5/8 18:38
 */

public class UserBizImpl implements UserBiz{

    private OnLoginListener loginListener;

    public UserBizImpl(OnLoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void login(UserBean login) {
        boolean status = false;
        String username;
        String password;
        username=login.getUsername();
        password=login.getPassword();
        if(username!=null&&"123".equals(username)){
            if(password!=null&&"123".equals(password)){
                status=true;
                loginListener.listenerStatus(status);
            }
        }
    }
}
