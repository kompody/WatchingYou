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
        return Observable.create(emitter -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(person);
            Paper.book().write(BASE_KEY, list);
            emitter.onNext(true);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Boolean> putAll(List<Person> list) {
        return Observable.create(emitter -> {
            Paper.book().write(BASE_KEY, list);
            emitter.onNext(true);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return Observable.create(emitter -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                list = new ArrayList<>();
            }
            emitter.onNext(list);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Boolean> updatePerson(Person person) {
        return Observable.create(emitter -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                emitter.onNext(false);
            } else {
                int index = list.indexOf(person);
                list.set(index, person);
                Paper.book().write(BASE_KEY, list);
                emitter.onNext(true);
            }
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Boolean> insert(Person person) {
        return Observable.create(emitter -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                emitter.onNext(false);
            } else {
                int newId = getMaxId() + 1;
                person.setID(String.valueOf(newId));
                list.add(person);
                Paper.book().write(BASE_KEY, list);
                emitter.onNext(true);
            }
            emitter.onComplete();
        });
    }

    @Override
    public Observable<Boolean> delete(Person person) {
        return Observable.create(emitter -> {
            List<Person> list = Paper.book().read(BASE_KEY);
            if (list == null) {
                emitter.onNext(false);
            } else {
                list.remove(person);
                Paper.book().write(BASE_KEY, list);
                emitter.onNext(true);
            }
            emitter.onComplete();
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
