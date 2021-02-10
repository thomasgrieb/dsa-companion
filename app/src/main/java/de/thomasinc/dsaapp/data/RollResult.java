package de.thomasinc.dsaapp.data;


/**
 * Simple object for saving the individual results of a roll
 * Automatically calculates crits and quality upon creation
 */
public class RollResult {

    /**
     * Crit Enum
     */
    public enum Crit {
        Failure,
        Success
    }

    private final int first;
    private final int second;
    private final int third;
    private final Crit firstCrit;
    private final Crit secondCrit;
    private final Crit thirdCrit;
    private final int diff;
    private int quality;
    private int compensate;

    public RollResult(int first, int second, int third, int diff, int bonus) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.firstCrit = checkForCrit(first);
        this.secondCrit = checkForCrit(second);
        this.thirdCrit = checkForCrit(third);
        this.diff = diff;
        calculateCompQual(bonus);
        //this.compensate = this.diff < 0 ? Math.abs(this.diff) : 0;
        //this.quality = this.diff < 0 ? 0 : ((bonus-this.compensate)+2)/3;
    }

    /**
     * internal function
     * checks if result of dice roll is a crit (20 or 1)
     * @param x dice result to be checked
     * @return {@link Crit} enum if true, else null
     */
    private Crit checkForCrit(int x){
        if(x==20){
            return Crit.Failure;
        }else if (x==1){
            return Crit.Success;
        }
        return null;
    }

    /**
     * internal function
     * calculates compensation and quality for every object of this class
     * @param bonus a characters skill value
     */
    private void calculateCompQual(int bonus) {
        int diff_abs = Math.abs(this.diff);
        if (diff_abs > bonus) {
            this.compensate = diff_abs - bonus;
            this.quality = 0;
        } else {
            this.compensate = 0;
            this.quality = (bonus - diff_abs == 0) ? 1 : (bonus - diff_abs + 2) / 3;
        }
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }

    public Crit isFirstCrit() {
        return firstCrit;
    }

    public Crit isSecondCrit() {
        return secondCrit;
    }

    public Crit isThirdCrit() {
        return thirdCrit;
    }

    public int getQuality() {
        return quality;
    }

    public int getCompensate() {
        return compensate;
    }
}
