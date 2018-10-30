package com.yurentsy.watchingyou.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.view.UpdateView;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class UpdatePresenter extends MvpPresenter<UpdateView> {

    private Scheduler scheduler;
    private Router router;

    public UpdatePresenter(Scheduler scheduler, Router router, String name, String lastmage, int age) {
        this.scheduler = scheduler;
        this.router = router;
        getViewState().setName(name);
        getViewState().setLastName(lastmage);
        getViewState().setAge(age);
    }

    public void onBackPressed() {
        router.exit();
    }
}
