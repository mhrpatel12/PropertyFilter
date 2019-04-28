package com.agentdesk.propertyfilter.ui.activity.main;

import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;
import com.agentdesk.propertyfilter.ui.activity.base.BaseView;

import java.util.ArrayList;
import java.util.HashMap;

public interface MainView extends BaseView {
    void showFacilities(FacilitiesResponse facilitiesResponse);

    void showFacilities(ArrayList<Facility> facilitiesResponse, HashMap<String, ArrayList<String>> exclustionMap);

    void showErrorMessage();

    void showThereIsNoFacilities();
}
