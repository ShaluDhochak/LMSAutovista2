package com.example.user.lmsautovista.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import com.example.user.lmsautovista.Model.ProcessBean;
import com.example.user.lmsautovista.Presenter.DashboardPresenter;
import com.example.user.lmsautovista.Presenter.LoginPresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.SessionManagement;
import com.example.user.lmsautovista.View.Fragment.AddMessageFragment;
import com.example.user.lmsautovista.View.Fragment.AddNewLeadFragment;
import com.example.user.lmsautovista.View.Fragment.AllLeadFragment;
import com.example.user.lmsautovista.View.Fragment.AssignNewLeadFragment;
import com.example.user.lmsautovista.View.Fragment.AssignTransferLeadFragment;
import com.example.user.lmsautovista.View.Fragment.CallingTaskFragment;
import com.example.user.lmsautovista.View.Fragment.DSEReportFragment;
import com.example.user.lmsautovista.View.Fragment.DailyReportFragment;
import com.example.user.lmsautovista.View.Fragment.DashboardFragment;
import com.example.user.lmsautovista.View.Fragment.DseDailyReportFragment;
import com.example.user.lmsautovista.View.Fragment.DseDailyReportViewFragment;
import com.example.user.lmsautovista.View.Fragment.EditCustomerFragment;
import com.example.user.lmsautovista.View.Fragment.LeadReportFragment;
import com.example.user.lmsautovista.View.Fragment.MonthlyReportFragment;
import com.example.user.lmsautovista.View.Fragment.NewStockFragment;
import com.example.user.lmsautovista.View.Fragment.POCStockFragment;
import com.example.user.lmsautovista.View.Fragment.SearchCustomerFragment;
import com.example.user.lmsautovista.View.Fragment.TrackerFragment;
import com.example.user.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IView.LoginView{

    @BindView(R.id.toolbar1)
    public Toolbar toolbar1;

    public FloatingActionButton fab;

    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer1;

    @BindView(R.id.nav_view)
    public NavigationView navigationView1;

    @BindView(R.id.toolbar1_title)
    TextView toolbar1_title;

    @BindView(R.id.toolbarLocation1_spinner)
    Spinner toolbarLocation1_spinner;

    //@BindView(R.id.nameHeader)
    public TextView txtName;

  //  @BindView(R.id.departmentHeader)
    public TextView txtDepartment;
    String  selectedLocationId, selectedLocation;
    Map<String, String> locationMap = new HashMap<>();

    public View navHeader;

    SessionManagement session;

    public Handler mHandler;

    public  static final String TAG_HOME = "Home";
    public  static final String TAG_CUSTOMER_OPERATION = "Customer operation";
    public static final String TAG_SEARCH_CUSTOMER = "Search Customer";
    public static final String TAG_EDIT_CUSTOMER = "Edit Customer";
    public  static final String TAG_REPORT = "report";
    public  static final String TAG_MONTHLY_REPORT = "Monthly Report";
    public static final String TAG_LEAD_REPORT = "Lead Report";
    public static final String TAG_DSE_REPORT = "Dse Report";
    public static final String TAG_DSE_DAILY_REPORT="Dse Daily Report";
    public static final String TAG_DSE_DAILY_VIEW_REPORT = "DSE Daily Report";
    public static final String TAG_CALLING_TASK = "Calling Task";
    public static final String TAG_TRACKER = "lead_tracker";
    public  static final String TAG_ASSIGN_NEW_LEAD = "Assign New Lead";
    public static final String TAG_ASSIGN_TRANSFERRED_LEAD = "Assign Transferred Lead";
    public  static final String TAG_ADD_NEW_LEAD = "Add New Lead";
    public static final String TAG_STOCK = "Stock";
    public static final String TAG_NEW_CAR_STOCK = "New Car Stock";
    public static final String TAG_POC_STOCK = "POC Stock";
    public static final String TAG_ADD_MESSAGE = "Send Message to DSE";

    public static final String TAG_REGISTER_MOBILENO = "Register MobileNo";
    public static String CURRENT_TAG = TAG_HOME;

    public  String[] activityTitles;
    public static int navItemIndex = 0;

    LoginPresenter loginPresenter;
    DashboardPresenter dashboardPresenter;


    SharedPreferences pref;
    String roleStr, locationPref, userPref, process_id, process_name, user_name, processSelect, processNameSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);

       initialiseUI();

        loadNavHeader();
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadDashboardFragment();
        }
    }

    public void initialiseUI(){
        setSupportActionBar(toolbar1);

        loginPresenter = new LoginPresenter(this);
        dashboardPresenter = new DashboardPresenter(this);

        pref = PreferenceManager.getDefaultSharedPreferences(NavigationDrawer.this);
        roleStr = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "");
        locationPref =SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.LOCATION_SESSION, "");
         userPref = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.USER_ID, "");
         process_id = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "");
         process_name = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_NAME, "");
        user_name = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.USER_NAME, "");

        mHandler = new Handler();
        // Session class instance
        session = new SessionManagement(getApplicationContext());

        navHeader = navigationView1.getHeaderView(0);

        txtName = (TextView) navHeader.findViewById(R.id.nameHeader);
        txtDepartment = (TextView) navHeader.findViewById(R.id.departmentHeader);

        txtName.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.USER_NAME, ""));
        txtDepartment.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, ""));

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        loginPresenter.getLocationList(this);

        navigationViewDashboardDefault();
        navigationViewAssignLeadDefault();
        navigationViewReportMenuDefault();
        navigationViewSearchCustomer();
        navigationViewStockDefault();
        navigationViewAddMessageDefault();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //   getMenuInflater().inflate(R.menu.home, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home1, menu);
      /*  MenuItem currentItem = menu.findItem(R.id.action_current);
        MenuItem serviceItem = menu.findItem(R.id.action_service);
        MenuItem finanaceItem = menu.findItem(R.id.action_finance);
        MenuItem accessoriesItem = menu.findItem(R.id.action_accessories);
        MenuItem newCarItem = menu.findItem(R.id.action_new_car);
        MenuItem usedCarItem = menu.findItem(R.id.action_used_car);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        if (role.equals("1")){
            //admin
            currentItem.setVisible(false);
            serviceItem.setVisible(true);
            finanaceItem.setVisible(true);
            accessoriesItem.setVisible(true);
            newCarItem.setVisible(true);
            usedCarItem.setVisible(true);

        }else if ( (role.equals("13")) ||(role.equals("14")) ){
            currentItem.setVisible(false);
            serviceItem.setVisible(false);
            finanaceItem.setVisible(true);
            accessoriesItem.setVisible(false);
            newCarItem.setVisible(false);
            usedCarItem.setVisible(false);
            process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
            process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
        //    loadDashboardFragment();
        } else if ((role.equals("3")) || (role.equals("2")) || (role.equals("4"))) {
            currentItem.setVisible(false);
            serviceItem.setVisible(false);
            finanaceItem.setVisible(false);
            accessoriesItem.setVisible(false);
            newCarItem.setVisible(true);
            usedCarItem.setVisible(true);
            process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
            process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
        //    loadDashboardFragment();

        }else if ((role.equals("5"))){
            currentItem.setVisible(false);
            serviceItem.setVisible(false);
            finanaceItem.setVisible(false);
            accessoriesItem.setVisible(false);
            newCarItem.setVisible(true);
            usedCarItem.setVisible(false);
            process_id = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID_initial, "");;
            process_name = SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, "");;
            loginPresenter.saveProcessInfo(process_id, process_name, SharedPreferenceManager.getInstance(this));
            //loadDashboardFragment();
        }
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_finance) {
            processSelect = "1";
            processNameSelect = "Finance";
            loginPresenter.saveProcessInfo(processSelect, processNameSelect, SharedPreferenceManager.getInstance(this));


            txtDepartment.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            loginPresenter.getLocationList(this);
            loadDashboardFragment();
            return true;
        }else if(id == R.id.action_service) {
            processSelect = "4";
            processNameSelect = "Service";
            loginPresenter.saveProcessInfo(processSelect, processNameSelect, SharedPreferenceManager.getInstance(this));
           // dashboardPresenter.getLocationList(this);

            txtDepartment.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            loginPresenter.getLocationList(this);
            loadDashboardFragment();
            // deptName1_tv.setText("Insurance");
            return true;
        }else if(id == R.id.action_accessories) {
            processSelect = "5";
            processNameSelect = "Accessories";
            loginPresenter.saveProcessInfo(processSelect, processNameSelect, SharedPreferenceManager.getInstance(this));
         //   dashboardPresenter.getLocationList(this);

            txtDepartment.setText( SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            loginPresenter.getLocationList(this);
            loadDashboardFragment();
            return true;
        }else if(id == R.id.action_new_car) {
            processSelect = "6";
            processNameSelect = "New Car";
            loginPresenter.saveProcessInfo(processSelect, processNameSelect, SharedPreferenceManager.getInstance(this));

            txtDepartment.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            loginPresenter.getLocationList(this);
          //  dashboardPresenter.getLocationSpinnerList(this);
            loadDashboardFragment();
          //  dashboardPresenter.getLocationList(this);
            return true;
        }else if(id == R.id.action_used_car) {
            processSelect = "7";
            processNameSelect = "Used Car";
            loginPresenter.saveProcessInfo(processSelect, processNameSelect, SharedPreferenceManager.getInstance(this));

            txtDepartment.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
            loginPresenter.getLocationList(this);
          //  dashboardPresenter.getLocationSpinnerList(this);
            loadDashboardFragment();
            //
           // dashboardPresenter.getLocationList(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

 /*   private void getTimerForDSEDailyReport(){

        final String pRepTime;
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        String date=dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");

        final String time = dfTime.format(Calendar.getInstance().getTime());
        final String inTimeString = "09:00:00";
        final String outTimeString = "20:00:00";
        final String timeParse[] = time.split(":");
        final String inTimeParse[] = inTimeString.split(":");
        final String outTimeParse[] = outTimeString.split(":");

        //Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        if (roleStr.equals("4")) {
            try {
                //   Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
                Map<String, String> countLeadHashMap = new HashMap<>();
                countLeadHashMap.put("user_id", userPref);

                String url = LmsAPI.ROOT_URL + LmsAPI.DSE_MESSAGE_FOR_TIME_INTERVAL;
                final GSONRequest<DailyDseTimeTrackerBean> gsonRequest = new GSONRequest<>(
                        Request.Method.POST, url, DailyDseTimeTrackerBean.class, countLeadHashMap,
                        new Response.Listener<DailyDseTimeTrackerBean>() {
                            @Override
                            public void onResponse(DailyDseTimeTrackerBean response) {
                                if (response.getDaliy_dse_tracker_time().size() > 0) {

                                    String dbTimestring = String.valueOf(response.getDaliy_dse_tracker_time().get(0).getReport_time());
                                    String sbTimeParse[] = dbTimestring.split(":");

                                    //Time string for db time
                                    final int dbTimeHour,  dbTimeMin, dbTimesec;
                                    dbTimeHour = Integer.parseInt(sbTimeParse[0]);
                                    dbTimeMin = Integer.parseInt(sbTimeParse[1]);
                                    dbTimesec =Integer.parseInt(sbTimeParse[2]);

                                    //current Time
                                    int currentTimeHout, currenTmMin, CurrentTmSec;
                                    currentTimeHout = Integer.parseInt(timeParse[0]);
                                    currenTmMin = Integer.parseInt(timeParse[1]);
                                    CurrentTmSec =Integer.parseInt(timeParse[2]);

                                    //in time
                                    int inTimeHour, inTimeMin, inTimeSec;
                                    inTimeHour = Integer.parseInt(inTimeParse[0]);
                                    inTimeMin = Integer.parseInt(inTimeParse[1]);
                                    inTimeSec =Integer.parseInt(inTimeParse[2]);

                                    //outTime
                                    int outTimeHour, outTimeMin, outTimeSec;
                                    outTimeHour = Integer.parseInt(outTimeParse[0]);
                                    outTimeMin = Integer.parseInt(outTimeParse[1]);
                                    outTimeSec =Integer.parseInt(outTimeParse[2]);

                                    if (((dbTimeHour > inTimeHour) ) &&(dbTimeHour <outTimeHour))
                                    {
                                        //  if (((11+3)<= currentTimeHout) && (12 >= currenTmMin) ){
                                        if (((dbTimeHour+3)<= currentTimeHout) && (dbTimeMin >= currenTmMin) ){
                                            navItemIndex = 15 ;
                                            CURRENT_TAG =TAG_DSE_DAILY_REPORT ;
                                            loadDashboardFragment();
                                            Toast.makeText(NavigationDrawer.this, "Please fill DSE Daily Report First", Toast.LENGTH_SHORT).show();
                                        }else{
                                            // Toast.makeText(NavigationDrawer.this, "How it possible", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(NavigationDrawer.this, "Out of Time Interval, Not Possible", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    //current Time
                                    int currentTimeHout, currenTmMin, CurrentTmSec;
                                    currentTimeHout = Integer.parseInt(timeParse[0]);
                                    currenTmMin = Integer.parseInt(timeParse[1]);
                                    CurrentTmSec =Integer.parseInt(timeParse[2]);
                                    if ((currentTimeHout>= 12) &&(currenTmMin >= 00)){

                                        navItemIndex = 15;
                                        CURRENT_TAG = TAG_DSE_DAILY_REPORT;
                                        loadDashboardFragment();
                                        Toast.makeText(NavigationDrawer.this, "Please fill DSE Daily Report First", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                );
                Utilities.getRequestQueue(NavigationDrawer.this).add(gsonRequest);
            } catch (Exception e) {
                Toast.makeText(NavigationDrawer.this, " Null POinter Exception", Toast.LENGTH_SHORT).show();
            }
        }
    }
    */

    @Override
    public void onResume(){
        super.onResume();


        txtName.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.USER_NAME, ""));
        txtDepartment.setVisibility(View.GONE);
        if(roleStr.equals("1")){
            txtDepartment.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME_INITIAL, ""));
        }else{
            txtDepartment.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_NAME, ""));
        }

        navItemIndex = 0;
        CURRENT_TAG = TAG_HOME;
        loadDashboardFragment();
        loginPresenter.getLocationList(this);
     //   dashboardPresenter.getLocationSpinnerList(selectedLocationId, this);
        // getTimerForDSEDailyReport();
    }

    public void navigationViewAddMessageDefault(){
        if (roleStr.equals("2")){
            navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
        }else{
            navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
        }
    }

    public void navigationViewDashboardDefault(){
        navigationView1.getMenu().findItem(R.id.nav_location_wise).setVisible(true);
    }

    public void navigationViewStockDefault(){
        navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
    }

    public void navigationViewAssignLeadDefault(){
        navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
    }

    public void navigationViewReportMenuDefault(){
        navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
    }

    public void navigationViewSearchCustomer(){
        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
    }

    private void loadNavHeader() {
        try {
            // if (!((LoginActivity.loginBean.getSession_data()) == null)) {
            if (!(userPref.equals(""))) {
                txtName.setText(user_name);
            }
            if (!(process_name.equals(""))) {
                txtDepartment.setText(process_name);
            }
            //  } else {
            //      Toast.makeText(NavigationDrawer.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            //  }
        }catch (Exception e){

        }
    }

    public void loadDashboardFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer1.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawer1.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                toolbar1_title.setText("Dashboard");
              //  getTimerForDSEDailyReport();
                DashboardFragment locationWiseFragment = new DashboardFragment();
                return locationWiseFragment;
            case 1:

              //  getTimerForDSEDailyReport();
                if ((roleStr.equals("2")) ||(roleStr.equals("3")) ) {
                    toolbar1_title.setText("Calling Task");
                    CallingTaskFragment callingTaskFragment = new CallingTaskFragment();
                    return callingTaskFragment;
                }else if ((roleStr.equals("4")) ||(roleStr.equals("5")) ) {
                    toolbar1_title.setText("Calling Task");
                    CallingTaskFragment callingTaskFragment = new CallingTaskFragment();
                    return callingTaskFragment;
                }else {
                    toolbar1_title.setText("All Lead");
                    AllLeadFragment allLeadFragment = new AllLeadFragment();
                    return allLeadFragment;
                }
            case 2:
                toolbar1_title.setText("Search Customer");
              //  getTimerForDSEDailyReport();
                SearchCustomerFragment customerOperationFragment = new SearchCustomerFragment();
                return customerOperationFragment;
            case 3:
                toolbar1_title.setText("Tracker");
             //   getTimerForDSEDailyReport();
                TrackerFragment trackerHomeFragment= new TrackerFragment();
                return trackerHomeFragment;
            case 4:
                toolbar1_title.setText("Daily Report");
            //    getTimerForDSEDailyReport();
                DailyReportFragment dailyReportFragment = new DailyReportFragment();
                return dailyReportFragment;
            case 5:
                toolbar1_title.setText("Monthly Report");
             //   getTimerForDSEDailyReport();
                MonthlyReportFragment monthlyReportFragment = new MonthlyReportFragment();
                return monthlyReportFragment;
            case 6:
                toolbar1_title.setText("DSE Wise Report");
             //   getTimerForDSEDailyReport();
                DSEReportFragment dseWiseReportFragment = new DSEReportFragment();
                return dseWiseReportFragment;
            case 7:
                toolbar1_title.setText("Lead Report");
            //    getTimerForDSEDailyReport();
                LeadReportFragment leadReportFragment = new LeadReportFragment();
                return leadReportFragment;
            case 8:
                toolbar1_title.setText("Add New Lead");
             //   getTimerForDSEDailyReport();
                AddNewLeadFragment addLeadFragment = new AddNewLeadFragment();
                return addLeadFragment;
            case 9:
                toolbar1_title.setText("Assign New Lead");
             //   getTimerForDSEDailyReport();
                AssignNewLeadFragment assignNewLeadFragment = new AssignNewLeadFragment();
                return assignNewLeadFragment;
            case 10:
                toolbar1_title.setText("Assign Transferred Lead");
             //   getTimerForDSEDailyReport();
                AssignTransferLeadFragment assignTransferedLeadFragment = new AssignTransferLeadFragment();
                return assignTransferedLeadFragment;
            case 11:
                toolbar1_title.setText("Edit Customer Operation");
            //    getTimerForDSEDailyReport();
                EditCustomerFragment editCustomerOperationFragment = new EditCustomerFragment();
                return editCustomerOperationFragment;
            case 12:
                toolbar1_title.setText("New Stock");
             //   getTimerForDSEDailyReport();
                NewStockFragment newCarStockFragment = new NewStockFragment();
                return newCarStockFragment;
            case 13:
                toolbar1_title.setText("POC Stock");
            //    getTimerForDSEDailyReport();
                POCStockFragment pocStockFragment = new POCStockFragment();
                return pocStockFragment;
            case 14:
                toolbar1_title.setText("Add Message");
             //   getTimerForDSEDailyReport();
                AddMessageFragment addMessageFragment = new AddMessageFragment();
                return addMessageFragment;
            case 15:
                toolbar1_title.setText("DSE Daily Report");
             //   getTimerForDSEDailyReport();
                DseDailyReportFragment dseDailyReportFragment = new DseDailyReportFragment();
                return dseDailyReportFragment;
            case 16:
                toolbar1_title.setText("DSE Daily Report View");
             //   getTimerForDSEDailyReport();
                DseDailyReportViewFragment dseDailyReportViewFragment = new DseDailyReportViewFragment();
                return dseDailyReportViewFragment;

            default:
                return new DashboardFragment();
        }
    }

    public void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView1.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_location_wise:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_calling_task:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_CALLING_TASK;
                        loadDashboardFragment();
                        break;

                    case R.id.nav_customer_operation:
                        navigationViewAssignLeadDefault();
                        navigationViewSearchCustomerVisibility();
                        navigationViewDashboardDefault();
                        navigationViewReportMenuDefault();
                        navigationViewStockDefault();
                        break;

                    case R.id.nav_search_customer:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SEARCH_CUSTOMER;
                        loadDashboardFragment();
                        break;

                    case R.id.nav_edit_customer:
                        navItemIndex = 11;
                        CURRENT_TAG = TAG_EDIT_CUSTOMER;
                        loadDashboardFragment();
                        break;

                    case R.id.nav_report:
                        navigationViewAssignLeadDefault();
                        setReportMenuVisibility();
                        navigationViewStockDefault();
                        navigationViewSearchCustomer();
                        navigationViewDashboardDefault();
                        break;

                    case R.id.nav_tracker:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_TRACKER;
                        loadDashboardFragment();
                        break;

                    case R.id.nav_Daily_Report:
                        navItemIndex=4;
                        CURRENT_TAG = TAG_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_Monthly_Report:
                        navItemIndex=5;
                        CURRENT_TAG = TAG_MONTHLY_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_Dsewise_report:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_DSE_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_DseDailywise_report:
                        navItemIndex = 15 ;
                        CURRENT_TAG =TAG_DSE_DAILY_REPORT ;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_DseDailyReportView_report:
                        navItemIndex = 16;
                        CURRENT_TAG = TAG_DSE_DAILY_VIEW_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_Lead_report:
                        navItemIndex=7;
                        CURRENT_TAG = TAG_LEAD_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_lead_management_lead:
                        setAllRightsCondition();
                        navigationViewStockDefault();
                        navigationViewReportMenuDefault();
                        navigationViewDashboardDefault();
                        navigationViewSearchCustomer();
                        break;

                    case R.id.nav_add_lead:
                        navItemIndex=8;
                        CURRENT_TAG = TAG_ADD_NEW_LEAD;
                        loadDashboardFragment();
                        break;

                    case R.id.nav_assign_lead:
                        setRightConditionForAssign();
                        navigationViewStockDefault();
                        navigationViewReportMenuDefault();
                        navigationViewDashboardDefault();
                        navigationViewSearchCustomer();
                        break;

                    case R.id.nav_assign_new_lead:
                        navItemIndex=9;
                        CURRENT_TAG = TAG_ASSIGN_NEW_LEAD;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_assign_transferred_lead:
                        navItemIndex = 10;
                        CURRENT_TAG =TAG_ASSIGN_TRANSFERRED_LEAD;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_stock:
                        navigationViewAssignLeadDefault();
                        navigationViewReportMenuDefault();
                        navigationViewDashboardDefault();
                        navigationViewSearchCustomer();
                        navigationViewStockVisibility();
                        break;

                    case R.id.nav_new_car_stock:
                        navItemIndex = 12;
                        CURRENT_TAG =TAG_NEW_CAR_STOCK;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_poc_stock:
                        navItemIndex = 13;
                        CURRENT_TAG =TAG_POC_STOCK;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_send_message:
                        navItemIndex = 14;
                        CURRENT_TAG = TAG_ADD_MESSAGE;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_register_MobileNo:
                      //  startActivity(new Intent(NavigationDrawer.this, RegisterPushActivity.class));
                        drawer1.closeDrawers();
                        return true;

                    case R.id.nav_change_password:
                        startActivity(new Intent(NavigationDrawer.this, ChangePasswordActivity.class));
                        drawer1.closeDrawers();
                        return true;

                    case R.id.nav_logout:
                        session.logoutUser();
                        // startActivity(new Intent(NavigationDrawer.this, LoginActivity.class));
                        drawer1.closeDrawers();
                        return true;

                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                return true;
            }
        });
        SetDrawerAction();
    }

    public void SetDrawerAction(){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer1, toolbar1, R.string.openDrawer, R.string.closeDrawer)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer1.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void navigationViewSearchCustomerVisibility(){
        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
    }

    public void navigationViewStockVisibility(){
        navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
        navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
    }
    //setRightConditionfor assign
    public void setRightConditionForAssign(){
        if (roleStr.equals("1")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
        }else if(roleStr.equals("2")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
        }else if (roleStr.equals("3")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
        }else if (roleStr.equals("4")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
        }else{
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
        }
    }

    //Setting the right condition for Assign Lead And Add Lead
    public void setAllRightsCondition(){
        if (roleStr.equals("1")) {
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
        } else   if (roleStr.equals("2")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
        }else   if (roleStr.equals("3")) {
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
        }else  if (roleStr.equals("4")) {
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
        }else if (roleStr.equals("5")) {
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
        }else {
            navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
        }
    }

    //Setting the right condition for report
    public void setReportMenuVisibility(){
        if (roleStr.equals("1")) {
            navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("2")) {
            navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("3")) {
            navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
        }else if (roleStr.equals("5")) {
            navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("4")) {
            navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawers();
            finish();
            return;
        }
        if(navItemIndex!=0){
            startActivity(new Intent(this, NavigationDrawer.class));
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
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

    @OnItemSelected(R.id.toolbarLocation1_spinner)
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
               // dashboardPresenter.getLocationSpinnerList(selectedLocationId, this);
            }
        }

        loadDashboardFragment();

    }

    @Override
    public void showLocationList(LoginBean jsonObject) {
        ArrayList<String> locationArrayList = new ArrayList<>();
        locationArrayList.clear();
        locationArrayList.add("Select Location");
        if (jsonObject.getSession_data().get(0).getProcess().size()>0) {
            for (int i = 0; i < jsonObject.getSession_data().get(0).getProcess().size();i++)
            {
                if (jsonObject.getSession_data().get(0).getProcess().get(i).getProcess_id().equals(SharedPreferenceManager.getInstance(this).getPreference(Constants.PROCESS_ID, ""))) {
                    for (int j = 0; j< jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().size(); j++) {
                        try {
                            locationArrayList.add(jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                            locationMap.put(jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation_id(), jsonObject.getSession_data().get(0).getProcess().get(i).getLocation().get(j).getLocation());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_white_text,locationArrayList);
        locationArrayAdapter.setDropDownViewResource(R.layout.spinner_checked_textview);
        toolbarLocation1_spinner.setAdapter(locationArrayAdapter);
    }

    @Override
    public void ShowDashboardCount(DashboardCountBean jsonObject) {

    }

    @Override
    public void showLocationDashboard(LocationDashboardBean jsonObject) {

    }

    @Override
    public void showProcessDashboard(ProcessBean jsonObject) {

    }


}
