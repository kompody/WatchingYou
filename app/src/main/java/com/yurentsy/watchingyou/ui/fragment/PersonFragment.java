package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;
import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.presenter.PersonPresenter;
import com.yurentsy.watchingyou.mvp.presenter.SettingPresenter;
import com.yurentsy.watchingyou.mvp.view.PersonView;
import com.yurentsy.watchingyou.mvp.view.SettingView;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Router;

public class PersonFragment extends MvpAppCompatFragment implements PersonView,BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    PersonPresenter presenter;

    public static PersonFragment getNewInstance(String name,String urlPhoto) {
        PersonFragment fragment = new PersonFragment();
        // TODO: 28.10.2018 если все же что-то добавил то fragment.setArguments(bundle)
        Bundle b=new Bundle();
        b.putString("name",name);
        b.putString("urlPhoto",urlPhoto);
        fragment.setArguments(b);
        return fragment;
    }

    @ProvidePresenter
    public PersonPresenter provideGeneralPresenter() {
        return new PersonPresenter(AndroidSchedulers.mainThread(), router,getArguments().getString("name"),getArguments().getString("urlPhoto"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_card, container, false);
        ButterKnife.bind(this, view);

        Toolbar toolbar=view.findViewById(R.id.d_toolbar);
        toolbar.setTitle(R.string.person);
        toolbar.setNavigationOnClickListener(item->{
            onBackPressed();
        });
        return view;
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void init() {
        // TODO: 28.10.2018 adapter и все такое
    }

    @BindView(R.id.person_card_name)
    TextView tvName;
    @BindView(R.id.person_card_photo)
    ImageView imagePhoto;

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setUrlPhoto(String urlPhoto) {
        Picasso.get()
                .load(urlPhoto)
                .into(imagePhoto);
    }

}