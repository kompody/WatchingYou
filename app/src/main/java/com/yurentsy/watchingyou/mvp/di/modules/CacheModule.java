package com.yurentsy.watchingyou.mvp.di.modules;

import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.cache.PaperCache;
import com.yurentsy.watchingyou.mvp.model.cache.room.PersonRoom;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Provides
    @Singleton
    public Cache cache() {
        return new PersonRoom();
    }
}
