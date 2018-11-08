package com.yurentsy.watchingyou.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class SettingFragment extends PreferenceFragmentCompat implements BackButtonListener, PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    @Inject
    Router router;

    public static SettingFragment getNewInstance() {
        SettingFragment fragment = new SettingFragment();
        //если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);

        Preference preference = findPreference("lang_ru");
        preference.setOnPreferenceClickListener(preference1 -> {
            Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Toolbar toolbar = null;
        if (view != null) {
            toolbar = view.findViewById(R.id.toolbar);
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        return view;
    }

    @Override
    public boolean onBackPressed() {
        router.exit();
        return true;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public Fragment getCallbackFragment() {
        return this;
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
        preferenceFragmentCompat.setPreferenceScreen(preferenceScreen);
        return true;
    }
}