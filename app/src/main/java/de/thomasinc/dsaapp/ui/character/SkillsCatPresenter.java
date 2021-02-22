package de.thomasinc.dsaapp.ui.character;

import android.content.Context;

import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.util.Util;

public class SkillsCatPresenter implements DsaPresenter {

    SkillsCatActivity view;

    public SkillsCatPresenter(SkillsCatActivity view) {
        this.view = view;
    }

    public void fillView(Context context) {
        view.fill(Util.getSkillKats(context));
    }

    public String resolveCat(Context context, int pos) {
        return Util.getSkillKats(context)[pos];
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onCreate() {

    }
}
