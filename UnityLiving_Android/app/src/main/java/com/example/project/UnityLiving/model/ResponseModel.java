package com.example.project.UnityLiving.model;

import com.example.project.UnityLiving.Utils.AppConstants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ResponseModel {

    @SerializedName(AppConstants.APARTMENT)
    public ArrayList<ApartmentModel> apartmentModels;

}
