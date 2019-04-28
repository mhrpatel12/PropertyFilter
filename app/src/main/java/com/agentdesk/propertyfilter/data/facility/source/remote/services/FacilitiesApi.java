package com.agentdesk.propertyfilter.data.facility.source.remote.services;

import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FacilitiesApi {
    @GET("iranjith4/ad-assignment/db")
    Call<FacilitiesResponse> fetchFacilities();
}