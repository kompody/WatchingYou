package com.yurentsy.watchingyou.mvp.model.repo;

import com.yurentsy.watchingyou.NetworkStatus;
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
                        isReceived = true;
                        if (cacheList.size() > 0) {
                            return cacheList;
                        } else {
                            cache.putAll(list);
                            return list;
                        }
                    });
        } else {
            return cache.getPersons();
        }
    }

    @Override
    public void updatePerson(Person person) {
        cache.updatePerson(person);
    }
}
