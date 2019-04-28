
package com.agentdesk.propertyfilter.data.facility.source.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exclusion implements Parcelable {

    @SerializedName("facility_id")
    @Expose
    private String facilityId;
    @SerializedName("options_id")
    @Expose
    private String optionsId;
    public final static Creator<Exclusion> CREATOR = new Creator<Exclusion>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Exclusion createFromParcel(Parcel in) {
            return new Exclusion(in);
        }

        public Exclusion[] newArray(int size) {
            return (new Exclusion[size]);
        }

    };

    protected Exclusion(Parcel in) {
        this.facilityId = ((String) in.readValue((String.class.getClassLoader())));
        this.optionsId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Exclusion() {
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(facilityId);
        dest.writeValue(optionsId);
    }

    public int describeContents() {
        return 0;
    }

}
