package de.thomasinc.dsaapp.ui.dice;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import de.thomasinc.dsaapp.data.DiceModel;
import de.thomasinc.dsaapp.data.RollResult;
import de.thomasinc.dsaapp.data.Skill;
import de.thomasinc.dsaapp.data.SkillCat;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.ConstantsGlobal;
import de.thomasinc.dsaapp.util.Json;
import de.thomasinc.dsaapp.util.Util;


/**
 * Presenter to {@link DiceActivity}.
 */

// TODO: Maybe move more functionality (extracting info form skillMap) to model?
public class DicePresenter implements DsaPresenter {

    private DiceActivity view;
    private final DiceModel model;
    private SharedPreferences pref;

    /**
     * Links the view
     * Initializes model with character and skill map (from context)
     *
     * @param view    corresponding view
     * @param context corresponding model
     */

    // TODO: passing context is suboptimal, look into alternatives
    //  https://stackoverflow.com/questions/34303510/does-the-presenter-having-knowledge-of-the-activity-context-a-bad-idea-in-the
    //  https://www.journaldev.com/20644/android-mvp-dagger2
    public DicePresenter(DiceActivity view, Context context) {
        this.pref = context.getSharedPreferences(ConstantsGlobal.PREFERENCES_FILE, 0);
        this.view = view;
        this.model = new DiceModel(Json.readCharFromJson(context, fetchCurrentCharacterFromPref()),
                Json.makeSkillsFromJson(context), Json.makeCatsFromJson(context));
    }

    /**
     * Fills the category dropdown menu.
     * Fetches the category list from the model, converts it to a {@link SkillCat} array and calls
     * {@link DiceActivity#fillCatDropdown}.
     */
    public void fillCatDropdown() {
        SkillCat[] catsAr = new SkillCat[0];
        catsAr = model.getSkillCatList().toArray(catsAr);
        view.fillCatDropdown(catsAr);
    }

    /**
     * Fills the skill dropdown menu.
     * Sets current category in model. Then uses {@link Util#getSkillsOfCat} to get a list of all
     * skills in that category, converts it to a {@link Skill} array and
     * calls {@link DiceActivity#fillSkillDropdown}.
     *
     * @param cat currently chosen category, used to set current category
     */
    public void fillSkillDropdown(SkillCat cat) {
        model.setCurrentCat(cat);
        try {
            ArrayList<Skill> skillList = Util.getSkillsOfCat(model.getSkillList(),
                    model.getCurrentCat());
            Skill[] skillsAr = new Skill[0];
            skillsAr = skillList.toArray(skillsAr);
            view.fillSkillDropdown(skillsAr);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Requests roll from model.
     */
    public void requestRoll() {
        if (model.getCurrentSkill() == null) {
            view.onError("Keine Fähigkeit ausgewählt!");
        } else {
            RollResult res = model.roll();
            view.setDice(res.getFirst(), res.getSecond(), res.getThird());
            view.setCompensate(res.getCompensate());
            view.setQuality(res.getQuality());
        }
    }

    /**
     * Updates the skill information field by calling {@link DiceActivity#setSkillInfo}.
     *
     * @param skill chosen skill
     */
    public void updateSkill(Skill skill) {
        model.setCurrentSkill(skill);
        try {
            view.setSkillInfo(model.getCurrentSkill().getName());
            view.setSkillFormula(model.getCurrentSkill().getFormula().print());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tries to fetch the current character saved in {@link SharedPreferences}.
     *
     * @return a set of character names or default set
     */
    public String fetchCurrentCharacterFromPref() {
        return pref.getString(ConstantsGlobal.PREFERENCES_CURRENT_CHAR_KEY, "");
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
