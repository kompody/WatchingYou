package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.InputPersonPresenter;
import com.yurentsy.watchingyou.mvp.view.InputPersonView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class InputPersonFragment extends MvpAppCompatFragment implements InputPersonView, BackButtonListener {

    @Inject
    Repo repo;

    @Inject
    Router router;

    @InjectPresenter
    InputPersonPresenter presenter;


    public static InputPersonFragment getNewInstance() {
        InputPersonFragment fragment = new InputPersonFragment();
        //если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @ProvidePresenter
    public InputPersonPresenter provideGeneralPresenter() {
        return new InputPersonPresenter(AndroidSchedulers.mainThread(), router, repo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_person, container, false);
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

    @OnClick(R.id.card_button_save_input_person)
    void onClickButton(Button b) {
        presenter.onClickSavePerson(getPerson());
    }

    @BindView(R.id.text_input_surname)
    TextInputLayout surname;
    @BindView(R.id.text_input_name)
    TextInputLayout name;
    @BindView(R.id.text_input_position)
    TextInputLayout position;
    @BindView(R.id.text_input_phone)
    TextInputLayout phone;
    @BindView(R.id.card_input_person_photo)
    ImageView photo;

    private Person getPerson() {
        //todo нужно подумать как будет генерировать ID
        return new Person("",
                name.getEditText().getText().toString(),
                surname.getEditText().getText().toString(),
                position.getEditText().getText().toString(),
                phone.getEditText().getText().toString(),
                "",
                "",
                //todo передаю пустой url, как получать будем?
                "");
    }

    @Override
    public void showInfoMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

}