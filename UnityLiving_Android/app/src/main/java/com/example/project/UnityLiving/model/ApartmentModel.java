package com.example.project.UnityLiving.model;

import com.example.project.UnityLiving.Utils.AppConstants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rahul on 24/01/16.
 */
public class ApartmentModel {

    @SerializedName(AppConstants.ID)
    public String mAppartmentId;


    @SerializedName(AppConstants.APARTMENT_NAME)
    public String mAppartmentName;

}
