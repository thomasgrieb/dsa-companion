package de.thomasinc.dsaapp.data.character;

import java.util.HashMap;

import de.thomasinc.dsaapp.data.DsaModel;
import de.thomasinc.dsaapp.util.Util;

public class AttributeModel implements DsaModel {

    private Character character;
    private final static int ATTRIBUTE_MAX_VALUE = 19;
    private final static int ATTRIBUTE_MIN_VALUE = 0;


    public AttributeModel(Character character){
        this.character = character;
    }

    public void buildChar(HashMap<String,Integer> attrMap) {
        character = new Character.CharBuilder(character.getName())
                .mu(attrMap.get("MU"))
                .kl(attrMap.get("KL"))
                .in(attrMap.get("IN"))
                .ch(attrMap.get("CH"))
                .ff(attrMap.get("FF"))
                .ge(attrMap.get("GE"))
                .ko(attrMap.get("KO"))
                .kk(attrMap.get("KK"))
                .build();
    }


    public boolean checkString(String text) {
        return Util.checkIfEmpty(text);
    }

    public Character getCharacter() {
        return character;
    }

    public int getAttributeMaxValue() {
        return ATTRIBUTE_MAX_VALUE;
    }

    public int getAttributeMinValue() {
        return ATTRIBUTE_MIN_VALUE;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
