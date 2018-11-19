package com.yurentsy.watchingyou.mvp.di.modules;

import android.arch.persistence.room.Room;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.cache.room.PersonRoom;
import com.yurentsy.watchingyou.mvp.model.cache.room.PersonRoomAbs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    private final String DATABASE_NAME = "persons.db";

    @Provides
    @Singleton
    public Cache cache() {
        return new PersonRoom(provideDatabase());
    }

    @Provides
    public PersonRoomAbs provideDatabase() {
        return Room.databaseBuilder(App.getInstance(), PersonRoomAbs.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
}
