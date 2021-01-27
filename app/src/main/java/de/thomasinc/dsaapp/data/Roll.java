package de.thomasinc.dsaapp.data;

import java.util.Random;

import de.thomasinc.dsaapp.util.Util;

/**
 * Manages rolls.
 * Every time a user rolls the dice on a skill, a new object of this class is created.
 * Intended for use with {@link DiceModel} only.
 */
public class Roll {


    private final Random ran = new Random();


    public Roll(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }


    public void roll(){
        int over = 0;
        String comp;

        int dice1 = ran.nextInt(20)+1;
        over+= Util.largerThan(dice1,value1);

        int dice2 = ran.nextInt(20)+1;
        over+=Util.largerThan(dice2,value2);

        int dice3 = ran.nextInt(20)+1;
        over+=Util.largerThan(dice3,value3);


    }



}
