package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.MainView;
import com.yurentsy.watchingyou.mvp.view.SettingView;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class SettingPresenter extends MvpPresenter<SettingView> {

    private Scheduler scheduler;
    private Router router;

    @SuppressLint("CheckResult")
    public SettingPresenter(Scheduler scheduler, Router router) {
        this.scheduler = scheduler;
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onBackPressed() {
        router.exit();
    }

}
