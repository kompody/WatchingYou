package com.yurentsy.watchingyou.mvp.model.cache;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;

/**
 * Created by silan on 30.10.2018.
 */

public class PaperCache implements Cache {
    private final String BASE_KEY = "paper_key";

    @Override
    public Observable<Boolean> put(Person person) {
        return Observable.create(e -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(person);
            Paper.book().write(BASE_KEY, list);
            e.onNext(true);
            e.onComplete();
        });
    }

    @Override
    public Observable<Boolean> putAll(List<Person> list) {
        return Observable.create(e -> {
            Paper.book().write(BASE_KEY, list);
            e.onNext(true);
            e.onComplete();
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

    @Override
    public Observable<Boolean> updatePerson(Person person) {
        return Observable.create(e -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                e.onNext(false);
            } else {
                int index = list.indexOf(person);
                list.set(index, person);
                Paper.book().write(BASE_KEY, list);
                e.onNext(true);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<Boolean> insert(Person p) {
        return Observable.create(e -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                e.onNext(false);
            } else {
                int newID = getMaxId() + 1;
                p.setID(String.valueOf(newID));
                list.add(p);
                Paper.book().write(BASE_KEY, list);
                e.onNext(true);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<Boolean> delete(Person person) {
        return Observable.create(e -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                e.onNext(false);
            } else {
                list.remove(person);
                Paper.book().write(BASE_KEY, list);
                e.onNext(true);
            }
            e.onComplete();
        });
    }

    private int getMaxId() {
        List<Person> list = Paper.book().read(BASE_KEY);
        if (list == null) {
            return -1;
        } else {
            int maxId = 0;
            for (Person person : list) {
                int currentId = Integer.parseInt(person.getId());
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
            return maxId;
        }
    }

}
