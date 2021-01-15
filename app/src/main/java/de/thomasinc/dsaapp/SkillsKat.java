package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Fills activity_skills_kat.xml window with the necessary skill categories
 */

public class SkillsKat extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_kat);

        Bundle b = getIntent().getExtras();
        final boolean values = b.getBoolean("values");


        final String[] kats = Util.getSkillKats(getApplicationContext());
        //TextView textView = findViewById(R.id.skillKatItem);
        listView = (ListView) findViewById(R.id.skillKatListView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, R.id.skillKatItem, kats);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String kat = null;
                String value = adapter.getItem(position);
                Intent intent;
                if(values){
                    intent = new Intent(SkillsKat.this, CharacterSkillValueInput.class);
                }else {
                    intent = new Intent(SkillsKat.this, Skills.class);
                }
                kat = kats[position];
                intent.putExtra("Kat", kat);
                startActivity(intent);
            }
        });
    }
}
