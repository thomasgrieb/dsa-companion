package de.thomasinc.dsaapp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.thomasinc.dsaapp.data.character.Character;

public class DiceModel implements DsaModel{

    private final ArrayList<Skill> skillList;
    private final ArrayList<SkillCat> skillCatList;
    private final Character character;
    private SkillCat currentCat;
    private Skill currentSkill;


    public DiceModel(Character character, ArrayList<Skill> skillList,
                     ArrayList<SkillCat> skillCatList){
        this.character = character;
        this.skillList = skillList;
        this.skillCatList = skillCatList;
    }

    /**
     * Rolls the dice for currently selected skill
     * @return {@link RollResult} object
     */
    public RollResult roll(){
        //TODO: fix possible Nullpointer
        Formula formula = currentSkill.getFormula();

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

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public ArrayList<SkillCat> getSkillCatList() {
        return skillCatList;
    }

    public SkillCat getCurrentCat() {
        return currentCat;
    }

    public void setCurrentCat(SkillCat currentCat) {
        this.currentCat = currentCat;
    }

    public Skill getCurrentSkill() {
        return currentSkill;
    }

    public void setCurrentSkill(Skill currentSkill) {
        this.currentSkill = currentSkill;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
