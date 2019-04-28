package com.agentdesk.propertyfilter.data.facility.source.remote.model.realm;

import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;
import io.realm.RealmObject;

public class FacilityRM extends RealmObject {
    private RealmList<Facility> facilities = new RealmList<>();
    private String value = "";

    public RealmList<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(RealmList<Facility> facilities) {
        this.facilities = facilities;
    }

    public JSONObject getValue() {
        try {
            return new JSONObject(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setValue(JSONObject value) {
        this.value = value.toString();
    }
}