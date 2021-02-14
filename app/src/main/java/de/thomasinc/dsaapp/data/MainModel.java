package de.thomasinc.dsaapp.data;

import java.util.Set;

public class MainModel implements DsaModel{

    private Set<String> profiles;
    private String currentProfile;

    public MainModel(Set<String> profiles){
        this.profiles = profiles;
    }

    public void setCurrentProfile(String currentProfile) {
        this.currentProfile = currentProfile;
    }

    public String getCurrentProfile() {
        return currentProfile;
    }

    public Set<String> getProfiles() {
        return profiles;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
