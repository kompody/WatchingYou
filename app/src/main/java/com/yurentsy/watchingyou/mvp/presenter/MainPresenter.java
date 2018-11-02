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
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Scheduler scheduler;
    private Router router;
    private Repo repo;

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    private List<Person> people = new ArrayList<>();

    @SuppressLint("CheckResult")
    public MainPresenter(Scheduler scheduler, Router router, Repo repo) {
        this.scheduler = scheduler;
        this.router = router;
        this.repo = repo;

        repo.getPersons()
                .observeOn(scheduler)
                .subscribe((List<Person> persones) -> {
                    people.addAll(persones);
                    getViewState().updateList();
                });
    }

    public void updatePersons(){
        repo.getPersons()
                .observeOn(scheduler)
                .subscribe(personList -> {
                    people=personList;
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
}
