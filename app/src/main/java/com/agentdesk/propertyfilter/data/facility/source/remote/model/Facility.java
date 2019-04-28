
package com.agentdesk.propertyfilter.data.facility.source.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Facility extends RealmObject implements Parcelable {

    @SerializedName("facility_id")
    @Expose
    private String facilityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("options")
    @Expose
    private RealmList<Option> options = new RealmList<Option>();
    public final static Creator<Facility> CREATOR = new Creator<Facility>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Facility createFromParcel(Parcel in) {
            return new Facility(in);
        }

        public Facility[] newArray(int size) {
            return (new Facility[size]);
        }

    };

    protected Facility(Parcel in) {
        this.facilityId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.options, (Option.class.getClassLoader()));
    }

    public Facility() {
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(RealmList<Option> options) {
        this.options = options;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(facilityId);
        dest.writeValue(name);
        dest.writeList(options);
    }

    public int describeContents() {
        return 0;
    }

}
