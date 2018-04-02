package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Presenter.LoginPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.View.Fragment.AddNewLeadFragment;
import com.example.user.lmsautovista.View.Fragment.AllLeadFragment;
import com.example.user.lmsautovista.View.Fragment.CallingTaskFragment;
import com.example.user.lmsautovista.View.Fragment.DashboardFragment;
import com.example.user.lmsautovista.View.Fragment.TrackerFragment;
import com.example.user.lmsautovista.View.IView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IView.LoginView {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;


    public View navHeader;
    public TextView deptName1_tv,userName_tv;
    String process_id, process_name;
    LoginPresenter loginPresenter;

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

        loginPresenter = new LoginPresenter(this);
        userName_tv = (TextView) navHeader.findViewById(R.id.userName_tv);
        deptName1_tv = (TextView) navHeader.findViewById(R.id.deptName1_tv);

        userName_tv.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.USER_NAME, ""));
        deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, ""));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        dashboardFragment();

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
        MenuItem serviceItem = menu.findItem(R.id.action_service);
        MenuItem finanaceItem = menu.findItem(R.id.action_finance);
        MenuItem accessoriesItem = menu.findItem(R.id.action_accessories);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        if (role.equals("1")){
            //admin
            serviceItem.setVisible(true);
            finanaceItem.setVisible(true);
            accessoriesItem.setVisible(true);

        }else if ((role.equals("3")) ||(role.equals("4")) || (role.equals("5")) ||(role.equals("6")) ||(role.equals("7")) ||(role.equals("8"))){
            serviceItem.setVisible(false);
            finanaceItem.setVisible(false);
            accessoriesItem.setVisible(false);
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
           // deptName1_tv.setText("Finance");
            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            dashboardFragment();
            return true;
        }else if(id == R.id.action_service) {
            process_id = "4";
            process_name = "Service";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            dashboardFragment();
           // deptName1_tv.setText("Insurance");
            return true;
        }else if(id == R.id.action_accessories) {
            process_id = "5";
            process_name = "Accessories";
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            deptName1_tv.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
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

                break;
            case R.id.nav_register_MobileNo:

                break;

            case R.id.nav_logout:

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
}

