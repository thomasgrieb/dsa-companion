package de.thomasinc.dsaapp;
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

/**
 * Implements functionality for the character value input window.
 * Creates an {@link EditText} Object for every value, 8 in total and collects them in an array. The
 *  possible values are limited by a filter implemented with {@link MinMaxValueFilter}.
 * Creates a confirmation {@link Button} that only becomes enabled if all fields are not empty,
 *  surveyed by a {@link TextWatcher}.
 * Loads up existing character json file, if any exist.
 * Upon pressing the confirmation button, the values get transformed into a {@link Character} object
 *  and saved as "mycharacter.json" in the corresponding files directory.
 * Ends with returning the user to the {@link MainActivity}.
 */

public class CharacterBaseValueInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_base_value_input);

        final String char_file = "myCharacter.json";
        final Context context = getApplicationContext();

        final EditText editMU = findViewById(R.id.inputMu);
        final EditText editKL = findViewById(R.id.inputKl);
        final EditText editIN = findViewById(R.id.inputIn);
        final EditText editCH = findViewById(R.id.inputCh);
        final EditText editFF = findViewById(R.id.inputFF);
        final EditText editGE = findViewById(R.id.inputGe);
        final EditText editKO = findViewById(R.id.inputKo);
        final EditText editKK = findViewById(R.id.inputKK);

        final EditText[] textArray = {editMU,editKL,editIN,editCH,editFF,editGE,editKO,editKK};

        final Button confirmBtn = (Button) findViewById(R.id.buttonCharConf);

        if(Util.checkIfCharExists(context)){
            Character c = Util.readCharFromJson(context);
            int[] cAr = c.charBaseValuesToArray();

            for(int i=0; i<8;i++) {
                System.out.println(cAr[i]);
                textArray[i].setText(String.valueOf(cAr[i]));
            }
        }

        if(!Util.checkIfAnyEmptyArray(textArray)){
            confirmBtn.setEnabled(true);
        }

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
                    if (!Util.checkIfAnyEmptyArray(textArray)) {
                        confirmBtn.setEnabled(true);
                    } else{
                        confirmBtn.setEnabled(false);
                    }
                }
            });
        }

        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //eigl model
                Character c_new = new Character(
                        Integer.parseInt(editMU.getText().toString()),
                        Integer.parseInt(editKL.getText().toString()),
                        Integer.parseInt(editIN.getText().toString()),
                        Integer.parseInt(editCH.getText().toString()),
                        Integer.parseInt(editFF.getText().toString()),
                        Integer.parseInt(editGE.getText().toString()),
                        Integer.parseInt(editKO.getText().toString()),
                        Integer.parseInt(editKK.getText().toString())
                );

                JSONObject charJobj = new CharToJson(c_new).getJobj();
                try {
                    Writer output = null;
                    File file = new File(getApplicationContext().getFilesDir(), char_file); //const-class?
                    output = new BufferedWriter(new FileWriter(file));
                    output.write(charJobj.toString());
                    output.close();
                } catch (IOException er){
                    er.printStackTrace();
                }
                startActivity(new Intent( CharacterBaseValueInput.this, MainActivity.class));
            }
        });
    }
}
