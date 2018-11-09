package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.PersonEditPresenter;
import com.yurentsy.watchingyou.mvp.view.PersonEditView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class PersonEditFragment extends MvpAppCompatFragment implements PersonEditView, BackButtonListener {

    @BindView(R.id.card_person_name_surname)
    EditText nameSurname;
    @BindView(R.id.card_person_position)
    EditText position;
    @BindView(R.id.card_person_phone)
    EditText phone;
    @BindView(R.id.card_person_photo)
    ImageView photo;

    @Inject
    Repo repo;

    @Inject
    Router router;

    @InjectPresenter
    PersonEditPresenter presenter;


    public static PersonEditFragment getNewInstance() {
        PersonEditFragment fragment = new PersonEditFragment();
        //если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @ProvidePresenter
    public PersonEditPresenter provideGeneralPresenter() {
        return new PersonEditPresenter(AndroidSchedulers.mainThread(), router, repo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_edit, container, false);
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

    @OnClick(R.id.card_button_save)
    void onClickButton(Button b) {
        presenter.onClickSavePerson(getPerson());
    }


    private Person getPerson() {
        //todo нужно подумать как будет генерировать ID
        return new Person("",
                nameSurname.getText().toString(),
                "",
                position.getText().toString(),
                phone.getText().toString(),
                "",
                "",
                //todo кастомный urlString, как получать будем?
                photo.getDrawable().toString());
    }

    @Override
    public void showInfoMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

}