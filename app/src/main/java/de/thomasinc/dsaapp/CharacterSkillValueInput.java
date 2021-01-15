package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterSkillValueInput extends AppCompatActivity {

    private String kat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_skill_values);

        final Button confirmBtn = (Button) findViewById(R.id.charSkillsConfirmButton);

        Bundle b = getIntent().getExtras();
        this.kat = b.getString("Kat");
        HashMap<String,Skill> skills = Util.getSkillsOfCat(getApplicationContext(), kat);

        ArrayList<String> skillsString = new ArrayList<>(skills.keySet());

        ListView listView = (ListView) findViewById(R.id.skillValueListView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_item_w_input, R.id.skillName, skillsString);
        listView.setAdapter(adapter);

        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
                                      }
        );


    }
}
