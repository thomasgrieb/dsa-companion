package de.thomasinc.dsaapp.data;

/**
 * Skill-Object class
 * Portrays an individual skill by saving its name as String and the formula to calculate it as
 * {@link Formula} object.
 */

public class Skill {

    private String name;
    private Formula formula;
    private int id;
    private String cat;


    public Skill(String name, int id, String cat, Formula formula) {
        this.formula = formula;
        this.name = name;
        this.id = id;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formula getFormula() {
        return formula;
    }

    public int getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }


    /**
     * @return name and complete formula in pretty print
     */
    public String print() {
        return name + ": " + formula.getFirst() + "-" + formula.getSecond() + "-" + formula.getThird();
    }
}
