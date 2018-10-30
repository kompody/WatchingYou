package com.yurentsy.watchingyou.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.view.SettingView;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class SettingPresenter extends MvpPresenter<SettingView> {

    private Scheduler scheduler;
    private Router router;

    public SettingPresenter(Scheduler scheduler, Router router) {
        this.scheduler = scheduler;
        this.router = router;
    }

    public void onBackPressed() {
        router.exit();
    }
}
