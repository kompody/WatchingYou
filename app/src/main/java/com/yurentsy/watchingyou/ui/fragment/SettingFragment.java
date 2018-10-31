package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.MainPresenter;
import com.yurentsy.watchingyou.mvp.presenter.SettingPresenter;
import com.yurentsy.watchingyou.mvp.view.MainView;
import com.yurentsy.watchingyou.mvp.view.SettingView;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class SettingFragment extends MvpAppCompatFragment implements SettingView,BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    SettingPresenter presenter;

    public static SettingFragment getNewInstance() {
        SettingFragment fragment = new SettingFragment();
        // TODO: 28.10.2018 если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @ProvidePresenter
    public SettingPresenter provideGeneralPresenter() {
        return new SettingPresenter(AndroidSchedulers.mainThread(), router);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);

        Toolbar toolbar=view.findViewById(R.id.d_toolbar);
        toolbar.setNavigationOnClickListener(item->{
            onBackPressed();
        });
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void init() {
        // TODO: 28.10.2018 adapter и все такое
    }

}