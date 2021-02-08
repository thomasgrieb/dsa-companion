package de.thomasinc.dsaapp.data;

import java.util.HashMap;
import java.util.Random;

public class DiceModel implements DsaModel{

    private final HashMap<String, HashMap<String,Skill>> skillMap;
    private final Character character;
    private String currentCat;
    private String currentSkill;


    public DiceModel(Character character, HashMap<String, HashMap<String,Skill>> skillMap){
        this.character = character;
        this.skillMap = skillMap;
    }

    /**
     * Rolls the dice for currently selected skill
     * @return {@link RollResult} object
     */
    public RollResult roll(){
        //TODO: fix possible Nullpointer
        Formula formula = skillMap.get(currentCat).get(currentSkill).getFormula();

        //TODO: if available, get char skill value and use as bonus
        int bonus = 0;

        int first = character.get(formula.getFirst());
        int second = character.get(formula.getSecond());
        int third = character.get(formula.getThird());

        Random ran = new Random();

        int diff = 0;

        int dice1 = ran.nextInt(20)+1;
        diff += (first < dice1) ? first-dice1 : 0;

        int dice2 = ran.nextInt(20)+1;
        diff += (second < dice2) ? second-dice2: 0;

        int dice3 = ran.nextInt(20)+1;
        diff += (third < dice3) ? third-dice3 : 0;

        return new RollResult(dice1,dice2,dice3,diff, bonus);
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
