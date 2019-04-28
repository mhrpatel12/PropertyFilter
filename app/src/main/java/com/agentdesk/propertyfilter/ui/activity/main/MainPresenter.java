package com.agentdesk.propertyfilter.ui.activity.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.agentdesk.propertyfilter.data.facility.source.FacilitiesDataSource;
import com.agentdesk.propertyfilter.data.facility.source.FacilitiesRepository;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;
import com.agentdesk.propertyfilter.ui.activity.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class MainPresenter extends BasePresenter<MainView> {

    public static final String SYNC_DATE = "sync_date";
    public static final String PREF_NAME = "PROP";
    public static final int PREF_NO = 0;
    private final FacilitiesRepository facilitiesRepository;
    private SharedPreferences sharedPreferences;

    MainPresenter(MainView view, FacilitiesRepository facilitiesRepository) {
        super(view);
        this.facilitiesRepository = facilitiesRepository;
    }

    public void getFacilities(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_NO);
        if (getDate() == 0 || System.currentTimeMillis() / 3600000 - getDate() >= 24) {
            facilitiesRepository.getFacilitiesFromRemoteDataSource(new FacilitiesCallListener(view));
        } else {
            facilitiesRepository.getFacilitiesFromLocalDataSource(new FacilitiesCallListener(view));
        }
    }

    private long getDate() {
        return sharedPreferences.getLong(SYNC_DATE, 0);
    }

    private static class FacilitiesCallListener implements FacilitiesDataSource.LoadFacilitiesCallback {

        private WeakReference<MainView> view;

        private FacilitiesCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onFacilitiesLoaded(FacilitiesResponse facilities) {
            if (view.get() == null) return;
            view.get().showFacilities(facilities);
        }

        @Override
        public void onFacilitiesLoaded(ArrayList<Facility> facilities, HashMap<String, ArrayList<String>> exclusionMap) {
            if (view.get() == null) return;
            view.get().showFacilities(facilities, exclusionMap);
        }

        @Override
        public void onDataNotAvailable() {
            if (view.get() == null) return;
            view.get().showThereIsNoFacilities();
        }

        @Override
        public void onError() {
            if (view.get() == null) return;
            view.get().showErrorMessage();
        }
    }
}