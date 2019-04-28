package com.agentdesk.propertyfilter.utils;

import com.agentdesk.propertyfilter.R;
import com.agentdesk.propertyfilter.data.facility.source.remote.model.Exclusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AppUtils {

    private static final String Apartment = "apartment";
    private static final String Condo = "condo";
    private static final String Boat = "boat";
    private static final String Land = "land";
    private static final String Rooms = "rooms";
    private static final String NoRoom = "no-room";
    private static final String Swimming = "swimming";
    private static final String Garden = "garden";
    private static final String Garage = "garage";

    public static int getRadioDrawable(String facility) {
        switch (facility) {
            case Apartment:
                return R.drawable.background_apartment_radio;
            case Condo:
                return R.drawable.background_condo_radio;
            case Boat:
                return R.drawable.background_boat_radio;
            case Land:
                return R.drawable.background_land_radio;
            case Rooms:
                return R.drawable.background_room_radio;
            case NoRoom:
                return R.drawable.background_no_room_radio;
            case Swimming:
                return R.drawable.background_swimming_radio;
            case Garden:
                return R.drawable.background_garden_radio;
            case Garage:
                return R.drawable.background_garage_radio;
            default:
                return R.drawable.background_apartment_radio;
        }
    }

    public static HashMap<String, ArrayList<String>> generateExclusionMap(ArrayList<ArrayList<Exclusion>> exclusionList) {
        HashMap<String, ArrayList<String>> exclusionMap = new HashMap<>();
        for (ArrayList<Exclusion> exclusionArrayList : exclusionList) {
            for (int i = 0; i < exclusionArrayList.size() - 1; i++) {
                String[] key = new String[2];
                String[] value = new String[2];
                ArrayList<String> arrayList = new ArrayList();
                ArrayList<String> arrayList2 = new ArrayList();
                key[0] = exclusionArrayList.get(i).getFacilityId();
                key[1] = exclusionArrayList.get(i).getOptionsId();
                value[0] = exclusionArrayList.get(i + 1).getFacilityId();
                value[1] = exclusionArrayList.get(i + 1).getOptionsId();
                arrayList.add(Arrays.toString(value));
                if (exclusionMap.containsKey(Arrays.toString(key))) {
                    exclusionMap.get(Arrays.toString(key)).add(Arrays.toString(value));
                } else {
                    exclusionMap.put(Arrays.toString(key), arrayList);
                }
                arrayList2.add(Arrays.toString(key));
                if (exclusionMap.containsKey(Arrays.toString(value))) {
                    exclusionMap.get(Arrays.toString(value)).add(Arrays.toString(key));
                } else {
                    exclusionMap.put(Arrays.toString(value), arrayList2);
                }
            }
        }
        return exclusionMap;
    }
}