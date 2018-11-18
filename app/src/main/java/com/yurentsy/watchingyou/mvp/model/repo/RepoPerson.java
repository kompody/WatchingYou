package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.ui.utils.NetworkStatus;
import com.yurentsy.watchingyou.mvp.model.api.ApiService;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;

public class RepoPerson implements Repo {
    private static boolean isReceived = false;
    private ApiService api;
    private Cache cache;

    public RepoPerson(ApiService api, Cache cache) {
        this.api = api;
        this.cache = cache;
    }

    @Override
    public Observable<List<Person>> getPersons() {
        if (NetworkStatus.isOnline() && !isReceived) {
            return api.getPersons()
                    .map(list -> {
                        List<Person> cacheList = cache.getPersons().blockingFirst();
                        if (cacheList.size() == 0) {
                            cache.putAll(list).subscribe();
                            isReceived = true;
                            return list;
                        } else return cacheList;
                    });
        } else {
            return cache.getPersons();
        }
    }

    @Override
    public Observable<Boolean> updatePerson(Person person) {
        return cache.updatePerson(person);
    }

    @Override
    public Observable<Boolean> insertPerson(Person p) {
        return cache.insert(p);
    }

    @Override
    public Observable<Boolean> deletePerson(Person person) {
        return cache.delete(person);
    }
}
