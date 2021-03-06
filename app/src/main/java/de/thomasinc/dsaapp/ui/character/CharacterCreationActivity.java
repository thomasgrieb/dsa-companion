package de.thomasinc.dsaapp.ui.character;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.ui.DsaView;


/**
 * Implements functionality for the character creation window.
 * TODO: add support for Optolith json file - Users should be able to create a new Profile from
 * a given Optolith-created json file
 * TODO: prohibit disruptive signs in profile name in order to create valid filename
 * TODO: add confirmation dialog or message and warning if char already exists
 * User enters a characters name; by clicking on the confirmation button, the program checks the
 * existing files for duplicates and number of files
 */
public class CharacterCreationActivity extends AppCompatActivity implements DsaView {

    private CharacterCreationPresenter presenter;

    private Button confBtn;
    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new CharacterCreationPresenter(this, getApplicationContext());

        confBtn = (Button) findViewById(R.id.buttonProfConf);
        confBtn.setEnabled(false);

        nameInput = findViewById(R.id.profcreatEditTextName);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.checkName(nameInput.getText().toString());
            }
        });

        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createCharacter();
                startActivity(new Intent(CharacterCreationActivity.this,
                        CharacterEditorActivity.class));
            }
        });

    }

    public void setConfBtnStatus(boolean status) {
        confBtn.setEnabled(status);
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
