package com.yurentsy.watchingyou.mvp.di;

import com.yurentsy.watchingyou.mvp.di.modules.ImageModule;
import com.yurentsy.watchingyou.mvp.di.modules.NavigationModule;
import com.yurentsy.watchingyou.mvp.di.modules.RepoModule;
import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;
import com.yurentsy.watchingyou.ui.fragment.PersonEditFragment;
import com.yurentsy.watchingyou.ui.fragment.PersonFragment;
import com.yurentsy.watchingyou.ui.fragment.SettingFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NavigationModule.class,
        RepoModule.class,
        ImageModule.class
})
public interface AppComponent {
    void inject(StartActivity startActivity);

    void inject(MainFragment mainFragment);

    void inject(SettingFragment settingFragment);

    void inject(PersonFragment personFragment);

    void inject(PersonEditFragment inputPersonFragment);

    void inject(MainAdapter.MyViewHolder myViewHolder);
}
