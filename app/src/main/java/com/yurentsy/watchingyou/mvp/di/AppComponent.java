package com.yurentsy.watchingyou.mvp.di;

import com.yurentsy.watchingyou.mvp.di.modules.NavigationModule;
import com.yurentsy.watchingyou.mvp.di.modules.RepoModule;
import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.fragment.FindFragment;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;
import com.yurentsy.watchingyou.ui.fragment.SettingFragment;
import com.yurentsy.watchingyou.ui.fragment.UpdateFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NavigationModule.class,
        RepoModule.class
})
public interface AppComponent {
    void inject(StartActivity startActivity);

    void inject(MainFragment mainFragment);

    void inject(SettingFragment settingFragment);

    void inject(FindFragment findFragment);

    void inject(UpdateFragment updateFragment);
}
