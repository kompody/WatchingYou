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
import com.squareup.picasso.Picasso;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.PersonEditPresenter;
import com.yurentsy.watchingyou.mvp.view.DialogView;
import com.yurentsy.watchingyou.mvp.view.PersonEditView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

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
        new DialogView(getView(),
                getString(R.string.dialog_title_save),
                () -> presenter.savePerson(getPerson()));
    }

    @Override
    public void setCard(Person person) {
        if (person != null) {
            nameSurname.setText(String.format("%s %s", person.getName(), person.getSurname()));
            toolbar.setTitle(nameSurname.getText().toString());
            position.setText(person.getPosition());
            phone.setText(person.getNumber());
            Picasso.get()
                    .load(person.getUrlPhoto())
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_crop_original_black_24dp)
                    .into(photo);
        }
    }

    @Override
    public void showInfoMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Person getPerson() {
        //todo нужно переписать этот код, он мне не нравиться, пока заглужка
        Person person = presenter.getPerson();
        String[] arr = nameSurname.getText().toString().split(" ");
        String name = "";
        String surname = "";
        if (arr.length == 1) {
            name = arr[0];
        } else if (arr.length == 2) {
            name = arr[0];
            surname = arr[1];
        }
        return new Person(person.getId(),
                name,
                surname,
                position.getText().toString(),
                phone.getText().toString(),
                person.getEmail(),
                person.getAddress(),
                //todo кастомный urlString, как получать будем?
                person.getUrlPhoto());
    }

}