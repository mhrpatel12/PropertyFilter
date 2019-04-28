package com.agentdesk.propertyfilter.data.facility.source.remote.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacilitiesService {

    private static final String URL = "https://my-json-server.typicode.com/";

    private FacilitiesApi mFacilitiesApi;

    private static FacilitiesService singleton;

    private FacilitiesService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mFacilitiesApi = mRetrofit.create(FacilitiesApi.class);
    }

    public static FacilitiesService getInstance() {
        if (singleton == null) {
            singleton = new FacilitiesService();
        }
        return singleton;
    }

    public FacilitiesApi getFacilityApi() {
        return mFacilitiesApi;
    }

}
