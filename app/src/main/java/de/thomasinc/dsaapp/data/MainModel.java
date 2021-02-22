package de.thomasinc.dsaapp.data;

import java.util.Set;

public class MainModel implements DsaModel{

    private Set<String> profiles;
    private String currentProfile;

    public MainModel(Set<String> profiles){
        this.profiles = profiles;
    }

    public void setCurrentCharacter(String currentProfile) {
        this.currentProfile = currentProfile;
    }

    public String getCurrentProfile() {
        return currentProfile;
    }

    public Set<String> getCharacters() {
        return profiles;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
