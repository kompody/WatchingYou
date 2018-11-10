package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

public interface PersonEditView extends MvpView {
    void init();

    void showInfoMessage(String message);

    void setCard(Person person);
}
