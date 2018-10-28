package com.yurentsy.watchingyou.mvp.di.modules;

import com.yurentsy.watchingyou.mvp.model.api.ApiService;
import com.yurentsy.watchingyou.mvp.model.cache.Cache;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {
    @Provides
    public Repo generalRepository(ApiService api, Cache cache) {
        return null;
    }
}
