package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.PersonPresenter;
import com.yurentsy.watchingyou.mvp.view.PersonView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class PersonFragment extends MvpAppCompatFragment implements PersonView, BackButtonListener {
    private static final String KEY_PERSON = "person";

    @Inject
    Repo repo;

    @Inject
    Router router;

    @InjectPresenter
    PersonPresenter presenter;

    @BindView(R.id.card_name)
    TextView name;
    @BindView(R.id.card_phone)
    TextView phone;
    @BindView(R.id.card_position)
    TextView position;
    @BindView(R.id.card_photo)
    ImageView photo;
    @BindView(R.id.button_come)
    Button buttonCome;
    @BindView(R.id.button_away)
    Button buttonAway;

    public static PersonFragment getNewInstance(Person person) {
        PersonFragment fragment = new PersonFragment();
        //если все же что-то добавил то fragment.setArguments(bundle)
        Bundle b = new Bundle();
        b.putSerializable(KEY_PERSON, person);
        fragment.setArguments(b);
        return fragment;
    }

    @ProvidePresenter
    public PersonPresenter provideGeneralPresenter() {
        return new PersonPresenter(AndroidSchedulers.mainThread(), router, repo, (Person) getArguments().getSerializable(KEY_PERSON));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        ButterKnife.bind(this, view);
        //настройки toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view1 -> onBackPressed());
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void init() {
        //init
    }

    @Override
    public void setCard(Person p) {
        name.setText(String.format("%s:\n%s %s", name.getText(), p.getName(), p.getSurname()));
        phone.setText(String.format("%s:\n%s", phone.getText(), p.getNumber()));
        position.setText(String.format("%s:\n%s", position.getText(), p.getPosition()));
        Picasso.get()
                .load(p.getUrlPhoto())
                .into(photo);
        hideButtons(p.isWorking());
    }

    @Override
    public void showInfoMessage(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }


    @OnClick({R.id.button_come, R.id.button_away})
    void onClickButton(Button b) {
        switch (b.getId()) {
            case R.id.button_come: {
                hideButtons(true);
                presenter.onClickButtonCome();
                break;
            }
            case R.id.button_away: {
                hideButtons(false);
                presenter.onClickButtonAway();
                break;
            }
        }
    }

    private void hideButtons(boolean flag) {
        buttonCome.setEnabled(!flag);
        buttonAway.setEnabled(flag);
    }

}