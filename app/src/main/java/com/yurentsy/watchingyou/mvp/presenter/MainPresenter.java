package com.yurentsy.watchingyou.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.Screens;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.MainView;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Scheduler scheduler;
    private Router router;
    private Repo repo;
    private List<Person> people = new ArrayList<>();

    @SuppressLint("CheckResult")
    public MainPresenter(Scheduler scheduler, Router router, Repo repo) {
        this.scheduler = scheduler;
        this.router = router;
        this.repo = repo;

        repo.getPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(personList -> {
                    people = personList;
                    getViewState().updateList();
                    updateStatusInfo();
                });
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @SuppressLint("CheckResult")
    public void updatePersons() {
        repo.getPersons()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(personList -> {
                    people = personList;
                    getViewState().updateList();
                    updateStatusInfo();
                });
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void bindViewHolder(MainAdapter.MyViewHolder holder, int position) {
        holder.bind(people.get(position));
        if (people.get(position).isOnline()) {
            updateStatusInfo();
        }
    }

    public int getPersoneSize() {
        return people.size();
    }

    public void onClickMenuSetting() {
        router.navigateTo(new Screens.SettingScreen());
    }

    public void onClickPerson(int position) {
        router.navigateTo(new Screens.PersonScreen(people.get(position)));
    }

    private void updateStatusInfo() {
        int onlinePeople = getCountPersonOnline(people);
        getViewState().showInfoStatus(onlinePeople, people.size() - onlinePeople);
    }

    private int getCountPersonOnline(List<Person> personList) {
        int count = 0;
        for (Person person : personList) {
            if (person.isOnline()) {
                count++;
            }
        }
        return count;
    }
}
