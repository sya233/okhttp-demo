package com.upai.httpdemo.model;

import android.util.Log;

import com.upai.httpdemo.HttpUtil;
import com.upai.httpdemo.controller.MainController;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainModel {

    private MainController mainController;
    private String responseData;
    private String errorData;

    private final static String TAG = "MainModel";

    public MainModel(MainController controller) {
        mainController = controller;
    }

    public void getDataFromServer(String address) {
        HttpUtil.sendOkHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                errorData=e.toString();
                Log.d(TAG,"错误信息是: "+e.toString());
                mainController.doWhenResultIsError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                Log.d(TAG, "返回的数据是: " + responseData);
                mainController.doWhenResultIsReady();
            }
        });
    }

    public String getErrorData() {
        return errorData;
    }

    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
