package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;

public interface Repo {
    Observable<List<Person>> getPersons();

    Observable<Boolean> updatePerson(Person person);
}
