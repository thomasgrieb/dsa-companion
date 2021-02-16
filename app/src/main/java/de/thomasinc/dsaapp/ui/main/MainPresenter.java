package de.thomasinc.dsaapp.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import de.thomasinc.dsaapp.data.MainModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;

public class MainPresenter implements DsaPresenter {

    private MainActivity view;
    private MainModel model;
    private SharedPreferences pref;

    public MainPresenter(MainActivity view, Context context){
        this.pref = context.getSharedPreferences("ProfilePrefs", 0);
        this.view = view;
        this.model = new MainModel(fetchProfilePref());
    }

    /**
     * Sets the current profile in the {@link SharedPreferences}
     * @param prof selected profile
     */
    public void setCurrentProfilePref(String prof){
        SharedPreferences.Editor editor = this.pref.edit();
        model.setCurrentProfile(prof);
        editor.apply();
        view.setThrowButtonStatus(true);
    }

    public void noProfileSelected(){
        view.setThrowButtonStatus(false);
    }

    /**
     * Tries to fetch the profiles saved in {@link SharedPreferences}.
     * If no profiles are found, a default set is defined
     * @return a set of profiles or default set
     */
    public Set<String> fetchProfilePref(){
        Set<String> noProf = new HashSet<String>();
        noProf.add("Bitte Profil erstellen");
        return pref.getStringSet("profiles",noProf);
    }

    public void fillProfileDropdown(){
        String[] profileAr = new String[model.getProfiles().size()];
        profileAr = model.getProfiles().toArray(profileAr);
        view.fillProfileDropdown(profileAr);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
