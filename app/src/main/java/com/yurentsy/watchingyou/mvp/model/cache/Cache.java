package com.yurentsy.watchingyou.mvp.model.cache;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;

public interface Cache {
    void put(Person person);

    void putAll(List<Person> list);

    Observable<Person> getPersonById(String id);

    Observable<List<Person>> getPersons();
}
