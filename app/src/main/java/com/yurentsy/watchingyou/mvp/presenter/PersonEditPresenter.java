package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.PersonEditView;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PersonEditPresenter extends MvpPresenter<PersonEditView> {
    private final String SAVE_OK = App.getInstance().getString(R.string.title_save_ok);//Data saved successfully.
    private final String SAVE_ERROR = App.getInstance().getString(R.string.title_save_error);//Error! No data saved.

    private Scheduler scheduler;
    private Router router;
    private Repo repo;
    private Person person;

    public Person getPerson() {
        return person;
    }

    @SuppressLint("CheckResult")
    public PersonEditPresenter(Scheduler scheduler, Router router, Repo repo, Person person) {
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

    public void savePerson(Person person) {
        //todo сделать проверку на пустые поля
        repo.getPersons()
                .subscribeOn(Schedulers.io())
                .map(personList -> {
                    if(personList.contains(person)){
                        return repo.updatePerson(person).subscribeOn(Schedulers.io()).blockingFirst();
                    } else {
                        return repo.insertPerson(person).subscribeOn(Schedulers.io()).blockingFirst();
                    }
                }).observeOn(scheduler)
                .subscribe(result -> {
                    if(result){
                        getViewState().showInfoMessage(SAVE_OK);
                    } else {
                        getViewState().showInfoMessage(SAVE_ERROR);
                    }
                    onBackPressed();
                });
    }

}
