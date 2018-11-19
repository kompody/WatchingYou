package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.PersonEditView;
import com.yurentsy.watchingyou.ui.utils.Message;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class PersonEditPresenter extends MvpPresenter<PersonEditView> {

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

    public void savePerson() {
        repo.getPersons()
                .subscribeOn(Schedulers.io())
                .map(personList -> {
                    if (personList.contains(person)) {
                        return repo.updatePerson(person).subscribeOn(Schedulers.io()).blockingFirst();
                    } else {
                        return repo.insertPerson(person).subscribeOn(Schedulers.io()).blockingFirst();
                    }
                }).observeOn(scheduler)
                .subscribe(result -> {
                    if (!result) {
                        getViewState().showInfoMessage(Message.SAVE_ERROR);
                    }
                    onBackPressed();
                });
    }

    public void onClickSavePerson(String nameSurname, String position, String phone) {
        String[] arr = nameSurname.split(" ");
        String name = arr.length == 1 ? arr[0] : "";
        String surname = "";
        if (arr.length == 2) {
            name = arr[0];
            surname = arr[1];
        }
        person.setName(name);
        person.setSurname(surname);
        person.setPosition(position);
        person.setNumber(phone);
        if (!checkIsEmpty()) {
            getViewState().showDialog(Message.getDialogTitleSave());
        }
    }

    private boolean checkIsEmpty() {
        //можно сделать дополнительную проверку на сохраняемые поля
        return false;
    }
}
