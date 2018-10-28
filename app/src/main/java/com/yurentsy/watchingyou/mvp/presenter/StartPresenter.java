package com.yurentsy.watchingyou.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.yurentsy.watchingyou.Screens;
import com.yurentsy.watchingyou.mvp.view.StartView;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    private Router router;

    public StartPresenter(Router router) {
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onBackPressed() {
        router.exit();
    }

    public void restartActivity() {
        router.replaceScreen(new Screens.StartScreen());
    }

    public void toMainScreen() {
        router.replaceScreen(new Screens.MainScreen());
    }
}
