package de.thomasinc.dsaapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Implements functionality for the profile creation window.
 * TODO: add support for Optolith json file - Users should be able to create a new Profile from
 *  a given Optolith-created json file
 * TODO: prohibit disruptive signs in profile name in order to create valid filename
 * User enters a name and by clicking on the confirmation button, the programm checks the existing
 * files for duplicates and number of files (currently one 5 profiles supported)
 */
public class ProfileCreation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        final Button confBtn = (Button) findViewById(R.id.buttonProfConf);
        final EditText nameInput = findViewById(R.id.edittextProfName);

        final String fileEnding = ".json";


        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String charname = nameInput.getText().toString();
                final String filename = charname+fileEnding;

                Character c = new Character.CharBuilder(charname).build();




            }
        });



    }
}
