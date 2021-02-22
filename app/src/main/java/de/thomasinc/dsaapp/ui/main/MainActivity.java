package de.thomasinc.dsaapp.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import de.thomasinc.dsaapp.ui.character.CharacterEditorActivity;
import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.ui.dice.DiceActivity;

public class MainActivity extends AppCompatActivity implements DsaView {

    private MainPresenter presenter;
    private Spinner charactersDropdown;
    private Button throwButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        throwButton = (Button) findViewById(R.id.buttonThrow);

        charactersDropdown = findViewById(R.id.profileSelectDropdown);

        presenter = new MainPresenter(this, getApplicationContext());
        presenter.fillCharactersDropdown();

        throwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.onDestroy();
                startActivity(new Intent(MainActivity.this, DiceActivity.class));
            }
        });

        Button exitButton = (Button) findViewById(R.id.buttonExit);

        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.onDestroy();
                finishAndRemoveTask();
            }
        });

        Button charButton = (Button) findViewById(R.id.buttonChar);

        charButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.onDestroy();
                startActivity(new Intent(MainActivity.this, CharacterEditorActivity.class));
            }
        });

        charactersDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setCurrentCharacterPref(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                presenter.noCharacterSelected();
            }
        });

    }

    public void fillCharactersDropdown(String[] characters){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, characters);
        charactersDropdown.setAdapter(adapter);
    }

    public void setThrowButtonStatus(boolean status){
        throwButton.setEnabled(status);
    }

    @Override
    public void onError(String errormsg) {
        //from
        // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        new AlertDialog.Builder(this)
                .setTitle("Fehler")
                .setMessage(errormsg)
                .setNeutralButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
