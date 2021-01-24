package de.thomasinc.dsaapp.ui.dice;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Random;

import de.thomasinc.dsaapp.R;
import de.thomasinc.dsaapp.data.Character;
import de.thomasinc.dsaapp.data.Formula;
import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.ui.DsaView;
import de.thomasinc.dsaapp.util.Util;
import de.thomasinc.dsaapp.util.Json;


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        catsDropdown =  (Spinner) findViewById(R.id.throwCatDropdown);
        skillsDropdown =  (Spinner) findViewById(R.id.throwSkillDropdown);
        skillInfo = (TextView) findViewById(R.id.throwSkill);
        skillFormula = (TextView) findViewById(R.id.throwFormular);

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

                presenter
                Character character = Json.readCharFromJson(getApplicationContext());
                Random ran = new Random();
                int over = 0;
                String comp;

                int dice1 = ran.nextInt(20)+1;
                int value1 = character.get(formula.getFirst());
                over+=Util.largerThan(dice1,value1);

                int dice2 = ran.nextInt(20)+1;
                int value2 = character.get(formula.getSecond());
                over+=Util.largerThan(dice2,value2);

                int dice3 = ran.nextInt(20)+1;
                int value3 = character.get(formula.getThird());
                over+=Util.largerThan(dice3,value3);

                TextView resultView0 = (TextView) findViewById(R.id.throwResult0);
                resultView0.setText(String.valueOf(dice1));
                resultView0.setTextColor(checkForCrit(dice1));
                TextView resultView1 = (TextView) findViewById(R.id.throwResult1);
                resultView1.setText(String.valueOf(dice2));
                resultView1.setTextColor(checkForCrit(dice2));
                TextView resultView2 = (TextView) findViewById(R.id.throwResult2);
                resultView2.setText(String.valueOf(dice3));
                resultView2.setTextColor(checkForCrit(dice3));
                TextView compensateView = (TextView) findViewById(R.id.throwCompensate);
                if(over==0){
                    comp = "0";
                } else {
                    comp = "-"+String.valueOf(over);
                }
                compensateView.setText(comp);
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

    @Override
    public void onError(String errormsg) {

    }
}
