package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void init();

    void updateList();

    void showInfoStatus(int online, int offline);

    void showInfoMessage(String message);

}
