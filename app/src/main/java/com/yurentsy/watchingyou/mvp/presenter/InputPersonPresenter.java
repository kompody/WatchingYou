package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.InputPersonView;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class InputPersonPresenter extends MvpPresenter<InputPersonView> {
    private final String SAVE_OK = App.getInstance().getString(R.string.title_save_ok);//Data saved successfully.
    private final String SAVE_ERROR = App.getInstance().getString(R.string.title_save_error);//Error! No data saved.

    private Scheduler scheduler;
    private Router router;
    private Repo repo;

    @SuppressLint("CheckResult")
    public InputPersonPresenter(Scheduler scheduler, Router router, Repo repo) {
        this.scheduler = scheduler;
        this.router = router;
        this.repo = repo;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void onClickSavePerson(Person person) {
        repo.insertPerson(person)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(result -> {
                    if (result) {
                        getViewState().showInfoMessage(SAVE_OK);
                    } else getViewState().showInfoMessage(SAVE_ERROR);
                    onBackPressed();
                });
    }

}
