package de.thomasinc.dsaapp.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import de.thomasinc.dsaapp.data.MainModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.ConstantsGlobal;
import de.thomasinc.dsaapp.util.Json;

public class MainPresenter implements DsaPresenter {

    private MainActivity view;
    private MainModel model;
    private SharedPreferences pref;
    private static final String NO_PROFILE_FOUND_MSG = "Bitte Profil erstellen";

    public MainPresenter(MainActivity view, Context context) {
        this.pref = context.getSharedPreferences(ConstantsGlobal.PREFERENCES_FILE, 0);
        this.view = view;
        this.model = new MainModel(fetchProfilePref());
        //Ensures that profile map file exists
        Json.checkProfileMapFile(context);
    }

    /**
     * Sets the current profile in the {@link SharedPreferences} and for the model if
     * the parameter prof is not equal to the no profile found message.
     *
     * @param prof selected profile
     */
    public void setCurrentProfilePref(String prof) {
        if(!prof.equals(NO_PROFILE_FOUND_MSG)){
            model.setCurrentProfile(prof);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(ConstantsGlobal.PREFERENCES_CURRENT_PROF_KEY, prof);
            editor.apply();
            view.setThrowButtonStatus(true);
        }
    }

    /**
     * Deactivates the dice throw button
     */
    public void noProfileSelected() {
        view.setThrowButtonStatus(false);
    }

    /**
     * Tries to fetch the profiles saved in {@link SharedPreferences}.
     * If no profiles are found, a default set is defined.
     *
     * @return a set of profiles or default set
     */
    public Set<String> fetchProfilePref() {
        Set<String> noProf = new HashSet<String>();
        noProf.add(NO_PROFILE_FOUND_MSG);
        return pref.getStringSet(ConstantsGlobal.PREFERENCES_PROFILE_LIST_KEY, noProf);
    }

    /**
     * Calls {@link #fetchProfilePref()} and converts the result to a {@link String} array.
     * Also calls {@link #noProfileSelected()} if the no profile found message is at index 0 of
     * this array. This ensures the dice button is only activated if at least one profile exists
     */
    public void fillProfileDropdown() {
        String[] profileAr = new String[model.getProfiles().size()];
        profileAr = model.getProfiles().toArray(profileAr);
        if(profileAr[0].equals(NO_PROFILE_FOUND_MSG)){
            noProfileSelected();
        }
        view.fillProfileDropdown(profileAr);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
