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
    private final int quality;
    private final int compensate;

    public RollResult(int first, int second, int third, int diff, int bonus) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.firstCrit = checkForCrit(first);
        this.secondCrit = checkForCrit(second);
        this.thirdCrit = checkForCrit(third);
        this.diff = diff+bonus;
        this.compensate = this.diff < 0 ? Math.abs(this.diff) : 0;
        this.quality = this.diff < 0 ? 0 : ((bonus-this.compensate)+2)/3;
    }

    private Crit checkForCrit(int x){
        if(x==20){
            return Crit.Failure;
        }else if (x==1){
            return Crit.Success;
        }
        return null;
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
