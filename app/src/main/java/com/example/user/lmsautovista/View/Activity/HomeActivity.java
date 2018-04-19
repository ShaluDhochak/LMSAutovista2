package com.example.user.lmsautovista.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DashboardCountBean;
import com.example.user.lmsautovista.Model.LocationDashboardBean;
import com.example.user.lmsautovista.Model.LoginBean;
import com.example.user.lmsautovista.Presenter.DashboardPresenter;
import com.example.user.lmsautovista.Presenter.LoginPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.SessionManagement;
import com.example.user.lmsautovista.View.Fragment.AddNewLeadFragment;
import com.example.user.lmsautovista.View.Fragment.AllLeadFragment;
import com.example.user.lmsautovista.View.Fragment.CallingTaskFragment;
import com.example.user.lmsautovista.View.Fragment.DashboardFragment;
import com.example.user.lmsautovista.View.Fragment.TrackerFragment;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IView.LoginView {


    SessionManagement session;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.toolbarLocation_spinner)
    Spinner toolbarLocation_spinner;

    public View navHeader;
    public TextView deptName1_tv,userName_tv;
    String process_id, process_name, selectedLocationId, selectedLocation;
    LoginPresenter loginPresenter;
    DashboardPresenter dashboardPresenter;

    Map<String, String> locationMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initialiseUI();
    }

    @Override
    public void onResume(){
        super.onResume();
        initialiseUI();
    }

    private void initialiseUI(){
        setSupportActionBar(toolbar);
        navHeader = navigationView.getHeaderView(0);

        // Session class instance
        session = new SessionManagement(getApplicationContext());

        loginPresenter = new LoginPresenter(this);
        dashboardPresenter = new DashboardPresenter(this);

        userName_tv = (TextView) navHeader.findViewById(R.id.userName_tv);
        deptName1_tv = (TextView) navHeader.findViewById(R.id.deptName1_tv);

        userName_tv.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.USER_NAME, ""));
        deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, ""));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        dashboardPresenter.getLocationList(this);

        navigationView.setNavigationItemSelectedListener(this);
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
     //   getMenuInflater().inflate(R.menu.home, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        MenuItem currentItem = menu.findItem(R.id.action_current);
        MenuItem serviceItem = menu.findItem(R.id.action_service);
        MenuItem finanaceItem = menu.findItem(R.id.action_finance);
        MenuItem accessoriesItem = menu.findItem(R.id.action_accessories);
        MenuItem newCarItem = menu.findItem(R.id.action_new_car);
        MenuItem usedCarItem = menu.findItem(R.id.action_used_car);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        if (role.equals("1")){
            //admin
            currentItem.setVisible(true);
            serviceItem.setVisible(true);
            finanaceItem.setVisible(true);
            accessoriesItem.setVisible(true);
            newCarItem.setVisible(true);
            usedCarItem.setVisible(true);

        }else if ( (role.equals("13")) ||(role.equals("14")) ){
            currentItem.setVisible(true);
            serviceItem.setVisible(false);
            finanaceItem.setVisible(true);
            accessoriesItem.setVisible(false);
            newCarItem.setVisible(false);
            usedCarItem.setVisible(false);
            process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
            process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardFragment();
        } else if ((role.equals("3")) || (role.equals("2")) || (role.equals("4"))) {
                currentItem.setVisible(true);
                serviceItem.setVisible(false);
                finanaceItem.setVisible(false);
                accessoriesItem.setVisible(false);
                newCarItem.setVisible(true);
                usedCarItem.setVisible(true);
                process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
                process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
                loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
                dashboardFragment();

        }else if ((role.equals("5"))){
            currentItem.setVisible(true);
            serviceItem.setVisible(false);
            finanaceItem.setVisible(false);
            accessoriesItem.setVisible(false);
            newCarItem.setVisible(true);
            usedCarItem.setVisible(false);
            process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
            process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardFragment();

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_finance) {
            process_id = "1";
            process_name = "Finance";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardPresenter.getLocationList(this);

            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
       //     dashboardFragment();
            return true;
        }else if(id == R.id.action_service) {
            process_id = "4";
            process_name = "Service";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardPresenter.getLocationList(this);

            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
          //  dashboardFragment();
           // deptName1_tv.setText("Insurance");
            return true;
        }else if(id == R.id.action_accessories) {
            process_id = "5";
            process_name = "Accessories";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardPresenter.getLocationList(this);

            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
        //    dashboardFragment();
            return true;
        }else if(id == R.id.action_new_car) {
            process_id = "6";
            process_name = "New Car";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardPresenter.getLocationList(this);
            deptName1_tv.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));

            return true;
        }else if(id == R.id.action_used_car) {
            process_id = "7";
            process_name = "Used Car";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            dashboardPresenter.getLocationList(this);

            deptName1_tv.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            dashboardFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dashboardFragment()
    {
        toolbar_title.setText("Dashboard");

        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(4).setVisible(false);

        navigationView.getMenu().getItem(5).setVisible(false);
        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(false);

        DashboardFragment dfragment = new DashboardFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dfragment);
        dtransaction.commit();
    }

    public void addNewLeadFragment(){
        toolbar_title.setText("Dashboard");
        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(4).setVisible(false);

        navigationView.getMenu().getItem(5).setVisible(false);
        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(false);

        AddNewLeadFragment addNewLeadfragment = new AddNewLeadFragment();
        FragmentTransaction addNewLeadtransaction = getSupportFragmentManager().beginTransaction();
        addNewLeadtransaction.replace(R.id.fragment_Container, addNewLeadfragment);
        addNewLeadtransaction.commit();
    }

    public void callingTashFragment(){
      //  toolbar_title.setText("Calling Task");
        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(4).setVisible(false);

        navigationView.getMenu().getItem(5).setVisible(false);
        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        if (role.equals("1")){
            toolbar_title.setText("All Leads");
            AllLeadFragment cfragment = new AllLeadFragment();
            FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
            dtransaction.replace(R.id.fragment_Container, cfragment);
            dtransaction.commit();
        }else {
            toolbar_title.setText("Calling Task");
            CallingTaskFragment cfragment = new CallingTaskFragment();
            FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
            dtransaction.replace(R.id.fragment_Container, cfragment);
            dtransaction.commit();
        }

    }

    public void trackerFragment(){

        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(4).setVisible(false);

        navigationView.getMenu().getItem(5).setVisible(false);
        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

             toolbar_title.setText("Tracker");
            TrackerFragment cfragment = new TrackerFragment();
            FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
            dtransaction.replace(R.id.fragment_Container, cfragment);
            dtransaction.commit();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.nav_location_wise:
                dashboardFragment();
                closeDrawer();
                break;
            case R.id.nav_calling_task:
                callingTashFragment();
                closeDrawer();
                break;
            case R.id.nav_tracker:
                trackerFragment();
                closeDrawer();
                break;

            case R.id.nav_lead_management_lead:
                navigationView.getMenu().getItem(4).setVisible(true);
                navigationView.getMenu().getItem(5).setVisible(true);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);

                navigationView.getMenu().getItem(9).setVisible(false);
                navigationView.getMenu().getItem(10).setVisible(false);
                break;

            case R.id.nav_add_lead:
                navigationView.getMenu().getItem(4).setVisible(true);
                navigationView.getMenu().getItem(5).setVisible(true);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);

                navigationView.getMenu().getItem(9).setVisible(false);
                navigationView.getMenu().getItem(10).setVisible(false);
                addNewLeadFragment();
                closeDrawer();
                break;

            case R.id.nav_assign_lead :
                navigationView.getMenu().getItem(4).setVisible(true);
                navigationView.getMenu().getItem(5).setVisible(true);
                navigationView.getMenu().getItem(6).setVisible(true);
                navigationView.getMenu().getItem(7).setVisible(true);

                navigationView.getMenu().getItem(9).setVisible(false);
                navigationView.getMenu().getItem(10).setVisible(false);

                break;

            case R.id.nav_assign_new_lead:
                navigationView.getMenu().getItem(4).setVisible(true);
                navigationView.getMenu().getItem(5).setVisible(true);
                navigationView.getMenu().getItem(6).setVisible(true);
                navigationView.getMenu().getItem(7).setVisible(true);

                navigationView.getMenu().getItem(9).setVisible(false);
                navigationView.getMenu().getItem(10).setVisible(false);

                closeDrawer();
                break;

            case R.id.nav_assign_transferred_lead :
                navigationView.getMenu().getItem(4).setVisible(true);
                navigationView.getMenu().getItem(5).setVisible(true);
                navigationView.getMenu().getItem(6).setVisible(true);
                navigationView.getMenu().getItem(7).setVisible(true);

                navigationView.getMenu().getItem(9).setVisible(false);
                navigationView.getMenu().getItem(10).setVisible(false);

                closeDrawer();
                break;

            case R.id.nav_customer_operation:
                navigationView.getMenu().getItem(0).setChecked(true);
                navigationView.getMenu().getItem(4).setVisible(false);
                navigationView.getMenu().getItem(5).setVisible(false);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);

                navigationView.getMenu().getItem(9).setVisible(true);
                navigationView.getMenu().getItem(10).setVisible(true);

                break;

            case R.id.nav_search_customer:

                navigationView.getMenu().getItem(4).setVisible(false);
                navigationView.getMenu().getItem(5).setVisible(false);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);

                navigationView.getMenu().getItem(9).setVisible(true);
                navigationView.getMenu().getItem(10).setVisible(true);

                break;
            case R.id.nav_edit_customer:
                navigationView.getMenu().getItem(4).setVisible(false);
                navigationView.getMenu().getItem(5).setVisible(false);
                navigationView.getMenu().getItem(6).setVisible(false);
                navigationView.getMenu().getItem(7).setVisible(false);

                navigationView.getMenu().getItem(9).setVisible(true);
                navigationView.getMenu().getItem(10).setVisible(true);

                break;
            case R.id.nav_change_password:
                startActivity(new Intent(HomeActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.nav_register_MobileNo:

                break;

            case R.id.nav_logout:
                session.logoutUser();
                // startActivity(new Intent(NavigationDrawer.this, LoginActivity.class));
                drawer.closeDrawers();
                break;
        }


     //   closeDrawer();
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void closeDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure(String message) {

    }

    @OnItemSelected(R.id.toolbarLocation_spinner)
    public void locationNameSelected(Spinner spinner, int position)
    {
        selectedLocation = spinner.getSelectedItem().toString();
        for (Map.Entry<String, String> e : locationMap.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            if(value.equals(selectedLocation)) {
                selectedLocationId = (String) key;
                Log.i("Selected CSE : ",selectedLocationId);
                loginPresenter.saveLocationInfo((String)key, (String) value, SharedPreferenceManager.getInstance(this));
            }
        }
        dashboardFragment();
    }

    @Override
    public void showLocationList(LoginBean loginBean) {
        ArrayList<String> locationArrayList = new ArrayList<>();
        locationArrayList.add("Select Location");
        if (loginBean.getSession_data().get(0).getProcess().size()>0) {
            for (int i = 0; i < loginBean.getSession_data().get(0).getProcess().size();i++)
            {
                if (loginBean.getSession_data().get(0).getProcess().get(i).getProcess_id().equals(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, ""))) {
                   for (int j = 0; j< loginBean.getSession_data().get(0).getProcess().get(i).getLocation().size(); j++) {
                       try {
                           locationArrayList.add(loginBean.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                           locationMap.put(loginBean.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation_id(), loginBean.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
                }
            }
        }

        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_white_text,locationArrayList);
        locationArrayAdapter.setDropDownViewResource(R.layout.spinner_checked_textview);
        toolbarLocation_spinner.setAdapter(locationArrayAdapter);
    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {

    }

    @Override
    public void showLocationDashboard(LocationDashboardBean jsonObject) {

    }
}

