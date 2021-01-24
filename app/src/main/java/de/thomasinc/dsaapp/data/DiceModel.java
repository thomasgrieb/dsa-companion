package de.thomasinc.dsaapp.data;

import java.util.HashMap;

public class DiceModel implements DsaModel{

    private HashMap<String, HashMap<String,Skill>> skillMap;
    private Character character;


    public DiceModel(Character character, HashMap<String, HashMap<String,Skill>> skillMap){
        this.character = character;
        this.skillMap = skillMap;
    }

    public

    public Character getCharacter() {
        return character;
    }

    public HashMap<String, HashMap<String, Skill>> getSkillMap() {
        return skillMap;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
