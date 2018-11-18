package com.yurentsy.watchingyou.ui.utils;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;

/**
 * Created by silan on 14.11.2018.
 */

public class Message {
    public static final String SAVE_ERROR = App.getInstance().getString(R.string.title_save_error);//Error! No data saved.
    public static final String DELETE_ERROR = App.getInstance().getString(R.string.title_delete_error);//Error! No data delete.

    public static String getDialogTitleSave() {
        return App.getInstance().getString(R.string.dialog_title_save);//Вы хотите сохранить данные?
    }

    public static String getDialogTitleDelete() {
        return App.getInstance().getString(R.string.dialog_title_delete);//Вы хотите удалить сотрудника?
    }

    public static String getTextErrorFieldIsEmpty() {
        return App.getInstance().getString(R.string.text_error_field_is_empty);//Ошибка! Поле не может быть пустым!
    }

    public static String getTextErrorFieldMinLength() {
        return App.getInstance().getString(R.string.text_error_field_min_length);//"Ошибка! Поле не может иметь менее 2-х символов";
    }


    public static String getTextNewPerson() {
        return App.getInstance().getString(R.string.text_new_employee);//Новый сотрудник
    }

    public static String getTextFullName(String name, String surname) {
        return String.format("%s %s", name, surname);
    }

    public static String getTextInfoStatusPerson(int countInJob, int countOutJob) {
        return String.format("%s %d , %s %d", App.getInstance().getString(R.string.info_status_work),
                countInJob,
                App.getInstance().getString(R.string.info_status_absents),
                countOutJob);
    }
}
