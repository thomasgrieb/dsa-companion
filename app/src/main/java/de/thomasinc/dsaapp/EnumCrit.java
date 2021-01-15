package de.thomasinc.dsaapp;
/**
 * Currently useless enum for Crits
 **/

enum Crit {
    FAILURE("red"), SUCCESS("green"), NONE("black");

    private final String colorcode;

    Crit(String colorcode) {
        this.colorcode = colorcode;
    }

    public String getColorcode() {
        return colorcode;
    }
}





