package com.example.administrator.loginmvp.view;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/5/8 19:00
 */

public interface LoginView {
    public String getUsername();
    public String getPasswored();
    public void clearUsername();
    public void clearPassword();
    public void showMsg(String msg);
}
