package de.thomasinc.dsaapp.ui.dice;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.util.Util;


/**
 * Implements the view for rolling the dice on a specific skill. Users must first select a category
 * from the first dropdown menu, then a skill from that category from the second dropdown.
 */
public class DiceActivity extends AppCompatActivity implements DsaView {

    private DicePresenter presenter;
    private Spinner catsDropdown;
    private Spinner skillsDropdown;
    private TextView skillInfo;
    private TextView skillFormula;
    private TextView resultView0;
    private TextView resultView1;
    private TextView resultView2;
    private TextView compensateView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        catsDropdown =  (Spinner) findViewById(R.id.throwCatDropdown);
        skillsDropdown =  (Spinner) findViewById(R.id.throwSkillDropdown);
        skillInfo = (TextView) findViewById(R.id.throwSkill);
        skillFormula = (TextView) findViewById(R.id.throwFormular);
        resultView0 = (TextView) findViewById(R.id.throwResult0);
        resultView1 = (TextView) findViewById(R.id.throwResult1);
        resultView2 = (TextView) findViewById(R.id.throwResult2);
        compensateView = (TextView) findViewById(R.id.throwCompensate);


        presenter = new DicePresenter(this, getApplicationContext());
        presenter.fillCatDropdown();

        Button diceButton = (Button) findViewById(R.id.throwDiceButton);

        catsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.fillSkillDropdown(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        skillsDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.updateSkillLabel(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        diceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.requestRoll();


                resultView0.setTextColor(checkForCrit(dice1));
                resultView1.setTextColor(checkForCrit(dice2));
                resultView2.setTextColor(checkForCrit(dice3));
                if(over==0){
                    comp = "0";
                } else {
                    comp = "-"+String.valueOf(over);
                }
            }
        });

         */
    }

    private int checkForCrit(int dice){
        if (dice == 1) {
            System.out.println(dice+" crit");
            return getResources().getColor(R.color.colorCritSuccess);
        } else if (dice == 20) {
            return getResources().getColor(R.color.colorCritFailure);
        } else {
            return getResources().getColor(R.color.borderColor);
        }
    }

    /**
     * Fills first dropdown
     * @param cats skill categories
     */
    public void fillCatDropdown(String[] cats){
        ArrayAdapter<String> catsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, cats);
        catsDropdown.setAdapter(catsAdapter);
    }

    /**
     * Fills second dropdown
     * @param skills skills from chosen skill category
     */
    public void fillSkillDropdown(String[] skills){
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, skills);
        skillsDropdown.setAdapter(skillAdapter);
    }

    /**
     * Sets skill label
     * @param skill chosen skill
     */
    public void setSkillInfo(String skill){
        skillInfo.setText(skill);
    }

    /**
     * Sets skill formula Label
     * @param formula formula for chosen skill
     */
    public void setSkillFormula(String formula){
        skillFormula.setText(formula);
    }

    public void setResults(String first, String second, String third, String comp){
        resultView0.setText(String.valueOf(first));
        resultView1.setText(String.valueOf(second));
        resultView2.setText(String.valueOf(third));
        compensateView.setText(comp);
    }



    @Override
    public void onError(String errormsg) {
        //from
        // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Fehler")
                .setMessage(errormsg)
                .setNeutralButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
