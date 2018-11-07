package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface InputPersonView extends MvpView {
    void init();

    void showInfoMessage(String message);

}
