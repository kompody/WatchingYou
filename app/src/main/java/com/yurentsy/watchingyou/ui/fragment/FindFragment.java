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
import com.yurentsy.watchingyou.mvp.presenter.FindPresenter;
import com.yurentsy.watchingyou.mvp.view.FindView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class FindFragment extends MvpAppCompatFragment implements FindView, BackButtonListener {

    private static final String KEY_NUMBER = "number";

    @BindView(R.id.eff_text)
    TextView text;

    @Inject
    Router router;

    @InjectPresenter
    FindPresenter presenter;

    public static Fragment getNewInstance(int i) {
        FindFragment fragment = new FindFragment();

        Bundle b = new Bundle();
        b.putInt(KEY_NUMBER, i);
        fragment.setArguments(b);

        return fragment;
    }

    @ProvidePresenter
    public FindPresenter provideGeneralPresenter() {
        return new FindPresenter(AndroidSchedulers.mainThread(), router, getArguments().getInt(KEY_NUMBER, 0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this); //create method inject in AppComponent
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment_find, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void setNumber(int anInt) {
        text.setText(String.valueOf(anInt));
    }
}
