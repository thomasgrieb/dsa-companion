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


    public Skill() { }

    public static class SkillBuilder {

        private final String name;
        private final Formula formula;
        private int id;
        private final String cat;

        public SkillBuilder(String name, String cat, Formula formula){
            this.formula = formula;
            this.name = name;
            this.cat = cat;
            // TODO default for id necessary?
        }

        public SkillBuilder id(int id){
            this.id = id;
            return this;
        }

        public Skill build(){
            Skill s = new Skill();
            s.cat = this.cat;
            s.name = this.name;
            s.id = this.id;
            s.formula = this.formula;
            return s;
        }
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

    @Override
    public String toString() {
        return name;
    }
}
