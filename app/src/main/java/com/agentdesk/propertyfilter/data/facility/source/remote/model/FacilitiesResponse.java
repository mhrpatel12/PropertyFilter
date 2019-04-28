package com.agentdesk.propertyfilter.data.facility.source.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FacilitiesResponse {

    @SerializedName("facilities")
    @Expose
    private ArrayList<Facility> facilities = new ArrayList<>();
    @SerializedName("exclusions")
    @Expose
    private ArrayList<ArrayList<Exclusion>> exclusions = new ArrayList<>();

    public FacilitiesResponse() {
    }

    public ArrayList<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<Facility> facilities) {
        this.facilities = facilities;
    }

    public ArrayList<ArrayList<Exclusion>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(ArrayList<ArrayList<Exclusion>> exclusions) {
        this.exclusions = exclusions;
    }
}