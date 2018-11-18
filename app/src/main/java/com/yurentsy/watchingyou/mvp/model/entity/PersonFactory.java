package com.yurentsy.watchingyou.mvp.model.entity;

import com.yurentsy.watchingyou.ui.utils.Message;

/**
 * Created by silan on 14.11.2018.
 */

public class PersonFactory {
    //значения по умолчанию
    private String id = "0";
    private String urlPhoto = "-";
    private String name = Message.getTextNewPerson();

    public Person getPerson() {
        return new Person(
                id,
                name,
                "",
                "",
                "",
                "",
                "",
                urlPhoto
        );
    }
}
