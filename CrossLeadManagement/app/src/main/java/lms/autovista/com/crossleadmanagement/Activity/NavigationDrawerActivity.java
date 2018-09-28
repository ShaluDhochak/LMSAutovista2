package lms.autovista.com.crossleadmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.Fragment.AddNewLeadFragment;
import lms.autovista.com.crossleadmanagement.Fragment.DashboardFragment;
import lms.autovista.com.crossleadmanagement.Fragment.LeadFragment;
import lms.autovista.com.crossleadmanagement.Fragment.MasterFragment;
import lms.autovista.com.crossleadmanagement.Fragment.MyProfileFragment;
import lms.autovista.com.crossleadmanagement.R;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dashboardFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void dashboardFragment() {
        toolbar_title.setText("Dashboard");

        navigationView.getMenu().getItem(0).setChecked(true);

        DashboardFragment dfragment = new DashboardFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dfragment);
        dtransaction.commit();
    }

    public void addNewLeadFragment() {
        toolbar_title.setText("Add New Lead");

        navigationView.getMenu().getItem(1).setChecked(true);

        AddNewLeadFragment addNewleadfragment = new AddNewLeadFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, addNewleadfragment);
        dtransaction.commit();
    }

    public void leadStatusFragment(){
        toolbar_title.setText("All Lead");

        navigationView.getMenu().getItem(2).setChecked(true);

        LeadFragment leadfragment = new LeadFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, leadfragment);
        dtransaction.commit();
    }

    public void masterFragment(){
        toolbar_title.setText("Master");

        navigationView.getMenu().getItem(3).setChecked(true);

        MasterFragment masterfragment = new MasterFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, masterfragment);
        dtransaction.commit();
    }

    public void myProfileFragment(){
        toolbar_title.setText("My Profile");

        navigationView.getMenu().getItem(4).setChecked(true);

        MyProfileFragment myProfileFragment = new MyProfileFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, myProfileFragment);
        dtransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            dashboardFragment();
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_master) {
            masterFragment();
        } else if (id == R.id.nav_add_new_lead) {
            addNewLeadFragment();
        } else if (id == R.id.nav_my_profile) {
            myProfileFragment();
        } else if (id == R.id.nav_lead_status) {
            leadStatusFragment();
        }else if (id ==R.id.nav_change_password){
            Intent changePasswordIntent = new Intent(this, ChangePasswordActivity.class);
            startActivity(changePasswordIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
