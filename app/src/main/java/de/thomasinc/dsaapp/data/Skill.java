package de.thomasinc.dsaapp.data;

/**
 * Skill-Object class
 * Portrays an individual skill by saving its name as String and the formula to calculate it as
 *  {@link Formula} object.
 */

public class Skill {

    private String name;
    private Formula formula;

    /**
     * Constructer
     * @param name name of the skill as String
     * @param formula {@link Formula} object that implements the formula required for calculation
     */
    public Skill(String name, Formula formula){
        this.formula = formula;
        this.name = name;
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

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    /**
     * @return name and complete formula in pretty print
     */
    public String print(){
        return name+": "+formula.getFirst()+"-"+formula.getSecond()+"-"+formula.getThird();
    }
}
