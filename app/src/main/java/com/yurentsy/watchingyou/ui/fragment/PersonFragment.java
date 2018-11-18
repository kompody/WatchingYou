package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.model.repo.Repo;
import com.yurentsy.watchingyou.mvp.presenter.PersonPresenter;
import com.yurentsy.watchingyou.mvp.view.DialogView;
import com.yurentsy.watchingyou.mvp.view.PersonView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;
import com.yurentsy.watchingyou.mvp.model.image.ImageLoader;
import com.yurentsy.watchingyou.ui.utils.Message;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class PersonFragment extends MvpAppCompatFragment implements PersonView, BackButtonListener {
    private static final String KEY_PERSON = "person_fragment";

    @Inject
    ImageLoader imageLoader;

    @Inject
    Repo repo;

    @Inject
    Router router;

    @InjectPresenter
    PersonPresenter presenter;

    @BindView(R.id.card_person_name_surname)
    TextView name;
    @BindView(R.id.card_person_phone)
    TextView phone;
    @BindView(R.id.card_person_position)
    TextView position;
    @BindView(R.id.card_person_photo)
    ImageView photo;
    @BindView(R.id.button_come)
    Button buttonCome;
    @BindView(R.id.button_away)
    Button buttonAway;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        switch (item.getItemId()) {
            case R.id.menu_item_edit: {
                presenter.onClickMenuEdit();
                return true;
            }
            case R.id.menu_item_delete: {
                new DialogView(getView(), Message.getDialogTitleDelete(),
                        () -> presenter.onClickMenuDelete());
                return true;
            }
            case R.id.home: {
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
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
        //настройки toolbar
        toolbar.setNavigationOnClickListener(view1 -> onBackPressed());
        toolbar.inflateMenu(R.menu.menu_person_edit);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    @OnClick(R.id.button_come)
    void onClickButtonCome() {
        hideButtons(true);
        presenter.onClickButtonCome();
    }

    @OnClick(R.id.button_away)
    void onClickButtonAway() {
        hideButtons(false);
        presenter.onClickButtonAway();
    }

    @Override
    public void setCard(Person person) {
        name.setText(String.format("%s %s", person.getName(), person.getSurname()));
        toolbar.setTitle(name.getText().toString());
        phone.setText(person.getNumber());
        position.setText(person.getPosition());
        imageLoader.loadInto(person.getUrlPhoto(),photo);
        hideButtons(person.isWorking());
    }

    @Override
    public void showInfoMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void hideButtons(boolean flag) {
        buttonCome.setEnabled(!flag);
        buttonAway.setEnabled(flag);
    }

}