package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.PersonView;

import io.reactivex.Scheduler;
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
        repo.updatePerson(person);
    }

    public void onClickButtonAway() {
        //временно дублирует код onClickButtonCome
        person.setOnline(!person.isWorking());
        repo.updatePerson(person);
    }
}
