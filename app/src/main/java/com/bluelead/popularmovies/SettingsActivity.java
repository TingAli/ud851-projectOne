package com.bluelead.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import java.util.List;

public class SettingsActivity extends PreferenceActivity {
    private final Context CONTEXT = SettingsActivity.this;
    private Toast mToast;

    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return MyPreferenceFragment.class.getName().equals(fragmentName);
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        private CheckBoxPreference mPopularCheckBoxPreference;
        private CheckBoxPreference mTopRatedCheckBoxPreference;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.fragmented_preferences);

            mTopRatedCheckBoxPreference = (CheckBoxPreference) findPreference("topRated_pref_cb");
            mPopularCheckBoxPreference = (CheckBoxPreference) findPreference("popular_pref_cb");

            mTopRatedCheckBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(mTopRatedCheckBoxPreference.isChecked()) {
                        mPopularCheckBoxPreference.setChecked(true);
                    }
                    else {
                        mPopularCheckBoxPreference.setChecked(false);
                    }
                    return true;
                }
            });

            mPopularCheckBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(mPopularCheckBoxPreference.isChecked()) {
                        mTopRatedCheckBoxPreference.setChecked(true);
                    }
                    else {
                        mTopRatedCheckBoxPreference.setChecked(false);
                    }
                    return true;
                }
            });
        }
    }
}
