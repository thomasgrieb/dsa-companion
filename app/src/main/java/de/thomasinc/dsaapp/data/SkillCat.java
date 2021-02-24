package de.thomasinc.dsaapp.data;

public class SkillCat {

    private final String name;
    private final String abbr;

    public SkillCat(String name, String abbr){
        this.name = name;
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    @Override
    public String toString() {
        return name;
    }
}
