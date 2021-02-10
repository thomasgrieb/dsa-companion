package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.thomasinc.dsaapp.ui.character.AttributeActivity;

public class CharacterInputDialog extends AppCompatActivity {

    private final boolean values = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_input_dialog);

        Button baseValueButton = (Button) findViewById(R.id.charBaseValueButton);

        baseValueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CharacterInputDialog.this,
                        AttributeActivity.class));
            }
        });

        Button skillValueButton = (Button) findViewById(R.id.charSkillValueButton);

        skillValueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CharacterInputDialog.this,
                        SkillsKat.class);
                startActivity(intent);
            }
        });

        Button profileCreationButton = (Button) findViewById(R.id.charButtonProfileCreation);

        profileCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterInputDialog.this,
                        ProfileCreation.class);
                startActivity(intent);
            }
        });
    }
}
