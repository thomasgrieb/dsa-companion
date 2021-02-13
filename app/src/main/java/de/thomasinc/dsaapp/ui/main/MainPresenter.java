package de.thomasinc.dsaapp.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import de.thomasinc.dsaapp.data.MainModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;

public class MainPresenter implements DsaPresenter {

    private MainActivity view;
    private MainModel model;
    private SharedPreferences pref;

    public MainPresenter(MainActivity view, Context context){
       this.view = view;
       this.model = new MainModel();
       this.pref = context.getSharedPreferences("ProfilePrefs", 0);
    }

    public void setCurrentProfilePref(String prof){
        System.out.println(prof);
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString("current", prof);
        editor.apply();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
