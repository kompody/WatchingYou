package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

public interface PersonView extends MvpView {
    void init();

    void setCard(Person person);

    void showInfoMessage(String message);
}
