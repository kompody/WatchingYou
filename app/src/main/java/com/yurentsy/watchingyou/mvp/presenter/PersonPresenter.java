package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.view.PersonView;
import com.yurentsy.watchingyou.mvp.view.SettingView;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {

    private Scheduler scheduler;
    private Router router;

    @SuppressLint("CheckResult")
    public PersonPresenter(Scheduler scheduler, Router router,String name,String urlPhoto) {
        this.scheduler = scheduler;
        this.router = router;
        getViewState().setName(name);
        getViewState().setUrlPhoto(urlPhoto);
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
