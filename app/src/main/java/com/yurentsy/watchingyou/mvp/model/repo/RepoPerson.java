package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.NetworkStatus;
import com.yurentsy.watchingyou.mvp.model.api.ApiService;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
            return api.getPersons().subscribeOn(Schedulers.io())
                    .doOnNext(list -> {
                        cache.putAll(list);
                        isReceived = true;
                    });
        } else {
            return cache.getPersons().subscribeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<Person> getPersonById(String id) {
        return null;
    }
}
