package de.thomasinc.dsaapp.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.data.SkillCat;
import de.thomasinc.dsaapp.data.character.Character;

/**
 * Utility helper class that implements various methods needed in several places of the app
 */

public class Util {


    /**
     * Finds all skills of a specific cat in the provided list.
     *
     * @param skillList list of all skills
     * @param cat       skill category to search for
     * @return list of all skills of category cat
     */
    public static ArrayList<Skill> getSkillsOfCat(ArrayList<Skill> skillList, SkillCat cat) {
        ArrayList<Skill> skillListOfCat = new ArrayList<>();
        for (Skill skill : skillList) {
            if (skill.getCat().equals(cat.getAbbr())) {
                skillListOfCat.add(skill);
            }
        }
        return skillListOfCat;
    }

    /**
     * Creates a {@link HashMap} containing {@link Skill}:{@link Integer}(0) pairs for every
     *  existing skill in skill.json in order to initialize an empty characters skill sheet.
     * Uses the {@link Json#makeSkillsFromJson(Context)} method to get a list of categories, loops over the
     *  result and uses the {@link #getSkillsOfCat(Context, String)} method to create a
     *  {@link Skill} array of all skills in that category. Lastly, the list is appended to a
     *  predefined {@link HashMap} and each key is assigned the value 0.
     * The method is supposed to be used after creating a new profile through the profile creating
     *  functionality in order to complete the creation of the corresponding {@link Character}
     *  object.
     * @param context
     * @return {@link HashMap} of {@link Skill}:{@link Integer} pairs
     */
    /*
    public static HashMap<Skill,Integer> initializeSkillValueMap(Context context){
        HashMap<Skill,Integer> skillValueMap = new HashMap<>();
        try{
            JSONObject obj = new JSONObject((Json.readSkillsJson(context)));
            Iterator<String> itCat = obj.keys();
            Skill[] skillsOfCat;
            while (itCat.hasNext()){
                skillsOfCat = getSkillsOfCat(context,itCat.next()).values().toArray(new Skill[0]);
                for (int i = 0; i< skillsOfCat.length;i++){
                    skillValueMap.put(skillsOfCat[i], 0);
                }
            }
        } catch (JSONException er){
            er.printStackTrace();
        }
        return skillValueMap;
    }

     */

    /**
     * Function for checking whether a text is empty or not
     *
     * @param text text to check
     * @return false if not empty, true if empty
     */
    public static boolean checkIfEmpty(String text) {
        return text.trim().length() == 0;
    }

    /**
     * Normalizes text by: \
     * trimming excess whitespace and replacing spaces with underscores \
     * setting to lowercase \
     * replacing umlauts \
     * removing punctuation
     *
     * @param text any {@link String}
     * @return a normalized version that can be used as filename etc.
     */
    public static String normalizeString(String text) {
        String textNoWhitespace = text.trim().replaceAll(" ", "_");
        String textLower = textNoWhitespace.toLowerCase();
        String textAscii = textLower
                .replaceAll("ä", "ae")
                .replaceAll("ö", "oe")
                .replaceAll("ü", "ue");
        return textAscii.replaceAll("[+-.,;]", "");
    }

}
