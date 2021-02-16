package de.thomasinc.dsaapp.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import de.thomasinc.dsaapp.data.character.Character;

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
     * @param context application context
     * @param c needs to include at least the characters name
     */
    public static void writeCharToJson(Context context, Character c) {
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("Name",c.getName());
            if (c.getMu()!=0) {
                jobj.put("MU", c.getMu());
                jobj.put("KL", c.getKl());
                jobj.put("IN", c.getIn());
                jobj.put("CH", c.getCh());
                jobj.put("FF", c.getFf());
                jobj.put("GE", c.getGe());
                jobj.put("KO", c.getKo());
                jobj.put("KK", c.getKk());
            }
        }
        catch (JSONException er){
            er.printStackTrace();
        }
        try {
            Writer output = null;
            File file = new File(context.getFilesDir(), Util.normalizeString(c.getName()));
            output = new BufferedWriter(new FileWriter(file));
            output.write(jobj.toString());
            output.close();
        } catch (IOException er){
            er.printStackTrace();
        }
    }
}
