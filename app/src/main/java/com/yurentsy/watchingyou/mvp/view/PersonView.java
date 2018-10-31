package com.yurentsy.watchingyou.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.yurentsy.watchingyou.mvp.model.entity.Person;

public interface PersonView extends MvpView {
    void init();

    void setCard(Person person);

/*    void setName(String name);

    void setUrlPhoto(String urlPhoto);

    void setSurname(String surname);

    void setAddress(String address);

    void setEmail(String email);

    void setPhone(String phone);

    void setPosition(String position);*/
}
