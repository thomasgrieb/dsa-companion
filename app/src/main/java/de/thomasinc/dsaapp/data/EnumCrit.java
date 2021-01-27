package de.thomasinc.dsaapp.data;
/**
 * Currently useless enum for Crits
 **/

enum CritOld {
    FAILURE("red"), SUCCESS("green"), NONE("black");

    private final String colorcode;

    CritOld(String colorcode) {
        this.colorcode = colorcode;
    }

    public String getColorcode() {
        return colorcode;
    }
}





