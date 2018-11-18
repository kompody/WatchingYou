package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.image.ImageLoader;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.PersonEditPresenter;
import com.yurentsy.watchingyou.mvp.view.DialogView;
import com.yurentsy.watchingyou.mvp.view.PersonEditView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;
import com.yurentsy.watchingyou.ui.utils.Message;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class PersonEditFragment extends MvpAppCompatFragment implements PersonEditView, BackButtonListener {
    private static final String KEY_PERSON = "person_edit_fragment";

    @BindView(R.id.card_person_name_surname)
    EditText nameSurname;
    @BindView(R.id.card_person_position)
    EditText position;
    @BindView(R.id.card_person_phone)
    EditText phone;
    @BindView(R.id.card_person_photo)
    ImageView photo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    Repo repo;

    @Inject
    Router router;

    @Inject
    ImageLoader imageLoader;

    @InjectPresenter
    PersonEditPresenter presenter;

    public static PersonEditFragment getNewInstance(Person person) {
        PersonEditFragment fragment = new PersonEditFragment();
        if (person == null) {
            return fragment;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PERSON, person);
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    public PersonEditPresenter provideGeneralPresenter() {
        return new PersonEditPresenter(AndroidSchedulers.mainThread(), router, repo, (Person) getArguments().getSerializable(KEY_PERSON));
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
    void onClickButtonSave() {
        if (!checkFieldPerson())
            presenter.onClickSavePerson(
                    nameSurname.getText().toString(),
                    position.getText().toString(),
                    phone.getText().toString());
    }

    private boolean checkFieldPerson() {
        if (checkEditText(nameSurname) ||
                checkEditText(position) ||
                checkEditText(phone)) {
            return true;
        }
        return false;
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().isEmpty() || editText.getText().toString().trim().equals("")) {
            editText.setError(Message.getTextErrorFieldIsEmpty());
            return true;
        } else if (editText.getText().toString().trim().length() < 2) {
            editText.setError(Message.getTextErrorFieldMinLength());
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void setCard(Person person) {
        if (person != null) {
            toolbar.setTitle(Message.getTextFullName(person.getName(), person.getSurname()));
            String name = person.getName().isEmpty() ? "" : person.getName();
            if (name.equals(getString(R.string.text_new_employee))) {
                name = "";
            }
            String surname = person.getSurname().isEmpty() ? "" : person.getSurname();
            nameSurname.setText(Message.getTextFullName(name, surname));
            position.setText(person.getPosition());
            phone.setText(person.getNumber());
            imageLoader.loadInto(person.getUrlPhoto(), photo);
        }
    }

    @Override
    public void showDialog(String title) {
        new DialogView(getView(),
                title,
                () -> presenter.savePerson());
    }

    @Override
    public void showInfoMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}