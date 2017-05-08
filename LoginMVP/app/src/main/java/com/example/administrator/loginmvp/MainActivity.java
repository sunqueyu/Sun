package com.example.administrator.loginmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.loginmvp.presenter.LoginPresenter;
import com.example.administrator.loginmvp.presenter.LoginPresenterImpl;
import com.example.administrator.loginmvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {
    private EditText username, password;
    private Button login, clear;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        loginPresenter = new LoginPresenterImpl(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        //鐧诲綍
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.clear();
            }
        });
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPasswored() {
        return password.getText().toString();
    }


    @Override
    public void clearUsername() {
        username.setText("");
    }

    //娑堥櫎瀵嗙爜
    @Override
    public void clearPassword() {
        password.setText("");
    }

    //鐧诲綍鎴愬姛涓庡惁
    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
