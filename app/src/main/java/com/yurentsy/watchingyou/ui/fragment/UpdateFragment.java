package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.presenter.UpdatePresenter;
import com.yurentsy.watchingyou.mvp.view.UpdateView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class UpdateFragment extends MvpAppCompatFragment implements UpdateView, BackButtonListener {

    private static final String KEY_NAME = "name";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_AGE = "age";

    @BindView(R.id.efu_name)
    TextView name;

    @BindView(R.id.efu_lastname)
    TextView lastname;

    @BindView(R.id.efu_age)
    TextView age;

    @Inject
    Router router;

    @InjectPresenter
    UpdatePresenter presenter;

    public static Fragment getNewInstance(String name, String lastName, int age) {
        UpdateFragment fragment = new UpdateFragment();

        Bundle b = new Bundle();
        b.putString(KEY_NAME, name);
        b.putString(KEY_LASTNAME, lastName);
        b.putInt(KEY_AGE, age);
        fragment.setArguments(b);

        return fragment;
    }

    @ProvidePresenter
    public UpdatePresenter provideGeneralPresenter() {
        return new UpdatePresenter(AndroidSchedulers.mainThread(), router,
                getArguments().getString(KEY_NAME, ""),
                getArguments().getString(KEY_LASTNAME, ""),
                getArguments().getInt(KEY_AGE, 0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this); //create method inject in AppComponent
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment_update, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setLastName(String lastmage) {
        this.lastname.setText(lastmage);
    }

    @Override
    public void setAge(int age) {
        this.age.setText(String.valueOf(age));
    }
}
