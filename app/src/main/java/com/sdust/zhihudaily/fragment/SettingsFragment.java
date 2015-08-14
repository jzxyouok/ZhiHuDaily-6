package com.sdust.zhihudaily.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.sdust.zhihudaily.Constants;
import com.sdust.zhihudaily.R;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private static final String PREF_VERSION = "pref_version";
    private static final String PREF_ABOUT_ME = "pref_about";

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        findPreference(PREF_VERSION).setOnPreferenceClickListener(this);
        findPreference(PREF_ABOUT_ME).setOnPreferenceClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // setHasOptionsMenu(true);
        // Inflate the layout for this fragment

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_story, menu);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals(PREF_ABOUT_ME)) {
            showDialog(false);
        } else if (preference.getKey().equals(PREF_VERSION)) {
            showDialog(true);
        }
        return false;
    }

    private void showDialog(boolean isVersion) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_version);

        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);

        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        String applicationName = getResources().getString(R.string.app_name);
        String applicationVersion = getResources().getString(R.string.app_version);
        if (isVersion) {
            String data = String.format("%1$s\r\nV%2$s", applicationName, applicationVersion);

            textView.setText(data);
        } else {
            String title = applicationName += "<br/>";
            String subTitleTemp = getResources().getString(R.string.app_sub_name);
            String subTitle = subTitleTemp += "<br/>";
            String author = new StringBuilder().append("@").append(getResources().getString(R.string.app_author)).toString();
            String githubUrl = new StringBuilder().append("<a href='")
                    .append(Constants.GITGUB_PROJECT)
                    .append("'>")
                    .append(Constants.GITGUB_PROJECT)
                    .append("</a>")
                    .append("<br/>")
                    .toString();
            String data = String.format("%1$s%2$s%3$sby %4$s",
                    title,
                    subTitle,
                    githubUrl,
                    author);

            CharSequence charSequence = Html.fromHtml(data);

            textView.setText(charSequence);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        dialog.show();
    }


}
