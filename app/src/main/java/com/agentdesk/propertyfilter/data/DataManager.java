package com.agentdesk.propertyfilter.data;

import com.agentdesk.propertyfilter.data.facility.source.FacilitiesRepository;
import com.agentdesk.propertyfilter.data.facility.source.local.FacilitiesLocalDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.FacilitiesRemoteDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.services.FacilitiesApi;
import com.agentdesk.propertyfilter.data.facility.source.remote.services.FacilitiesService;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public FacilitiesApi getFacilitiesApi() {
        return FacilitiesService.getInstance().getFacilityApi();
    }

    public FacilitiesRepository getFacilityRepository(FacilitiesRemoteDataSource facilitiesRemoteDataSource, FacilitiesLocalDataSource facilitiesLocalDataSource) {
        return FacilitiesRepository.getInstance(facilitiesRemoteDataSource, facilitiesLocalDataSource);
    }

}
