package de.thomasinc.dsaapp.ui.character;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.SkillsKat;

/**
 * Displays a window where the user can choose between creating a character,
 * editing an existing characters attributes and
 * editing an existing characters skills.
 */

public class CharacterEditorActivity extends AppCompatActivity {

    private final boolean values = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_input_dialog);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button baseValueButton = (Button) findViewById(R.id.charBaseValueButton);

        baseValueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CharacterEditorActivity.this,
                        AttributeActivity.class));
            }
        });

        Button skillValueButton = (Button) findViewById(R.id.charSkillValueButton);

        skillValueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CharacterEditorActivity.this,
                        SkillsKat.class);
                startActivity(intent);
            }
        });

        Button profileCreationButton = (Button) findViewById(R.id.charButtonCharacterCreation);

        profileCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterEditorActivity.this,
                        CharacterCreationActivity.class);
                startActivity(intent);
            }
        });
    }
}
