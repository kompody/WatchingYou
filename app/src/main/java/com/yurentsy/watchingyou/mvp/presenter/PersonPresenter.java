package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.view.PersonView;

import java.io.Serializable;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PersonPresenter extends MvpPresenter<PersonView> {

    private Scheduler scheduler;
    private Router router;

    @SuppressLint("CheckResult")
    public PersonPresenter(Scheduler scheduler, Router router, Person person) {
        this.scheduler = scheduler;
        this.router = router;

/*        getViewState().setName(person.getName());
        getViewState().setUrlPhoto(person.getUrlPhoto());
        getViewState().setSurname(person.getSurname());
        getViewState().setAddress(person.getAddress());
        getViewState().setEmail(person.getEmail());
        getViewState().setPhone(person.getNumber());
        getViewState().setPosition(person.getPosition());*/

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

}
