package de.thomasinc.dsaapp.ui.character;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.data.character.Character;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.util.Util;

/**
 * Implements functionality for the profile creation window.
 * TODO: add support for Optolith json file - Users should be able to create a new Profile from
 *  a given Optolith-created json file
 * TODO: prohibit disruptive signs in profile name in order to create valid filename
 * User enters a characters name; by clicking on the confirmation button, the program checks the existing
 * files for duplicates and number of files (currently one 5 profiles supported)
 */
public class ProfileActivity extends AppCompatActivity implements DsaView {

    private ProfilePresenter presenter;

    private Button confBtn;
    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        presenter = new ProfilePresenter(this, getApplicationContext());

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
                final String charname = nameInput.getText().toString();
                final String filename = charname+fileEnding;

                Character c = new Character.CharBuilder(charname).build();




            }
        });

    }

    public void setConfBtnStatus(boolean status) {
        confBtn.setEnabled(status);
    }

    @Override
    public void onError(String errormsg) {

    }
}
