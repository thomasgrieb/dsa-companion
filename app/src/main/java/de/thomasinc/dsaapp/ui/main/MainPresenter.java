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
    private static final String NO_CHARACTER_FOUND_MSG = "Bitte Profil erstellen";

    public MainPresenter(MainActivity view, Context context) {
        this.pref = context.getSharedPreferences(ConstantsGlobal.PREFERENCES_FILE, 0);
        this.view = view;
        this.model = new MainModel(fetchCharacterPref());
        //Ensures that character map file exists
        Json.checkCharacterMapFile(context);
    }

    /**
     * Sets the current character in the {@link SharedPreferences} and for the model if
     * the parameter prof is not equal to the no profile found message.
     *
     * @param character selected character
     */
    public void setCurrentCharacterPref(String character) {
        if(!character.equals(NO_CHARACTER_FOUND_MSG)){
            model.setCurrentCharacter(character);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(ConstantsGlobal.PREFERENCES_CURRENT_CHAR_KEY, character);
            editor.apply();
            view.setThrowButtonStatus(true);
        }
    }

    /**
     * Deactivates the dice throw button
     */
    public void noCharacterSelected() {
        view.setThrowButtonStatus(false);
    }

    /**
     * Tries to fetch the profiles saved in {@link SharedPreferences}.
     * If no profiles are found, a default set is defined.
     *
     * @return a set of profiles or default set
     */
    public Set<String> fetchCharacterPref() {
        Set<String> noProf = new HashSet<String>();
        noProf.add(NO_CHARACTER_FOUND_MSG);
        return pref.getStringSet(ConstantsGlobal.PREFERENCES_CHARACTER_LIST_KEY, noProf);
    }

    /**
     * Calls {@link #fetchCharacterPref()} and converts the result to a {@link String} array.
     * Also calls {@link #noCharacterSelected()} if the no character found message is at index 0 of
     * this array. This ensures the dice button is only activated if at least one character exists
     */
    public void fillCharactersDropdown() {
        String[] characterAr = new String[model.getCharacters().size()];
        characterAr = model.getCharacters().toArray(characterAr);
        if(characterAr[0].equals(NO_CHARACTER_FOUND_MSG)){
            noCharacterSelected();
        }
        view.fillCharactersDropdown(characterAr);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
