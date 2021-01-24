package de.thomasinc.dsaapp.data;

/**
 * Manages rolls.
 * Every time a user rolls the dice on a skill, a new object of this class is created.
 * Intended for use with {@link DiceModel} only.
 */
public class Roll {

    private int first;
    private int second;
    private int third;
    private boolean firstCrit;
    private boolean secondCrit;
    private boolean thirdCrit;

    public Roll(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public



}
