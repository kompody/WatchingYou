package com.yurentsy.watchingyou;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.fragment.FindFragment;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;
import com.yurentsy.watchingyou.ui.fragment.SettingFragment;
import com.yurentsy.watchingyou.ui.fragment.UpdateFragment;

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
        }

        @Override
        public Fragment getFragment() {
            return MainFragment.getNewInstance();
        }
    }

    public static final class FindScreen extends SupportAppScreen {
        private int i;

        public FindScreen(int i) {
            this.i = i;
        }

        @Override
        public Fragment getFragment() {
            return FindFragment.getNewInstance(i);
        }
    }

    public static final class UpdateScreen extends SupportAppScreen {
        private String name;
        private String lastName;
        private int age;

        public UpdateScreen(String name, String lastName, int age) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public Fragment getFragment() {
            return UpdateFragment.getNewInstance(name, lastName, age);
        }
    }

    public static final class SettingScreen extends SupportAppScreen {
        public SettingScreen() {
            //empty
        }

        @Override
        public Fragment getFragment() {
            return SettingFragment.getNewInstance();
        }
    }
}
