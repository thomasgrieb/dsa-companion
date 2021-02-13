package de.thomasinc.dsaapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import de.thomasinc.dsaapp.CharacterInputDialog;
import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.ui.dice.DiceActivity;

public class MainActivity extends AppCompatActivity implements DsaView {

    private MainPresenter presenter;
    private Spinner profileDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, getApplicationContext());

        Button throwButton = (Button) findViewById(R.id.buttonThrow);

        throwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, DiceActivity.class));
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

    @Override
    public void onError(String errormsg) {

    }
}
