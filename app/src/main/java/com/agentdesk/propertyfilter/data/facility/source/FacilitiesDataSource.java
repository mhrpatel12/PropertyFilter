package com.agentdesk.propertyfilter.data.facility.source;

import com.agentdesk.propertyfilter.data.facility.source.remote.model.Exclusion;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;

import java.util.ArrayList;
import java.util.HashMap;

public interface FacilitiesDataSource {

    interface LoadFacilitiesCallback {
        void onFacilitiesLoaded(FacilitiesResponse facilities);

        void onFacilitiesLoaded(ArrayList<Facility> facilities, HashMap<String, ArrayList<String>> exclusionMap);

        void onDataNotAvailable();

        void onError();
    }

    void getFacilities(LoadFacilitiesCallback callback);

    void saveFacilities(ArrayList<Facility> facilities, ArrayList<ArrayList<Exclusion>> exclusions);
}