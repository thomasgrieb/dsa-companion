package de.thomasinc.dsaapp.ui.character;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import de.thomasinc.dsaapp.data.character.ProfileModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.ConstantsGlobal;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.Util;

public class ProfilePresenter implements DsaPresenter {

    private ProfileActivity view;
    private ProfileModel model;
    private SharedPreferences pref;
    private Context context;


    public ProfilePresenter(ProfileActivity view, Context context) {
        this.view = view;
        this.pref = context.getSharedPreferences(ConstantsGlobal.PREFERENCES_FILE, 0);
        this.model = new ProfileModel();
        this.context = context;
    }

    /**
     * Calls name validation from model and enables confirmation button if name is valid,
     * otherwise calls error-function from view.
     *
     * @param name characters name
     */
    public void checkName(String name) {
        //TODO Work on a better implementation of the warning.
        // Current implementation causes alert dialog to appear immediately after typing a
        // prohibited sign and, if the user continues typing, displays the dialog after every
        // new character.
        // Possible solutions:
        //      -Dialog after clicking on confirm (Button is active but doesn't cause
        //          activity-change)
        //      -No Dialog, but permanent warning on screen (inactive button if prohibited sign is
        //          present)
        if (!Util.checkIfEmpty(name)) {
            if (model.validateName(name)) {
                view.setConfBtnStatus(true);
            } else {
                view.onError("Es sind nur Buchstaben, Zahlen, die Sonderzeichen " +
                        "'ä', 'ö', 'ü', 'Ä', 'Ö', 'Ü', '+', '-', '_', '.', ',', ';'" +
                        " und Leertaste im Charakternamen erlaubt.");
            }
        }
    }

    /**
     * calls function from Util in order to write {@link Character} object to json
     * adds the character name to {@link SharedPreferences} file
     */
    public void createProfile() {
        final String profilesKey = ConstantsGlobal.PREFERENCES_PROFILE_LIST_KEY;
        Json.writeCharToJson(context, model.buildCharNameOnly());
        Set<String> prof = new HashSet<String>(
                pref.getStringSet(profilesKey, new HashSet<String>()));
        prof.add(model.getName());
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(profilesKey, prof).apply();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
