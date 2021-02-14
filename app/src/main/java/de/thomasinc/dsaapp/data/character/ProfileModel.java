package de.thomasinc.dsaapp.data.character;

import de.thomasinc.dsaapp.data.DsaModel;

public class ProfileModel implements DsaModel {

    private final String fileEnding = ".json";
    private String name;

    public ProfileModel(){

    }

    public boolean validateName(String name){
        String regex = "^[\\wäöüÄÖÜ+-_.,; ]*$";
        if (name.matches(regex)){
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
