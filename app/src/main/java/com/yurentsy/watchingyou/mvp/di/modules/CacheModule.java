package com.yurentsy.watchingyou.mvp.di.modules;

import com.yurentsy.watchingyou.mvp.model.cache.Cache;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Provides
    public Cache cache() {
        return null;
    }
}
