package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface Repo {
    Observable<Person> getPersonById(String id);
    Observable<List<Person>> getPersons();
}
