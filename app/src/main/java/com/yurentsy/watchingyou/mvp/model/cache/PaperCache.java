package com.yurentsy.watchingyou.mvp.model.cache;

import android.annotation.SuppressLint;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by silan on 30.10.2018.
 */

public class PaperCache implements Cache {
    private final String BASE_KEY = "paper_key";

    @SuppressLint("CheckResult")
    @Override
    public void put(Person person) {
        Completable.fromAction(() -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(person);
            Paper.book().write(BASE_KEY, list);
        }).subscribeOn(Schedulers.computation());
    }

    @SuppressLint("CheckResult")
    @Override
    public void putAll(List<Person> list) {
        Completable.fromAction(() -> Paper.book().write(BASE_KEY, list))
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Person> getPersonById(String id) {
        return Observable.just(id)
                .map(i -> {
                    List<Person> list = Paper.book().read(BASE_KEY);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    Person p = list.get(Integer.parseInt(i));
                    if (list.equals(p)) {
                        return p;
                    } else {
                        throw new RuntimeException("There is no such person");
                    }
                });
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return Observable.create(e -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                list = new ArrayList<>();
            }
            e.onNext(list);
            e.onComplete();
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void updatePerson(Person person) {
        Completable.fromAction(() -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                return;
            }
            int index = getIndexPersonById(list, person.getId());
            list.set(index, person);
            Paper.book().write(BASE_KEY, list);
        }).subscribeOn(Schedulers.computation());
    }

    private int getIndexPersonById(List<Person> people, String personId) {
        for (int i = 0; i < people.size(); i++)
            if (people.get(i).getId().equals(personId))
                return i;
        return -1;
    }
}
