package com.yurentsy.watchingyou.mvp.di.modules;

import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@Module
public class CacheModule {
    @Provides
    public Cache cache() {
        return new Cache() {
            @Override
            public void put(Person person) {

            }

            @Override
            public Observable<Person> getPersonById(String id) {
                return null;
            }

            @Override
            public Observable<List<Person>> getPersons() {
                return null;
            }
        };
    }
}
