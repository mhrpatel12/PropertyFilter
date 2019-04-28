package com.agentdesk.propertyfilter.data.facility.source.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.agentdesk.propertyfilter.data.facility.source.FacilitiesDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Exclusion;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;
import com.agentdesk.propertyfilter.data.facility.source.remote.services.FacilitiesApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agentdesk.propertyfilter.ui.activity.main.MainPresenter.PREF_NAME;
import static com.agentdesk.propertyfilter.ui.activity.main.MainPresenter.PREF_NO;
import static com.agentdesk.propertyfilter.ui.activity.main.MainPresenter.SYNC_DATE;

public class FacilitiesRemoteDataSource implements FacilitiesDataSource {

    private static FacilitiesRemoteDataSource instance;
    private final FacilitiesApi facilitiesApi;
    private static SharedPreferences sharedPreferences;

    private FacilitiesRemoteDataSource(FacilitiesApi facilitiesApi) {
        this.facilitiesApi = facilitiesApi;
    }

    public static FacilitiesRemoteDataSource getInstance(FacilitiesApi facilitiesApi, Context context) {
        if (instance == null) {
            instance = new FacilitiesRemoteDataSource(facilitiesApi);
        }
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_NO);
        return instance;
    }

    @Override
    public void getFacilities(final LoadFacilitiesCallback callback) {
        facilitiesApi.fetchFacilities().enqueue(new Callback<FacilitiesResponse>() {
            @Override
            public void onResponse(Call<FacilitiesResponse> call, Response<FacilitiesResponse> response) {
                if (response.body() != null && !response.body().getFacilities().isEmpty()) {
                    callback.onFacilitiesLoaded(response.body());
                    setDate();
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<FacilitiesResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }

    private void setDate() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(SYNC_DATE, System.currentTimeMillis() / 3600000);
        edit.apply();
    }

    @Override
    public void saveFacilities(ArrayList<Facility> facilities, ArrayList<ArrayList<Exclusion>> exclusions) {
    }
}