
package com.agentdesk.propertyfilter.data.facility.source.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Option extends RealmObject implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    public final static Creator<Option> CREATOR = new Creator<Option>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        public Option[] newArray(int size) {
            return (new Option[size]);
        }

    };

    protected Option(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Option() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(icon);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
