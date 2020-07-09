package com.upai.httpdemo.controller;

import com.upai.httpdemo.model.MainModel;
import com.upai.httpdemo.view.MainView;

public class MainController {

    private MainView mainView;
    private MainModel mainModel;

    public void initView(MainView view) {
        mainView = view;
    }

    public void initModel(MainModel model) {
        mainModel = model;
    }

    public void getDataFromServer(String address){
        mainModel.getDataFromServer(address);
        mainView.showProgressBar();
    }

    public void doWhenResultIsReady(){
        mainView.hideProgressBar();
        mainView.showData();
    }

    public void doWhenResultIsError(Exception e){
        mainView.hideProgressBar();
        mainView.showErrorData();
    }

}
