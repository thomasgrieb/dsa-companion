package de.thomasinc.dsaapp.ui.character;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

import de.thomasinc.dsaapp.data.character.AttributeModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.ConstantsGlobal;
import de.thomasinc.dsaapp.util.Json;

public class AttributePresenter implements DsaPresenter {

    private AttributeActivity view;
    private AttributeModel model;
    private SharedPreferences pref;


    public AttributePresenter(AttributeActivity view, Context context){
        this.pref = context.getSharedPreferences(ConstantsGlobal.PREFERENCES_FILE, 0);
        this.view = view;
        this.model = new AttributeModel(Json.readCharFromJson(context,
                fetchCurrentCharacterFromPref()));
    }

    /**
     * Calls {@link AttributeActivity#setEditTextValue} for every attribute in the map retrieved
     * from the current character.
     */
    public void setAttributeBoxes(){
        HashMap<String,Integer> attributes = model.getCharacter().charAttributesToMap();
        for(HashMap.Entry<String, Integer> pair : attributes.entrySet()){
            view.setEditTextValue(pair.getKey(),pair.getValue());
        }
    }

    /**
     * Middleman method.
     * @return true if empty, false if not
     */
    public boolean checkField(String text){
        return model.checkString(text);
    }

    /**
     * Tries to fetch the current profile saved in {@link SharedPreferences}.
     *
     * @return a set of profiles or default set
     */
    public String fetchCurrentCharacterFromPref() {
        return pref.getString(ConstantsGlobal.PREFERENCES_CURRENT_CHAR_KEY, "");
    }

    /**
     * Calls function from Util in order to write {@link Character} object to json.
     * Adds the character name to {@link SharedPreferences} file
     */
    public void remakeChar(Context context) {
        HashMap<String,Integer> attributes = model.getCharacter().charAttributesToMap();
        for(String key: attributes.keySet()){
            attributes.put(key, view.getEditTextValue(key));
        }
        model.buildChar(attributes);
        Json.writeCharToJson(context, model.getCharacter());
        Log.i("attrPres","Saved character " + model.getCharacter().getName() +
                " with new attributes: " + model.getCharacter().charAttributesToMap());
    }

    /**
     * Middleman method.
     * @return max value
     */
    public int getMaxAttributeFromModel(){
        return model.getAttributeMaxValue();
    }

    /**
     * Middleman method.
     * @return min value
     */
    public int getMinAttributeFromModel(){
        return model.getAttributeMinValue();
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
