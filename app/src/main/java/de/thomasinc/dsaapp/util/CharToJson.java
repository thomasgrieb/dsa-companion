package de.thomasinc.dsaapp.util;
import org.json.JSONException;
import org.json.JSONObject;

import de.thomasinc.dsaapp.data.Character;

public class CharToJson {

    private JSONObject jobj = new JSONObject();

    //TODO integrate into Character class as method CharToJObj

    public CharToJson(Character character){
        JSONObject jobj = new JSONObject();
        try {
            this.jobj.put("MU",character.getMu());
            this.jobj.put("KL",character.getKl());
            this.jobj.put("IN",character.getIn());
            this.jobj.put("CH",character.getCh());
            this.jobj.put("FF",character.getFf());
            this.jobj.put("GE",character.getGe());
            this.jobj.put("KO",character.getKo());
            this.jobj.put("KK",character.getKk());
        }
        catch (JSONException er){
            er.printStackTrace();
        }
    }

    public JSONObject getJobj() {
        return jobj;
    }
}
