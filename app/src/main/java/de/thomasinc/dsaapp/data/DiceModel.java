package de.thomasinc.dsaapp.data;

import java.util.HashMap;

public class DiceModel implements DsaModel{

    private HashMap<String, HashMap<String,Skill>> skillMap;


    public DiceModel(){}



    @Override
    public DsaModel getModel() {
        return this;
    }
}
