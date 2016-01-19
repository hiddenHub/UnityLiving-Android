package com.example.project.UnityLiving.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.project.UnityLiving.R;
import com.example.project.UnityLiving.fragment.ChangePasswordFragment;
import com.example.project.UnityLiving.fragment.CreateRequestFragment;
import com.example.project.UnityLiving.fragment.PendingRequestsFragment;
import com.example.project.UnityLiving.fragment.VisitorsListFragment;
public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();

        CreateRequestFragment createRequestFragment = new CreateRequestFragment();
        setMainFragment(createRequestFragment);
        navigationView.setCheckedItem(R.id.nav_create_request);
 /*       mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment f = mFragmentManager.findFragmentById(R.id.content_frame);
                if (f != null) {
                    if (f instanceof CreateRequestFragment) {
                        toolbar.setTitle(getString(R.string.title_create_request));
                        navigationView.setCheckedItem(R.id.nav_create_request);
                    }


                }
            }
        });*/



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if(mFragmentManager.getBackStackEntryCount()==0)
            {
                toolbar.setTitle(getString(R.string.title_create_request));
                navigationView.setCheckedItem(R.id.nav_create_request);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_request) {
            // Handle the camera action
            CreateRequestFragment createRequestFragment = new CreateRequestFragment();
            setMainFragment(createRequestFragment);
        } else if (id == R.id.nav_visitors_list) {
            popAllFragments();
            VisitorsListFragment visitorsListFragment = new VisitorsListFragment();
            addFragment(visitorsListFragment,getString(R.string.title_visitors_list));

        } else if (id == R.id.nav_pending_request) {
            popAllFragments();
            PendingRequestsFragment pendingRequestsFragment = new PendingRequestsFragment();
            addFragment(pendingRequestsFragment,getString(R.string.title_pending_request));
        } else if (id == R.id.nav_change_password) {
            popAllFragments();
            ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
            addFragment(changePasswordFragment,getString(R.string.title_change_password));

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void setMainFragment(Fragment fragment) {
        toolbar.setTitle(getString(R.string.title_create_request));
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_frame, fragment);
        mFragmentTransaction.commit();
    }

    private void addFragment(Fragment fragment,String title) {
        toolbar.setTitle(title);
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        mFragmentTransaction.addToBackStack(fragment.getClass().getName());
        mFragmentTransaction.commit();
    }

    public void popAllFragments() {
        int count = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            mFragmentManager.popBackStack();
        }
    }
}
