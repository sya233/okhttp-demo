package com.upai.httpdemo.view;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.upai.httpdemo.HttpUtil;
import com.upai.httpdemo.R;
import com.upai.httpdemo.controller.MainController;
import com.upai.httpdemo.model.MainModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化
        init();

        mainController.getDataFromServer("http://www.baidu.com/");
    }

    private MainModel mainModel;
    private MainController mainController;
    private TextView tvData;
    private ProgressBar pbMain;

    private void init() {
        // 初始化Model和Controller
        mainController = new MainController();
        mainModel = new MainModel(mainController);
        mainController.initView(this);
        mainController.initModel(mainModel);
        // 初始化控件id
        tvData = findViewById(R.id.tv_data);
        pbMain = findViewById(R.id.pb_main);
        // 设置TextView能够滚动
        tvData.setMovementMethod(new ScrollingMovementMethod());
    }

    public void showProgressBar() {
        pbMain.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pbMain.setVisibility(View.GONE);
            }
        });
    }

    public void showData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvData.setText(mainModel.getResponseData());
            }
        });
    }

    public void showErrorData() {
        tvData.setText(mainModel.getErrorData());
    }
}
