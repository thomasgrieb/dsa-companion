package de.thomasinc.dsaapp.data;

import java.util.Set;

public class MainModel implements DsaModel{

    private final Set<String> characters;
    private String currentCharacter;

    public MainModel(Set<String> characters){
        this.characters = characters;
    }

    public void setCurrentCharacter(String currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public String getCurrentCharacter() {
        return currentCharacter;
    }

    public Set<String> getCharacters() {
        return characters;
    }

    @Override
    public DsaModel getModel() {
        return this;
    }
}
