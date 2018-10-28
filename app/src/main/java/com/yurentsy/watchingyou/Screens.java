package com.yurentsy.watchingyou;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.yurentsy.watchingyou.ui.activity.StartActivity;
import com.yurentsy.watchingyou.ui.fragment.MainFragment;

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
}
