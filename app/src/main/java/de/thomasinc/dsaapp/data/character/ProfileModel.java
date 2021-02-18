package de.thomasinc.dsaapp.data.character;

import de.thomasinc.dsaapp.data.DsaModel;

public class ProfileModel implements DsaModel {

    private String name;

    public ProfileModel() {
    }

    /**
     * Checks the name for prohibited characters. Also sets name if name is valid
     *
     * @param name characters name
     * @return true if name is valid, false if invalid
     */
    public boolean validateName(String name) {
        String regex = "^[\\wäöüÄÖÜ+-.,; ]*$";
        if (name.matches(regex)) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    /**
     * creates {@link Character} object using name set in validate name
     * Needs to be called after name validation!
     * //TODO: catch error if called before
     *
     * @return
     */
    public Character buildCharNameOnly() {
        return new Character.CharBuilder(name).build();
    }

    public String getName() {
        return name;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
