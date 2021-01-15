package de.thomasinc.dsaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Random;

public class Dice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        Bundle b = getIntent().getExtras();
        final String skill = b.getString("Skill");
        final String kat = b.getString("Kat");
        final String comp_neg = "-";

        final HashMap<String,Skill> skills = Util.getSkills(getApplicationContext(),kat);

        final Formula formula = skills.get(skill).getFormula();

        TextView skillInfo = (TextView) findViewById(R.id.throwSkill);
        skillInfo.setText(skill);
        TextView skillFormular = (TextView) findViewById(R.id.throwFormular);
        skillFormular.setText(formula.print());

        Button diceButton = (Button) findViewById(R.id.throwDiceButton);

        diceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Character character = Util.readCharFromJson(getApplicationContext());
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
}
