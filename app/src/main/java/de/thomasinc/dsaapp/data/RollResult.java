package de.thomasinc.dsaapp.data;


/**
 * Simple object for saving the individual results of a roll performed by {@link Roll}
 */
public class RollResult {

    /**
     * Crit Enum
     */
    public enum Crit {
        Failure,
        Success
    }

    private int first;
    private int second;
    private int third;
    private Crit firstCrit;
    private Crit secondCrit;
    private Crit thirdCrit;
    private int compensate;

    public Roll(int first, int second)

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public boolean isFirstCrit() {
        return firstCrit;
    }

    public void setFirstCrit(boolean firstCrit) {
        this.firstCrit = firstCrit;
    }

    public boolean isSecondCrit() {
        return secondCrit;
    }

    public void setSecondCrit(boolean secondCrit) {
        this.secondCrit = secondCrit;
    }

    public boolean isThirdCrit() {
        return thirdCrit;
    }

    public void setThirdCrit(boolean thirdCrit) {
        this.thirdCrit = thirdCrit;
    }

    public int getCompensate() {
        return compensate;
    }

    public void setCompensate(int compensate) {
        this.compensate = compensate;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }
}
