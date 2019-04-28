package com.agentdesk.propertyfilter.data.facility.source.local;

import com.agentdesk.propertyfilter.data.facility.source.FacilitiesDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Exclusion;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.realm.FacilityRM;
import com.agentdesk.propertyfilter.utils.AppUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmList;

public class FacilitiesLocalDataSource implements FacilitiesDataSource {

    @Override
    public void getFacilities(final LoadFacilitiesCallback callback) {
        Realm mRealm = Realm.getDefaultInstance();
        FacilityRM facilityRM = mRealm.where(FacilityRM.class).findFirst();
        if (facilityRM != null && facilityRM.getFacilities() != null && !facilityRM.getFacilities().isEmpty()) {
            ArrayList<Facility> facilities = new ArrayList(facilityRM.getFacilities());
            HashMap<String, ArrayList<String>> exclusionMap = new Gson().fromJson(facilityRM.getValue().toString(), HashMap.class);
            callback.onFacilitiesLoaded(facilities, exclusionMap);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void saveFacilities(final ArrayList<Facility> facilities, final ArrayList<ArrayList<Exclusion>> exclusions) {
        final Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            public void execute(Realm realm) {
                RealmList<Facility> facilitiesList = new RealmList<>();
                facilitiesList.addAll(facilities);
                FacilityRM facilityRM = new FacilityRM();
                facilityRM.setFacilities(facilitiesList);
                facilityRM.setValue(new JSONObject(AppUtils.generateExclusionMap(exclusions)));
                realm.copyToRealm(facilityRM);
            }
        });
    }
}