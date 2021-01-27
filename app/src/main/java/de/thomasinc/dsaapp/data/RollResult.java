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

    public RollResult(int first, int second, int third, int compensate) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.firstCrit = checkForCrit(first);
        this.secondCrit = checkForCrit(second);
        this.thirdCrit = checkForCrit(third);
        this.compensate = compensate;
    }

    private Crit checkForCrit(int x){
        if(x==20){
            return Crit.Failure;
        }else if (x==1){
            return Crit.Success;
        }
        return null;
    }

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

    public Crit isFirstCrit() {
        return firstCrit;
    }

    public void setFirstCrit(Crit firstCrit) {
        this.firstCrit = firstCrit;
    }

    public Crit isSecondCrit() {
        return secondCrit;
    }

    public void setSecondCrit(Crit secondCrit) {
        this.secondCrit = secondCrit;
    }

    public Crit isThirdCrit() {
        return thirdCrit;
    }

    public void setThirdCrit(Crit thirdCrit) {
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
