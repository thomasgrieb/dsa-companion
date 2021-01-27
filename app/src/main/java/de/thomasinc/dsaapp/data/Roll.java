package de.thomasinc.dsaapp.data;

import java.util.Random;

import de.thomasinc.dsaapp.util.Util;

/**
 * Manages rolls.
 * Every time a user rolls the dice on a skill, a new object of this class is created.
 * Intended for use with {@link DiceModel} only.
 */
public class Roll {

    private int first;
    private int second;
    private int third;

    private final Random ran = new Random();


    public Roll(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public RollResult roll(){
        RollResult res = null;

        int over = 0;

        int dice1 = ran.nextInt(20)+1;
        over += largerThan(dice1,first);

        int dice2 = ran.nextInt(20)+1;
        over += largerThan(dice2,second);

        int dice3 = ran.nextInt(20)+1;
        over += largerThan(dice3,third);

        return new RollResult(first,second,third,over);
    }

    public static int largerThan(int x,int y){
        int z = 0;
        if(x > y){
            z = x-y;
        }
        return z;
    }



}
