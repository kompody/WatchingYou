package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface PersonView extends MvpView {
    void init();

    void setName(String name);

    void setUrlPhoto(String urlPhoto);
}
