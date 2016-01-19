package com.example.project.UnityLiving.activity;

/**
 * Created by rahul on 26/11/15.
 */


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import com.example.project.UnityLiving.R;


public class MainSplashScreen extends Activity {
    CoordinatorLayout coord;
    private Snackbar snackbar;
    private Thread background;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainSplashScreen.this);

        alertDialog.setTitle("Info");
        alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });


        background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        background.start();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


}