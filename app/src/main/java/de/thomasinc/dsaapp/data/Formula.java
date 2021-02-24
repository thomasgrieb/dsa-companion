package de.thomasinc.dsaapp.data;

public class Formula {
    private String first;
    private String second;
    private String third;

    public Formula(String formula) {
        String[] f = formula.split("-");
        this.first = f[0];
        this.second = f[1];
        this.third = f[2];
    }

    public String getFirst() {
        return first;
    }


    public String getSecond() {
        return second;
    }


    public String getThird() {
        return third;
    }


    public String print() {
        return first + " - " + second + " - " + third;
    }
}
