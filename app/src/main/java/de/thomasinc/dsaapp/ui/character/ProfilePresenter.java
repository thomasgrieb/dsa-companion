package de.thomasinc.dsaapp.ui.character;

import android.content.Context;
import android.content.SharedPreferences;

import de.thomasinc.dsaapp.data.character.ProfileModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.Util;

public class ProfilePresenter implements DsaPresenter {

    private ProfileActivity view;
    private ProfileModel model;
    private SharedPreferences pref;


    public ProfilePresenter(ProfileActivity view, Context context){
        this.view = view;
        this.pref = context.getSharedPreferences("ProfilePrefs", 0);
    }

    public void checkName(String name){
        if (!Util.checkIfEmpty(name)){
            if (model.validateName(name)){

                view.setConfBtnStatus(true);
            }
        }
    }

    @Override
    public void onCreate() { }

    @Override
    public void onDestroy() {
        view = null;
    }
}
