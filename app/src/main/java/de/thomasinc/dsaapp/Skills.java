package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.util.Util;

public class Skills extends AppCompatActivity {

    private String kat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        Bundle b = getIntent().getExtras();
        this.kat = b.getString("Kat");
        HashMap<String, Skill> skills = Util.getSkillsOfCat(getApplicationContext(), kat);

        ArrayList<String> skillsString = new ArrayList<>(skills.keySet());

        ListView listView = (ListView) findViewById(R.id.skillListView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_item, R.id.skillKatItem, skillsString);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("klick");
                String value = adapter.getItem(position);
                Intent intent = new Intent(Skills.this, Dice.class);
                intent.putExtra("Skill", value);
                intent.putExtra("Kat",kat);
                startActivity(intent);

            }
        });
    }
}
