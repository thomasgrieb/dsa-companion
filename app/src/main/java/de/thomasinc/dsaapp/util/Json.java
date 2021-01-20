package de.thomasinc.dsaapp.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import de.thomasinc.dsaapp.data.Character;

/**
 * Utility class that provides functions for dealing with json-files
 */

public class Json {
    /**
     * Reads the skills from the json file saved in the apps' assets folder
     * @param context application context needed for filepath
     * @return {@link String} of skills
     */
    public static String readSkillsJson(Context context){
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
     * Reads existing charfile (json) from the  appropriate file folder and creates corresponding
     *  {@link Character} object.
     * @param context application context needed for filepath
     * @return {@link Character} object with the saved attributes
     */
    public static Character readCharFromJson(Context context) {
        ArrayList<Integer> l = new ArrayList<>();
        String json = null;
        try {
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
     * Writes a Character object to a json file, derives the filename from the charactername
     * @param context
     * @param c
     */
    public static void writeCharToJson(Context context, Character c) {
        //final String filename =
    }
}
