package de.thomasinc.dsaapp.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import de.thomasinc.dsaapp.data.Formula;
import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.data.SkillCat;
import de.thomasinc.dsaapp.data.character.Character;

/**
 * Utility class that provides functions for dealing with json-files
 */

public class Json {

    // json file ending
    private static final String FILE_ENDING_JSON = ".json";

    // filename of profile map file
    private static final String PROFILE_MAP_FILENAME = "profilemap" + FILE_ENDING_JSON;

    // key for retrieving name from character file
    private static final String CF_KEY_CHARACTER_NAME = "Name";
    // key for retrieving attributes from character file
    private static final String CF_KEY_CHARACTER_ATTRIBUTE = "Attributes";

    // filename of skill file
    private static final String SKILL_FILENAME = "skills" + FILE_ENDING_JSON;
    // keys for retrieving skill details
    private static final String SF_SKILL_ID = "id";
    private static final String SF_SKILL_NAME = "name";
    private static final String SF_SKILL_CAT = "cat";
    private static final String SF_SKILL_FORMULA = "formula";

    // filename of skill categories file
    private static final String SKILL_CATS_FILENAME = "skill-categories" + FILE_ENDING_JSON;

    /**
     * Reads the skills from the json file saved in the apps' assets folder
     *
     * @param context application context needed for filepath
     * @return map of skill id : skill object
     */
    public static ArrayList<Skill> makeSkillsFromJson(Context context) {
        ArrayList<Skill> skills = new ArrayList<>();
        String json = readJsonFromAssets(context, SKILL_FILENAME);
        try {
            assert json != null;
            JSONArray skillArr = new JSONArray(json);
            for (int i = 0; i < skillArr.length(); i++) {
                JSONObject skillObj = skillArr.getJSONObject(i);
                skills.add(new Skill.SkillBuilder(
                                skillObj.getString(SF_SKILL_NAME),
                                skillObj.getString(SF_SKILL_CAT),
                                new Formula(skillObj.getString(SF_SKILL_FORMULA))
                        )
                                .id(skillObj.getInt(SF_SKILL_ID))
                                .build()
                );
            }
            Log.i("json", "Successfully created skill list.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return skills;
    }

    /**
     * Internal function.
     * Reads any file from internal file folder.
     *
     * @param context  application context
     * @param filename name of file
     * @return single {@link String} containing the entire file
     */
    private static String readJsonFromAssets(Context context, String filename) {
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (json != null) {
            Log.i("json", "Successfully read file " + filename + ".");
        } else {
            Log.e("json", "Warning: File " + filename + " is empty or doesn't exist!");
        }
        return json.toString();
    }

    /**
     * Reads the skill categories from the json file saved in the apps' assets folder
     *
     * @param context application context
     * @return list of skill categories
     */
    public static ArrayList<SkillCat> makeCatsFromJson(Context context) {
        String json = readJsonFromAssets(context, SKILL_CATS_FILENAME);
        System.out.println("jsonraw " + json);
        ArrayList<SkillCat> catList = new ArrayList<>();
        try {
            assert json != null;
            JSONObject catsObj = new JSONObject(json);
            Iterator<String> it = catsObj.keys();
            while (it.hasNext()) {
                String key = it.next();
                catList.add(new SkillCat(catsObj.getString(key), key));
            }
            Log.i("json", "Successfully created skill category list.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return catList;
    }


    /**
     * Checks whether the profile map file exists.
     * If it doesn't exist, it's created here.
     * The function is intended for calling right after starting the app
     * (currently in the constructor of {@link de.thomasinc.dsaapp.ui.main.MainPresenter}.
     *
     * @param context applications context
     */
    public static void checkCharacterMapFile(Context context) {
        File profileFile = new File(context.getFilesDir(), PROFILE_MAP_FILENAME);
        if (!profileFile.exists()) {
            try {
                profileFile.createNewFile();
                Log.i("json", "Created profilemap file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("json", "Profilemap file found.");

        }
    }

    /**
     * Retrieves the contents of the profile map file
     *
     * @param context application context
     * @return profile map
     */
    public static JSONObject readProfileMapFile(Context context) {
        JSONObject jobjMap = new JSONObject();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    context.openFileInput(PROFILE_MAP_FILENAME), StandardCharsets.UTF_8));
            String json = reader.readLine();
            if (json != null) {
                try {
                    jobjMap = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobjMap;
    }

    /**
     * Retrieves a specific characters filepath using readProfileMapFile
     *
     * @param context application context
     * @param name    characters name
     * @return the corresponding filename
     */
    public static String getCharFilename(Context context, String name) {
        String filename = "";
        try {
            JSONObject jobj = readProfileMapFile(context);
            filename = jobj.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filename;
    }

    /**
     * Reads existing charfile (json) from the  appropriate file folder and creates corresponding
     * {@link Character} object.
     *
     * @param context application context needed for filepath
     * @return {@link Character} object with the saved attributes
     */
    public static Character readCharFromJson(Context context, String name) {
        String filename = getCharFilename(context, name);
        ArrayList<Integer> attrList = new ArrayList<>();
        String json = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    context.openFileInput(filename), StandardCharsets.UTF_8));
            json = reader.readLine();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            assert json != null;
            JSONObject obj = new JSONObject(json);
            if (!obj.get(CF_KEY_CHARACTER_NAME).equals(name)) {
                Log.e("json", "Name given to function does not match name in file!" +
                        "File: '" + obj.get(CF_KEY_CHARACTER_NAME) + "' != " +
                        "Profile: '" + name + "'");
            }
            JSONObject attrObj = obj.getJSONObject(CF_KEY_CHARACTER_ATTRIBUTE);
            Iterator<String> it = attrObj.keys();
            while (it.hasNext()) {
                attrList.add(attrObj.getInt(it.next()));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        Log.i("json", "Successfully read character " + name + " from file " + filename +
                ". Creating Character object.");

        return new Character.CharBuilder(name)
                .mu(attrList.get(0))
                .kl(attrList.get(1))
                .in(attrList.get(2))
                .ch(attrList.get(3))
                .ff(attrList.get(4))
                .ge(attrList.get(5))
                .ko(attrList.get(6))
                .kk(attrList.get(7))
                .build();
    }

    /**
     * Writes a Character object to a json file, derives the filename from the charactername.
     * Also adds name and full filename to a separate json for name-to-filename mapping.
     *
     * @param context application context
     * @param c       needs to include at least the characters name
     */
    public static void writeCharToJson(Context context, Character c) {
        String name = c.getName();
        String filename = Util.normalizeString(name) + FILE_ENDING_JSON;

        //TODO create seperate attribute-dictionary in character json file
        JSONObject jobjChar = new JSONObject();
        try {
            jobjChar.put(CF_KEY_CHARACTER_NAME, name);
            if (c.getMu() != 0) {
                JSONObject attrObj = new JSONObject();
                attrObj.put("MU", c.getMu());
                attrObj.put("KL", c.getKl());
                attrObj.put("IN", c.getIn());
                attrObj.put("CH", c.getCh());
                attrObj.put("FF", c.getFf());
                attrObj.put("GE", c.getGe());
                attrObj.put("KO", c.getKo());
                attrObj.put("KK", c.getKk());
                jobjChar.put(CF_KEY_CHARACTER_ATTRIBUTE, attrObj);
            }
        } catch (JSONException er) {
            er.printStackTrace();
        }

        File charFile = new File(context.getFilesDir(), filename);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(charFile));
            Log.i("json", "Wrote Character " + name + " to file " + filename + ".");
            writer.write(jobjChar.toString());
            writer.close();
        } catch (IOException er) {
            er.printStackTrace();
        }

        JSONObject jobjMap = readProfileMapFile(context);
        try {
            jobjMap.put(name, filename);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        File profileFile = new File(context.getFilesDir(), PROFILE_MAP_FILENAME);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(profileFile));
            writer.write(jobjMap.toString());
            writer.close();
            Log.i("json", "Wrote character " + name + " to profile map file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
