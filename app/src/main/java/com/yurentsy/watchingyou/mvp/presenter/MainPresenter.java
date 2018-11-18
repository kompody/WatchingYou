package com.yurentsy.watchingyou.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.Screens;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.entity.PersonFactory;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private Scheduler scheduler;
    private Router router;
    private Repo repo;
    private List<Person> people = new ArrayList<>();
    private List<Person> displayedPeople = new ArrayList<>();

    public MainPresenter(Scheduler scheduler, Router router, Repo repo) {
        this.scheduler = scheduler;
        this.router = router;
        this.repo = repo;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Person> getDisplayedPeople() {
        return displayedPeople;
    }

    public void setDisplayedPeople(List<Person> displayedPeople) {
        this.displayedPeople = displayedPeople;
    }

    public void loadPersons() {
        repo.getPersons()
                .flatMap(Observable::fromIterable)
                .sorted((p1, p2) -> (p1.getSurname() + p1.getName()).compareTo(p2.getSurname() + p2.getName()))
                .compose(obs -> Observable.concat(
                        obs.filter(Person::isWorking),
                        obs.filter(p -> !p.isWorking())
                ))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(personList -> {
                    people = personList;
                    displayedPeople = personList;
                    updateViews();
                }, throwable -> {
                    getViewState().showInfoMessage(throwable.getMessage());
                });
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadPersons();
    }

    public void onBackPressed() {
        router.exit();
    }

    public int getDisplayedPersoneSize() {
        return displayedPeople.size();
    }

    public void onClickMenuSetting() {
        router.navigateTo(new Screens.SettingScreen());
    }

    public void onClickPerson(int position) {
        router.navigateTo(new Screens.PersonScreen(displayedPeople.get(position)));
    }

    private void updateStatusInfo() {
        int countPersonsInJob = getCountPersonInJob(displayedPeople);
        getViewState().showInfoStatus(countPersonsInJob, displayedPeople.size() - countPersonsInJob);
    }

    private int getCountPersonInJob(List<Person> personList) {
        int count = 0;
        for (Person person : personList) {
            if (person.isWorking()) {
                count++;
            }
        }
        return count;
    }

    public void updateViews() {
        getViewState().updateList();
        updateStatusInfo();
    }

    public void onClickMenuNewPerson() {
        router.navigateTo(new Screens.PersonEditScreen(new PersonFactory().getPerson()));
    }
}
