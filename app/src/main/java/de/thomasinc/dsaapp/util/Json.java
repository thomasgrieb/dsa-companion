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
                Log.i("Json","Created profilemap file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the contents of the profile map file
     *
     * @param context application context
     * @return profile map
     */
    public static JSONObject readProfileMapFile(Context context){
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
     * @param name characters name
     * @return the corresponding filename
     */
    public static String getCharFilepath(Context context, String name){
        String filename = "";
        try {
            JSONObject jobj = readProfileMapFile(context);
            filename = jobj.getString(name);
        } catch (JSONException e){
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
        ArrayList<Integer> l = new ArrayList<>();
        String json = null;
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(
            //        context.openFileInput(), StandardCharsets.UTF_8));

            InputStream file = context.openFileInput("myCharacter.json"); //const-class?
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            assert json != null;
            JSONObject obj = new JSONObject(json);
            Iterator<String> it = obj.keys();
            while (it.hasNext()) {
                l.add(obj.getInt(it.next()));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return new Character.CharBuilder("placeholder")
                .mu(l.get(0))
                .kl(l.get(1))
                .in(l.get(2))
                .ch(l.get(3))
                .ff(l.get(4))
                .ge(l.get(5))
                .ko(l.get(6))
                .kk(l.get(7))
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
            jobjChar.put("Name", name);
            if (c.getMu() != 0) {
                jobjChar.put("MU", c.getMu());
                jobjChar.put("KL", c.getKl());
                jobjChar.put("IN", c.getIn());
                jobjChar.put("CH", c.getCh());
                jobjChar.put("FF", c.getFf());
                jobjChar.put("GE", c.getGe());
                jobjChar.put("KO", c.getKo());
                jobjChar.put("KK", c.getKk());
            }
        } catch (JSONException er) {
            er.printStackTrace();
        }

        File charFile = new File(context.getFilesDir(), filename);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(charFile));
            System.out.println(charFile);
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
            System.out.println("Profilemap: " + jobjMap.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
