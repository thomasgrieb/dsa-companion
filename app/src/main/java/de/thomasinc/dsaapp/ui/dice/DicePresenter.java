package de.thomasinc.dsaapp.ui.dice;

import android.content.Context;

import java.util.Set;

import de.thomasinc.dsaapp.data.DiceModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.Util;


/**
 * Presenter to {@link DiceActivity}.
 */

// TODO: Maybe move more functionality (extracting info form skillMap) to model?
public class DicePresenter implements DsaPresenter {

    private DiceActivity view;
    private final DiceModel model;

    /**
     * Links the view
     * Initializes model with character and skillmap (from context).
     * @param view corresponding view
     * @param context corresponding model
     */

    // TODO: passing context is suboptimal, look into alternatives
    //  https://stackoverflow.com/questions/34303510/does-the-presenter-having-knowledge-of-the-activity-context-a-bad-idea-in-the
    //  https://www.journaldev.com/20644/android-mvp-dagger2
    public DicePresenter(DiceActivity view, Context context){
        this.view = view;
        //TODO: errorhandling
        this.model = new DiceModel(Json.readCharFromJson(context),Util.makeSkillMap(context));
    }

    /**
     * Fills the category dropdown menu.
     * Fetches the categories from the skillmap, converts them to a {@link String} array and calls
     * {@link DiceActivity#fillCatDropdown}.
     */
    public void fillCatDropdown(){
        String [] catsAr = new String[model.getSkillMap().size()];
        catsAr = model.getSkillMap().keySet().toArray(catsAr);
        view.fillCatDropdown(catsAr);
    }

    /**
     * Fills the category dropdown menu.
     * Fetches the skills from the skillmap, converts them to a {@link String} array and calls
     * {@link DiceActivity#fillSkillDropdown}.
     * @param cat currently chosen category, used to set current category
     */
    public void fillSkillDropdown(String cat){
        // TODO: currentcat better?
        model.setCurrentCat(cat);
        Set<String> skills =model.getSkillMap().get(model.getCurrentCat()).keySet();
        String[] skillsAr = new String[skills.size()];
        skillsAr = skills.toArray(skillsAr);
        view.fillSkillDropdown(skillsAr);

    }

    public void requestRoll(){
        model.executeRoll();
    }

    /**
     * Updates the skill information field by calling {@link DiceActivity#setSkillInfo}.
     * @param skill chosen skill
     */
    public void updateSkillLabel(String skill){
        model.setCurrentSkill(skill);
        view.setSkillInfo(model.getCurrentSkill());
        view.setSkillFormula(
                model.getSkillMap()
                .get(model.getCurrentCat())
                .get(model.getCurrentSkill())
                .getFormula()
                .print()
        );
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
