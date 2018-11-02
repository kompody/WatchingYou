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
    public void put(Person person) {
        List<Person> list = Paper.book().read(BASE_KEY);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(person);
        Paper.book().write(BASE_KEY, list);
    }

    @Override
    public void putAll(List<Person> list) {
        Paper.book().write(BASE_KEY, list);
    }

    @Override
    public Observable<Person> getPersonById(String id) {
        return Observable.just(id)
                .map(i -> {
                    //Читаем данные
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
}
