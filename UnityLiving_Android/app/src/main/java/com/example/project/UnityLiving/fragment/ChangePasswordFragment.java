package com.example.project.UnityLiving.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.UnityLiving.R;

public class ChangePasswordFragment extends Fragment {
    private View root;
    private EditText mPasswordEditText, mRepeatEditText;
    private Button mChangePasswordButton;

    private String  mNewPassword, mRepeatPassword;


    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_change_password, container, false);
        initViews();
        return root;
    }

    private void initViews() {
        mPasswordEditText = (EditText) root.findViewById(R.id.password);
        mRepeatEditText = (EditText) root.findViewById(R.id.repeat_password);
        mChangePasswordButton = (Button) root.findViewById(R.id.change_password);
        mChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNewPassword = mPasswordEditText.getText().toString();
                mRepeatPassword = mRepeatEditText.getText().toString();

                if (validateForm()) {

                }

            }
        });


    }

    private boolean validateForm() {

        if (mNewPassword.isEmpty()) {
            mPasswordEditText.setError(getString(R.string.error_form));
            return false;
        }
        if (mRepeatPassword.isEmpty()) {
            mRepeatEditText.setError(getString(R.string.error_form));
            return false;
        }
        if (!mNewPassword.contentEquals(mRepeatPassword)) {
            mRepeatEditText.setError(getString(R.string.error_password));
            return false;
        }
        return true;

    }


}
