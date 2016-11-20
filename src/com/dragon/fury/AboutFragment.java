package com.dragon.fury;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.content_main, new SettingsPreferenceFragment())
                .commit();
    }

    public static class SettingsPreferenceFragment extends PreferenceFragment {

        private String PREF_WEBSITE = "website";
        private String PREF_FURY_DOWNLOADS = "fury_downloads";
        private String PREF_FURY_SOURCE = "fury_source";

        private Preference mWebsite;
        private Preference mFuryDownloads;
        private Preference mFurySource;
        public SettingsPreferenceFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.about_layout);

            PreferenceScreen prefSet = getPreferenceScreen();
            Activity activity = getActivity();

            final ContentResolver resolver = getActivity().getContentResolver();

            mWebsite = prefSet.findPreference(PREF_WEBSITE);
            mFuryDownloads = prefSet.findPreference(PREF_FURY_DOWNLOADS);
            mFurySource = prefSet.findPreference(PREF_FURY_SOURCE);

        }

        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            if (preference == mWebsite) {
                String url = "https://furydragons.github.io/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else if (preference == mFuryDownloads) {
                String url = "https://furydragons.github.io/downloads.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else if (preference == mFurySource) {
                String url = "https://github.com/FuryDragons";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else {
                return super.onPreferenceTreeClick(preferenceScreen, preference);
            }

            return false;
        }
    }
}
