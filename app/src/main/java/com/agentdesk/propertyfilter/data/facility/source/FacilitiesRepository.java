package com.agentdesk.propertyfilter.data.facility.source;

import com.agentdesk.propertyfilter.data.facility.source.local.FacilitiesLocalDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.FacilitiesRemoteDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Exclusion;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;

import java.util.ArrayList;
import java.util.HashMap;

public class FacilitiesRepository implements FacilitiesDataSource {

    private final FacilitiesDataSource facilityRemote;
    private final FacilitiesDataSource facilityLocal;
    private static FacilitiesRepository instance;

    private FacilitiesRepository(FacilitiesRemoteDataSource facilityRemote,
                                 FacilitiesDataSource facilityLocal) {
        this.facilityRemote = facilityRemote;
        this.facilityLocal = facilityLocal;
    }

    public static FacilitiesRepository getInstance(FacilitiesRemoteDataSource facilitiesRemoteDataSource,
                                                   FacilitiesLocalDataSource facilitiesLocalDataSource) {
        if (instance == null) {
            instance = new FacilitiesRepository(facilitiesRemoteDataSource, facilitiesLocalDataSource);
        }
        return instance;
    }

    @Override
    public void getFacilities(LoadFacilitiesCallback callback) {

    }

    @Override
    public void saveFacilities(ArrayList<Facility> facilities, ArrayList<ArrayList<Exclusion>> exclusions) {
        facilityLocal.saveFacilities(facilities, exclusions);
    }

    public void getFacilitiesFromLocalDataSource(final LoadFacilitiesCallback callback) {
        facilityLocal.getFacilities(new LoadFacilitiesCallback() {
            @Override
            public void onFacilitiesLoaded(FacilitiesResponse facilities) {
            }

            @Override
            public void onFacilitiesLoaded(ArrayList<Facility> facilities, HashMap<String, ArrayList<String>> exclusionMap) {
                callback.onFacilitiesLoaded(facilities, exclusionMap);
            }

            @Override
            public void onDataNotAvailable() {
                getFacilitiesFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {
                getFacilitiesFromRemoteDataSource(callback);
            }
        });
    }

    public void getFacilitiesFromRemoteDataSource(final LoadFacilitiesCallback callback) {
        facilityRemote.getFacilities(new LoadFacilitiesCallback() {
            @Override
            public void onFacilitiesLoaded(FacilitiesResponse facilities) {
                callback.onFacilitiesLoaded(facilities);
                saveFacilities(facilities.getFacilities(), facilities.getExclusions());
            }

            @Override
            public void onFacilitiesLoaded(ArrayList<Facility> facilities, HashMap<String, ArrayList<String>> exclusionMap) {

            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    public void destroyInstance() {
        instance = null;
    }
}
