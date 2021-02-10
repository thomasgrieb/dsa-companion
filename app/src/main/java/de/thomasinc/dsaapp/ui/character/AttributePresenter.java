package de.thomasinc.dsaapp.ui.character;

import android.content.Context;

import de.thomasinc.dsaapp.data.character.AttributeModel;
import de.thomasinc.dsaapp.data.character.Character;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.Util;

public class AttributePresenter implements DsaPresenter {

    private final AttributeActivity view = new AttributeActivity();
    //private final AttributeModel model = new AttributeModel();

    public AttributePresenter(AttributeActivity view, Context context){
        //this.model = new AttributeModel();
    }

    /*
    private retrieveCharacter
    if(Util.checkIfCharExists(context)){
        Character c = Json.readCharFromJson(context);
        int[] cAr = c.charBaseValuesToArray();

        for(int i=0; i<8;i++) {
            System.out.println(cAr[i]);
            textArray[i].setText(String.valueOf(cAr[i]));
        }
    }
    */

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
