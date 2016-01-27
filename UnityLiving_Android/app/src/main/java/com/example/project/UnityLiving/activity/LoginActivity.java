
package com.example.project.UnityLiving.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.UnityLiving.Network.NetworkManager;
import com.example.project.UnityLiving.Network.NetworkOptions;
import com.example.project.UnityLiving.Network.Urls;
import com.example.project.UnityLiving.R;
import com.example.project.UnityLiving.model.ApartmentModel;
import com.example.project.UnityLiving.model.ResponseModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements NetworkManager.OnNetWorkListener {

    private EditText mUsernameEditText, mPasswordEditText, mAppartmentEditText;
    private Button mSignInButton;
    private String mUsername, mPassword, mApartment;
    private NetworkManager networkManager;
    private ArrayList<ApartmentModel> mItems;
    private ArrayList<String> mApartmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        getApartmentList();

    }

    private void getApartmentList() {
        mItems = new ArrayList<>();
        mApartmentName = new ArrayList<>();
        networkManager = NetworkManager.getInstance(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        networkManager.postJsonRequest(NetworkOptions.POST_REQUEST, Urls.APARTMNET_LIST_URL, jsonObject, Urls.APARTMNET_LIST_URL_TAG);

    }

    private void tryLogin() {
        networkManager = NetworkManager.getInstance(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", mUsername);
            jsonObject.put("password", mPassword);
            jsonObject.put("apartment", mApartment);
        } catch (Exception e) {
        }
        networkManager.postJsonRequest(NetworkOptions.POST_REQUEST, Urls.LOGIN_URL, jsonObject, Urls.LOGIN_URL_TAG);

    }

    private void initViews() {
        mUsernameEditText = (EditText) findViewById(R.id.user_name);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mAppartmentEditText = (EditText) findViewById(R.id.appartment);
        mSignInButton = (Button) findViewById(R.id.sign_in);
        mAppartmentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });
        mAppartmentEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();
                mApartment = mAppartmentEditText.getText().toString();
                if (validateForm()) {
                    tryLogin();
                }


            }
        });


    }

    private boolean validateForm() {

        if (mUsername.isEmpty()) {
            mUsernameEditText.setError(getString(R.string.error_form));
            return false;
        }
        if (mPassword.isEmpty()) {
            mPasswordEditText.setError(getString(R.string.error_form));
            return false;
        }
        if (mApartment.isEmpty()) {
            mAppartmentEditText.setError(getString(R.string.error_form));
            return false;
        }

        return true;

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object object, int type, int requestId) {
        if (requestId == Urls.APARTMNET_LIST_URL_TAG) {
            Gson gson = new Gson();
            ResponseModel responseModel = gson.fromJson(object.toString(), ResponseModel.class);
            mItems = responseModel.apartmentModels;
            AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
            for (ApartmentModel mItem : mItems) {
                mApartmentName.add(mItem.mAppartmentName);
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mApartmentName);
            ab.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAppartmentEditText.setText(mApartmentName.get(which));
                }
            });


        }
        else
        {

        }

    }
}
