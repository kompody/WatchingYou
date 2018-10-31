package com.yurentsy.watchingyou;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;
import com.yurentsy.watchingyou.ui.fragment.PersonFragment;
import com.yurentsy.watchingyou.ui.fragment.SettingFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static final class StartScreen extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, StartActivity.class);
        }
    }

    public static final class MainScreen extends SupportAppScreen {
        public MainScreen() {
            // TODO: 28.10.2018 если нужно в конструктор фрагмента что-то предать
        }

        @Override
        public Fragment getFragment() {
            return MainFragment.getNewInstance();
        }
    }

    public static final class SettingScreen extends SupportAppScreen {
        public SettingScreen() {
            // TODO: 28.10.2018 если нужно в конструктор фрагмента что-то предать
        }

        @Override
        public Fragment getFragment() {
            return SettingFragment.getNewInstance();
        }
    }

    public static final class PersonScreen extends SupportAppScreen {
        private String name;
/*        private String surname;
        private String position;*/
        private String urlPhoto;

        public PersonScreen(Person person) {
            // TODO: 28.10.2018 если нужно в конструктор фрагмента что-то предать
            this.name=person.getName();
/*            this.surname=person.getSurname();
            this.position=person.getPosition();*/
            this.urlPhoto=person.getUrlPhoto();
        }

        @Override
        public Fragment getFragment() {
            return PersonFragment.getNewInstance(name,urlPhoto);
        }
    }
}
