package de.thomasinc.dsaapp.ui.main;

import de.thomasinc.dsaapp.data.DsaModel;
import de.thomasinc.dsaapp.ui.DsaPresenter;
import de.thomasinc.dsaapp.ui.DsaView;

public class MainPresenter implements DsaPresenter {

    private DsaView view;
    private DsaModel model;

    public MainPresenter (DsaView view){
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
