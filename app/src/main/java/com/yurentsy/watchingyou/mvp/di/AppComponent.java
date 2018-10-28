package com.yurentsy.watchingyou.mvp.di;

import com.yurentsy.watchingyou.mvp.di.modules.ApiModule;
import com.yurentsy.watchingyou.mvp.di.modules.CacheModule;
import com.yurentsy.watchingyou.mvp.di.modules.NavigationModule;
import com.yurentsy.watchingyou.mvp.di.modules.RepoModule;
import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NavigationModule.class,
        ApiModule.class,
        RepoModule.class,
        CacheModule.class
})
public interface AppComponent {
    void inject(StartActivity startActivity);

    void inject(MainFragment mainFragment);
}
