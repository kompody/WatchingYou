package com.yurentsy.watchingyou.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.presenter.StartPresenter;
import com.yurentsy.watchingyou.mvp.view.StartView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class StartActivity extends MvpAppCompatActivity implements StartView {

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;
    @InjectPresenter
    StartPresenter presenter;
    private Navigator navigator = new SupportAppNavigator(this, R.id.main_container);

    @ProvidePresenter
    public StartPresenter provideMainPresenter() {
        StartPresenter presenter = new StartPresenter(router);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            presenter.toMainScreen();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void init() {
        // TODO: 28.10.2018 тут всякие запросы на доступ к функция ОС
    }
}
