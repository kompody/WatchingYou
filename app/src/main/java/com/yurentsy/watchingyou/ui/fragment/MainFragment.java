package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.MainPresenter;
import com.yurentsy.watchingyou.mvp.view.MainView;
import com.yurentsy.watchingyou.ui.adapter.MainAdapter;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class MainFragment extends MvpAppCompatFragment implements MainView, BackButtonListener {
    @BindView(R.id.recycler_main)
    RecyclerView recyclerView;
    private MainAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.info_status)
    TextView infoStatus;

    @Inject
    Repo repo;

    @Inject
    Router router;

    @InjectPresenter
    MainPresenter presenter;

    public static MainFragment getNewInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @ProvidePresenter
    public MainPresenter provideGeneralPresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread(), router, repo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        toolbar.setNavigationOnClickListener(view1 -> onBackPressed());
        toolbar.inflateMenu(R.menu.fragment_menu);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        MenuItem searchItem = toolbar.getMenu().findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null)
                    adapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                presenter.onClickMenuSetting();
                return true;
            case R.id.menu_item_input_person:
                presenter.onClickMenuNewPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showInfoStatus(int countInJob, int countOutJob) {
        infoStatus.setText(String.format(getString(R.string.info_status_title), countInJob, countOutJob));
    }

    @Override
    public void showInfoMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadPersons();
    }
}