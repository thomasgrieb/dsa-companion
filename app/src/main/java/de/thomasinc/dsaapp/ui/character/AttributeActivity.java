package de.thomasinc.dsaapp.ui.character;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.data.character.Character;
import de.thomasinc.dsaapp.ui.main.MainActivity;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.MinMaxValueFilter;
import de.thomasinc.dsaapp.util.Util;

/**
 * Implements functionality for the character value input window.
 * Creates an {@link EditText} Object for every value, 8 in total and collects them in an array. The
 *  possible values are limited by a filter implemented with {@link MinMaxValueFilter}.
 * Creates a confirmation {@link Button} that only becomes enabled if all fields are not empty,
 *  surveyed by a {@link TextWatcher} (or starts enabled if values are already set).
 * Loads up existing character json file, if any exist.
 * Upon pressing the confirmation button, the values get transformed into a {@link Character} object
 *  and saved as "mycharacter.json" in the corresponding files directory.
 * Ends with returning the user to the {@link MainActivity}.
 */

public class AttributeActivity extends AppCompatActivity {

    private AttributePresenter presenter;

    private EditText editMU;
    private EditText editKL;
    private EditText editIN;
    private EditText editCH;
    private EditText editFF;
    private EditText editGE;
    private EditText editKO;
    private EditText editKK;
    private final EditText[] textArray = {editMU,editKL,editIN,editCH,editFF,editGE,editKO,editKK};

    private Button confirmBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_base_value_input);

        final String char_file = "myCharacter.json";
        final Context context = getApplicationContext();

        presenter = new AttributePresenter(this, context);

        editMU = findViewById(R.id.inputMu);
        editKL = findViewById(R.id.inputKl);
        editIN = findViewById(R.id.inputIn);
        editCH = findViewById(R.id.inputCh);
        editFF = findViewById(R.id.inputFF);
        editGE = findViewById(R.id.inputGe);
        editKO = findViewById(R.id.inputKo);
        editKK = findViewById(R.id.inputKK);

        confirmBtn = (Button) findViewById(R.id.buttonCharConf);


        //If values are already set, confirmation button is enabled from the start due to this code
        // snippet

        //presenter.
        /*
        if(!Util.checkIfAnyEmptyArray(textArray)){
            confirmBtn.setEnabled(true);
        }
        */

        for(int i=0; i<8;i++) {
            //Adjust minimum und maximum input values for character here (inclusive)
            //Current: 0 - 19
            textArray[i].setFilters(new InputFilter[]{new MinMaxValueFilter(0,19)});
            textArray[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {}

                @Override
                public void afterTextChanged(Editable s) {
                    /*
                    if (!Util.checkIfAnyEmptyArray(textArray)) {
                        confirmBtn.setEnabled(true);
                    } else{
                        confirmBtn.setEnabled(false);
                    }
                     */
                }
            });
        }

        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //TODO: get char for current profile and write only new values to file

                //eigl model
                Character c = new Character.CharBuilder("placeholder")
                        .mu(Integer.parseInt(editMU.getText().toString()))
                        .kl(Integer.parseInt(editKL.getText().toString()))
                        .in(Integer.parseInt(editIN.getText().toString()))
                        .ch(Integer.parseInt(editCH.getText().toString()))
                        .ff(Integer.parseInt(editFF.getText().toString()))
                        .ge(Integer.parseInt(editGE.getText().toString()))
                        .ko(Integer.parseInt(editKO.getText().toString()))
                        .kk(Integer.parseInt(editKK.getText().toString()))
                        .build();

                Json.writeCharToJson(context,c);
                startActivity(new Intent( AttributeActivity.this, MainActivity.class));
            }
        });
    }

    public void setConfirmBtnStatus(boolean status) {
        this.confirmBtn.setEnabled(status);
    }


}
