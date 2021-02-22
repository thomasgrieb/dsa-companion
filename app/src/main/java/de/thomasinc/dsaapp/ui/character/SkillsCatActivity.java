package de.thomasinc.dsaapp.ui.character;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.util.ConstantsGlobal;

/**
 * Fills activity_skills_kat.xml window with the necessary skill categories
 */

public class SkillsCatActivity extends AppCompatActivity {

    private SkillsCatPresenter presenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_cat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new SkillsCatPresenter(this);

        listView = (ListView) findViewById(R.id.skillKatListView);

        presenter.fillView(getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(SkillsCatActivity.this,
                        SkillsActivity.class);
                intent.putExtra(ConstantsGlobal.EXTRA_SKILL_CAT,
                        presenter.resolveCat(getApplicationContext(), position));
                startActivity(intent);
            }
        });
    }

    public void fill(String[] cats) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_item, R.id.skillKatItem,
                cats);
        listView.setAdapter(adapter);
    }
}
