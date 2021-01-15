package de.thomasinc.dsaapp;

import java.util.HashMap;

/**
 * Defines the Character object
 * Every character object has three groups of main attributes:
 *  one unique name, nine character attributes and an arbitrary number of skills
 *  The name is represented by a {@link String}
 *  The character attributes (also called abilities) are represented by nine {@link Integer}s
 *  The skills represented by a {@link HashMap}
 * Uses the Builder Pattern due to number of parameters
 * TODO: Consider combining abilities into HashMap?
 */
public class Character {

    private int mu;
    private int kl;
    private int in;
    private int ch;
    private int ff;
    private int ge;
    private int ko;
    private int kk;
    private final HashMap<String, Integer> abilityMap = new HashMap<>();
    private HashMap<Skill, Integer> skillMap = new HashMap<>();
    private String name;

    /*
    public Character(String name, int mu, int kl, int in, int ch, int ff, int ge, int ko, int kk,
                     HashMap<Skill, Integer> skills) {
        this.name = name;
        this.mu = mu;
        this.kl = kl;
        this.in = in;
        this.ch = ch;
        this.ff = ff;
        this.ge = ge;
        this.ko = ko;
        this.kk = kk;
        this.abilityMap.put("MU", mu);
        this.abilityMap.put("KL", kl);
        this.abilityMap.put("IN", in);
        this.abilityMap.put("CH", ch);
        this.abilityMap.put("FF", ff);
        this.abilityMap.put("GE", ge);
        this.abilityMap.put("KO", ko);
        this.abilityMap.put("KK", kk);
        this.skillMap = skills;
    }
     */

    /**
     * Empty Constructor
     */
    private Character() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMu() {
        return mu;
    }

    public void setMu(int mu) {
        this.mu = mu;
    }

    public int getKl() {
        return kl;
    }

    public void setKl(int kl) {
        this.kl = kl;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public int getFf() {
        return ff;
    }

    public void setFf(int ff) {
        this.ff = ff;
    }

    public int getGe() {
        return ge;
    }

    public void setGe(int ge) {
        this.ge = ge;
    }

    public int getKo() {
        return ko;
    }

    public void setKo(int ko) {
        this.ko = ko;
    }

    public int getKk() {
        return kk;
    }

    public void setKk(int kk) {
        this.kk = kk;
    }

    public HashMap<Skill, Integer> getSkillmap() {
        return skillMap;
    }

    public void setSkillMap(HashMap<Skill, Integer> skillMap) {
        this.skillMap = skillMap;
    }

    /**
     * Implements find and return method for the abilityMap
     *
     * @param s
     * @return ability score or 0 if nonexistant
     */
    public int get(String s) {
        if (abilityMap.containsKey(s)) {
            return abilityMap.get(s);
        } else {
            return 0;
        }
    }

    /**
     * creates an array representation of the ability scores
     *
     * @return {@link Integer} array of ability scores
     */
    public int[] charBaseValuesToArray() {
        int[] ar = {mu, kl, in, ch, ff, ge, ko, kk};
        return ar;
    }

    /**
     * Builder Pattern
     */
    public static class CharBuilder {
        private int mu;
        private int kl;
        private int in;
        private int ch;
        private int ff;
        private int ge;
        private int ko;
        private int kk;
        private final HashMap<String, Integer> abilityMap = new HashMap<>();
        private HashMap<Skill, Integer> skillMap = new HashMap<>();
        private final String name;

        public CharBuilder(String name) {
            this.name = name;
            this.mu = 8;
            this.kl = 8;
            this.in = 8;
            this.ch = 8;
            this.ff = 8;
            this.ge = 8;
            this.ko = 8;
            this.kk = 8;
        }

        public CharBuilder mu(int mu) {
            this.mu = mu;
            return this;
        }

        public CharBuilder kl(int kl) {
            this.kl = kl;
            return this;
        }

        public CharBuilder in(int in) {
            this.in = in;
            return this;
        }

        public CharBuilder ch(int ch) {
            this.ch = ch;
            return this;
        }

        public CharBuilder ff(int ff) {
            this.ff = ff;
            return this;
        }

        public CharBuilder ge(int ge) {
            this.ge = ge;
            return this;
        }

        public CharBuilder ko(int ko) {
            this.ko = ko;
            return this;
        }

        public CharBuilder kk(int kk) {
            this.kk = kk;
            return this;
        }

        public CharBuilder skillMap(HashMap<Skill, Integer> skillMap) {
            this.skillMap = skillMap;
            return this;
        }

        public Character build() {
            Character c = new Character();
            c.name = this.name;
            c.mu = this.mu;
            c.kl = this.kl;
            c.in = this.in;
            c.ch = this.ch;
            c.ff = this.ff;
            c.ge = this.ge;
            c.ko = this.ko;
            c.kk = this.kk;
            c.skillMap = this.skillMap;
            c.abilityMap.put("MU", this.mu);
            c.abilityMap.put("KL", this.kl);
            c.abilityMap.put("IN", this.in);
            c.abilityMap.put("CH", this.ch);
            c.abilityMap.put("FF", this.ff);
            c.abilityMap.put("GE", this.ge);
            c.abilityMap.put("KO", this.ko);
            c.abilityMap.put("KK", this.kk);
            return c;

        }

    }
}
