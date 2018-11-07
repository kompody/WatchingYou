package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void init();

    void updateList();

    void showInfoStatus(int online, int offline);

    void showInfoMessage(String message);

}
