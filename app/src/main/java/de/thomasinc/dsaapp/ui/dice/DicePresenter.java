package de.thomasinc.dsaapp.ui.dice;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;

import de.thomasinc.dsaapp.data.DiceModel;
import de.thomasinc.dsaapp.data.DsaModel;
import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.ui.DsaView;

public class DicePresenter implements DsaPresenter {

    private DiceActivity view;
    private DsaModel model = new DiceModel();
    //TODO: passing context is suboptimal, look into alternatives
    //  https://stackoverflow.com/questions/34303510/does-the-presenter-having-knowledge-of-the-activity-context-a-bad-idea-in-the
    //  https://www.journaldev.com/20644/android-mvp-dagger2
    private Context context;

    public DicePresenter(DiceActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    public void fillSkillCatDropdown(HashMap<String, HashMap<String, Skill>> skillMap){
        view.fillSkillDropdown(skillMap.keySet().toArray());
    }

    public void updateSkillLabel(){

    }

    public void fillSkillDropdown(){

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
