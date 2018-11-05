package com.yurentsy.watchingyou.mvp.model.cache;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;

public interface Cache {
    Observable<Boolean> put(Person person);

    Observable<Boolean> putAll(List<Person> list);

    Observable<List<Person>> getPersons();

    Observable<Boolean> updatePerson(Person person);
}
