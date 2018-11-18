package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.Screens;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.PersonView;
import com.yurentsy.watchingyou.ui.utils.Message;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {
    private Scheduler scheduler;
    private Router router;
    private Repo repo;
    private Person person;

    @SuppressLint("CheckResult")
    public PersonPresenter(Scheduler scheduler, Router router, Repo repo, Person person) {
        this.scheduler = scheduler;
        this.router = router;
        this.repo = repo;
        this.person = person;
        getViewState().setCard(person);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void onClickButtonCome() {
        person.setOnline(!person.isWorking());
        repo.updatePerson(person)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(result -> {
                    if (!result)
                        getViewState().showInfoMessage(Message.SAVE_ERROR);
                });
    }

    public void onClickButtonAway() {
        //временно дублирует код onClickButtonCome
        onClickButtonCome();
    }

    public void onClickMenuEdit() {
        router.replaceScreen(new Screens.PersonEditScreen(person));
    }

    public void onClickMenuDelete() {
        repo.deletePerson(person)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(result -> {
                    if (!result) {
                        getViewState().showInfoMessage(Message.DELETE_ERROR);
                        router.exit();
                    }
                });
    }
}
