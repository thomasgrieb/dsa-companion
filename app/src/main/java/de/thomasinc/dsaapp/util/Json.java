package de.thomasinc.dsaapp.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import de.thomasinc.dsaapp.data.character.Character;

/**
 * Utility class that provides functions for dealing with json-files
 */

public class Json {

    private static final String FILE_ENDING_JSON = ".json";
    private static final String PROFILE_MAP = "profilemap" + FILE_ENDING_JSON;
    private static final String CHARACTER_NAME_KEY = "Name";
    private static final String CHARACTER_ATTRIBUTE_KEY = "Attributes";


    /**
     * Reads the skills from the json file saved in the apps' assets folder
     *
     * @param context application context needed for filepath
     * @return {@link String} of skills
     */
    public static String readSkillsJson(Context context) {
        String json = null;
        try {
            InputStream file = context.getAssets().open("skills.json");
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * Checks whether the profile map file exists.
     * If it doesn't exist, it's created here.
     * The function is intended for calling right after starting the app
     * (currently in the constructor of {@link de.thomasinc.dsaapp.ui.main.MainPresenter}.
     *
     * @param context applications context
     */
    public static void checkProfileMapFile(Context context) {
        File profileFile = new File(context.getFilesDir(), PROFILE_MAP);
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
                    context.openFileInput(PROFILE_MAP), StandardCharsets.UTF_8));
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
            System.out.println(filename);
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
            if (obj.get(CHARACTER_NAME_KEY) != name) {
                Log.e("json", "Name given to function does not match name in file!");
            }
            JSONObject attrObj = obj.getJSONObject(CHARACTER_ATTRIBUTE_KEY);
            Iterator<String> it = attrObj.keys();
            while (it.hasNext()) {
                attrList.add(attrObj.getInt(it.next()));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        Log.i("json", "Read character " + name + " from file " + filename +
                ". Creating Character object ...");

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
            jobjChar.put(CHARACTER_NAME_KEY, name);
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
                jobjChar.put(CHARACTER_ATTRIBUTE_KEY, attrObj);
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

        File profileFile = new File(context.getFilesDir(), PROFILE_MAP);
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
