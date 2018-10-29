package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.entity.PersonFactory;
import com.yurentsy.watchingyou.mvp.presenter.MainPresenter;
import com.yurentsy.watchingyou.mvp.view.MainView;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class MainFragment extends MvpAppCompatFragment implements MainView, BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    MainPresenter presenter;

    public static MainFragment getNewInstance() {
        MainFragment fragment = new MainFragment();
        // TODO: 28.10.2018 если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @ProvidePresenter
    public MainPresenter provideGeneralPresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread(), router);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.title1:
                // do
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @BindView(R.id.recycler_main)
    RecyclerView recyclerView;
    private MainAdapter adapter;

    @Override
    public void init() {
        // TODO: 28.10.2018 adapter и все такое
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //тестовый список
        adapter = new MainAdapter(PersonFactory.getList(),presenter);
        recyclerView.setAdapter(adapter);
    }


}