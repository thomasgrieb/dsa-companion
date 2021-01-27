package de.thomasinc.dsaapp.data;

import java.util.HashMap;

public class DiceModel implements DsaModel{

    private final HashMap<String, HashMap<String,Skill>> skillMap;
    private final Character character;
    private String currentCat ="";
    private String currentSkill ="";


    public DiceModel(Character character, HashMap<String, HashMap<String,Skill>> skillMap){
        this.character = character;
        this.skillMap = skillMap;
    }

    public void executeRoll(){
        Formula formula = skillMap.get(currentCat).get(currentSkill).getFormula();
        int value1 = character.get(formula.getFirst());
        int value2 = character.get(formula.getSecond());
        int value3 = character.get(formula.getThird());
    }

    public Character getCharacter() {
        return character;
    }

    public HashMap<String, HashMap<String, Skill>> getSkillMap() {
        return skillMap;
    }

    public String getCurrentCat() {
        return currentCat;
    }

    public void setCurrentCat(String currentCat) {
        this.currentCat = currentCat;
    }

    public String getCurrentSkill() {
        return currentSkill;
    }

    public void setCurrentSkill(String currentSkill) {
        this.currentSkill = currentSkill;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
