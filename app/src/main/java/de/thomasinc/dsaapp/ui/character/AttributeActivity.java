package de.thomasinc.dsaapp.ui.character;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.data.character.Character;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.ui.main.MainActivity;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.MinMaxValueFilter;

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

public class AttributeActivity extends AppCompatActivity implements DsaView {

    private AttributePresenter presenter;

    private EditText editMU;
    private EditText editKL;
    private EditText editIN;
    private EditText editCH;
    private EditText editFF;
    private EditText editGE;
    private EditText editKO;
    private EditText editKK;
    private EditText[] textArray;

    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_attributes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        this.textArray = new EditText[]{editMU,editKL,editIN,editCH,editFF,editGE,editKO,editKK};

        confirmBtn = (Button) findViewById(R.id.buttonCharConf);
        confirmBtn.setEnabled(true);

        presenter.setAttributeBoxes();

        for (EditText editText : textArray) {
            Log.i("attrView","Setting up Filter" +
                    " for " + editText.getTag() +
                    " with Max = " + presenter.getMaxAttributeFromModel() +
                    " and Min = " + presenter.getMinAttributeFromModel());
            editText.setFilters(new InputFilter[]{new MinMaxValueFilter(
                    presenter.getMinAttributeFromModel(),
                    presenter.getMaxAttributeFromModel())});
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) { }

                @Override
                public void afterTextChanged(Editable s) {
                    confirmBtn.setEnabled(true);
                    for (EditText editText : textArray) {
                        if (presenter.checkField(editText.getText().toString())){
                            confirmBtn.setEnabled(false);
                        }
                    }
                }
            });
        }

        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: get char for current profile and write only new values to file
                presenter.remakeChar(getApplicationContext());
                startActivity(new Intent( AttributeActivity.this,
                        CharacterEditorActivity.class));
            }
        });
    }

    public void setConfirmBtnStatus(boolean status) {
        this.confirmBtn.setEnabled(status);
    }

    /**
     * Iterates over the {@link EditText} objects and compares their tags with the specified tag.
     * If the correct object is found, sets its text to the specified value.
     * @param value attribute value
     * @param tag attribute tag
     */
    public void setEditTextValue(String tag, int value){
        for(EditText editText: textArray){
            if (editText.getTag().equals(tag)){
                editText.setText(String.valueOf(value));
                break;
            }
        }
    }

    /**
     * Iterates over the {@link EditText} objects and compares their tags with the specified tag
     * in order to find the one with the specified tag.
     * @param tag attribute tag
     * @return value of box with tag
     */
    public int getEditTextValue(String tag){
        for(EditText editText: textArray){
            if (editText.getTag().equals(tag)){
                return Integer.parseInt(editText.getText().toString());
            }
        }
        return 0;
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
