package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                        CharacterBaseValueInput.class));
            }
        });

        Button skillValueButton = (Button) findViewById(R.id.charSkillValueButton);

        skillValueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CharacterInputDialog.this,
                        SkillsKat.class);
                intent.putExtra("values",values);
                startActivity(intent);
            }
        });
    }
}
