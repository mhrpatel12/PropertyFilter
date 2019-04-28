package com.agentdesk.propertyfilter.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agentdesk.propertyfilter.R;
import com.agentdesk.propertyfilter.data.DataManager;
import com.agentdesk.propertyfilter.data.facility.source.FacilitiesRepository;
import com.agentdesk.propertyfilter.data.facility.source.local.FacilitiesLocalDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.FacilitiesRemoteDataSource;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.FacilitiesResponse;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Facility;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Option;
import com.agentdesk.propertyfilter.data.facility.source.remote.services.FacilitiesApi;
import com.agentdesk.propertyfilter.ui.activity.base.BaseActivity;
import com.agentdesk.propertyfilter.utils.AppUtils;
import com.agentdesk.propertyfilter.widgets.FacilityRadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private LinearLayout layoutFacilities;
    HashMap<String, ArrayList<String>> exclustionMap = new HashMap<>();
    ArrayList<String> disabledFacilities = new ArrayList<>();
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        layoutFacilities = findViewById(R.id.layoutFacilities);
        presenter.getFacilities(this);
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {

        FacilitiesApi facilitiesApi = DataManager.getInstance().getFacilitiesApi();
        FacilitiesRemoteDataSource remoteDataSource = FacilitiesRemoteDataSource.getInstance(facilitiesApi, this);

        FacilitiesLocalDataSource localDataSource = new FacilitiesLocalDataSource();
        FacilitiesRepository facilityRepository = DataManager.getInstance().getFacilityRepository(remoteDataSource, localDataSource);

        return new MainPresenter(this, facilityRepository);
    }


    @Override
    public void showFacilities(FacilitiesResponse facilitiesResponse) {
        showFacilities(facilitiesResponse.getFacilities(), AppUtils.generateExclusionMap(facilitiesResponse.getExclusions()));
    }

    @Override
    public void showFacilities(ArrayList<Facility> facilitiesResponse, HashMap<String, ArrayList<String>> exclustionMap) {
        this.exclustionMap = exclustionMap;
        for (Facility facility : facilitiesResponse) {
            View layoutRadioGroup = inflater.inflate(R.layout.layout_facility_radio_group, null);
            TextView txtFacility = layoutRadioGroup.findViewById(R.id.txtFacility);
            FacilityRadioGroup radioGrp = layoutRadioGroup.findViewById(R.id.radio_group_facility_options);
            layoutFacilities.addView(layoutRadioGroup);
            txtFacility.setText(facility.getName());
            for (Option option : facility.getOptions()) {
                View layoutPollSingleSelection = inflater.inflate(R.layout.layout_facility_selection, null);
                AppCompatRadioButton radioButton = layoutPollSingleSelection.findViewById(R.id.radio_button_facility_option);
                String[] tag = new String[2];
                tag[0] = facility.getFacilityId();
                tag[1] = option.getId();
                radioButton.setTag(Arrays.toString(tag));
                radioButton.setText(option.getName());
                radioButton.setButtonDrawable(MainActivity.this.getResources().getDrawable(AppUtils.getRadioDrawable(option.getIcon())));
                final HashMap<String, ArrayList<String>> finalExclustionMap = exclustionMap;
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            for (String disabledFacility : disabledFacilities) {
                                ((AppCompatRadioButton) layoutFacilities.findViewWithTag(disabledFacility)).setEnabled(true);
                            }
                            disabledFacilities.clear();
                            if (finalExclustionMap.containsKey(compoundButton.getTag())) {
                                ArrayList<String> exclusions = finalExclustionMap.get(compoundButton.getTag());
                                for (String exclusion : exclusions) {
                                    ((AppCompatRadioButton) layoutFacilities.findViewWithTag(exclusion)).setChecked(false);
                                    ((AppCompatRadioButton) layoutFacilities.findViewWithTag(exclusion)).setEnabled(false);
                                    disabledFacilities.add(exclusion);
                                }
                            }
                        }
                    }
                });
                radioGrp.addView(radioButton);
            }
        }
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showThereIsNoFacilities() {
        Toast.makeText(this, "There are no facilities!", Toast.LENGTH_SHORT).show();
    }
}
