package com.yurentsy.watchingyou.mvp.model.repo;

import android.support.v7.widget.RecyclerView;

import com.yurentsy.watchingyou.NetworkStatus;
import com.yurentsy.watchingyou.mvp.model.api.ApiService;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class RepoPerson implements Repo{
    private Cache cache;
    private ApiService api;

    @Override
    public Observable<List<Person>> getPersons() {
        if (NetworkStatus.isOnline()) {
            return api.getPersons().subscribeOn(Schedulers.io());
        } else {
            return cache.getPersons();
        }
    }

    @Override
    public Observable<Person> getPersonById(String id) {
        return null;
    }
}
