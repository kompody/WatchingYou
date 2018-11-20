package com.yurentsy.watchingyou.mvp.view;

import android.net.Uri;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PersonEditView extends MvpView {
    void init();

    void showInfoMessage(String message);

    void setCard(Person person);

    void showDialog(String title);

    void showImportCamera(Uri uri);
}
