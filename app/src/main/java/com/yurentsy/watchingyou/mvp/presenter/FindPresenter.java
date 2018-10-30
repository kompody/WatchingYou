package com.yurentsy.watchingyou.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.view.FindView;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class FindPresenter extends MvpPresenter<FindView> {

    private Scheduler scheduler;
    private Router router;

    public FindPresenter(Scheduler scheduler, Router router, int anInt) {
        this.scheduler = scheduler;
        this.router = router;
        getViewState().setNumber(anInt);
    }

    public void onBackPressed() {
        router.exit();
    }
}
