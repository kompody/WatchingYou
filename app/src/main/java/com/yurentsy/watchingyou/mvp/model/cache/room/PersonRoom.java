package com.yurentsy.watchingyou.mvp.model.cache.room;

import android.arch.persistence.room.Room;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;


public class PersonRoom implements Cache {
    private final String DATABASE_NAME = "persons.db";
    private PersonRoomAbs database;

    public PersonRoom() {
        this.database = Room.databaseBuilder(App.getInstance(), PersonRoomAbs.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    @Override
    public Observable<Boolean> put(Person person) {
        return Observable.just(person)
                .map(person1 -> {
                    database.photoDao().insert(person1);
                    return true;
                });
    }

    @Override
    public Observable<Boolean> putAll(List<Person> list) {
        return Observable.just(list)
                .map(personList -> {
                    database.photoDao().insertAll(personList);
                    return true;
                });
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return Observable.just(database.photoDao().getList());
    }

    @Override
    public Observable<Boolean> updatePerson(Person person) {
        return Observable.just(person)
                .map(person1 -> {
                    database.photoDao().update(person1);
                    return true;
                });
    }

    @Override
    public Observable<Boolean> insert(Person person) {
        return Observable.just(person)
                .map(person1 -> {
                    int maxId = database.photoDao().getMaxId() + 1;
                    person1.setId(String.valueOf(maxId));
                    database.photoDao().insert(person1);
                    return true;
                });
    }

    @Override
    public Observable<Boolean> delete(Person person) {
        return Observable.just(person)
                .map(person1 -> {
                    database.photoDao().delete(person);
                    return true;
                });
    }
}
