package de.thomasinc.dsaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final boolean values = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button throwButton = (Button) findViewById(R.id.buttonThrow);

        throwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SkillsKat.class);
                intent.putExtra("values",values);
                startActivity(intent);
            }
        });

        Button exitButton = (Button) findViewById(R.id.buttonExit);

        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finishAndRemoveTask();
            }
        });

        Button charButton = (Button) findViewById(R.id.buttonChar);

        charButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CharacterInputDialog.class));
            }
        });
    }

}
