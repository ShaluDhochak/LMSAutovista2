package com.excell.lms.lmsautovista.View.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.Model.DailyDseTimeTrackerBean;
import com.excell.lms.lmsautovista.Model.DashboardCountBean;
import com.excell.lms.lmsautovista.Model.LocationDashboardBean;
import com.excell.lms.lmsautovista.Model.LocationWiseDashboardCountBean;
import com.excell.lms.lmsautovista.Model.LoginBean;
import com.excell.lms.lmsautovista.Presenter.DashboardPresenter;
import com.excell.lms.lmsautovista.Presenter.LoginPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Config;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.Utils.NotificationUtils;
import com.excell.lms.lmsautovista.Utils.SessionManagement;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.View.Fragment.AddMessageFragment;
import com.excell.lms.lmsautovista.View.Fragment.AddNewLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.AllLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.AssignNewLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.AssignTransferLeadFragment;
import com.excell.lms.lmsautovista.View.Fragment.CallingTaskFragment;
import com.excell.lms.lmsautovista.View.Fragment.DSEReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.DailyProductivityReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.DailyReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.DashboardFragment;
import com.excell.lms.lmsautovista.View.Fragment.DseDailyReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.DseDailyReportViewFragment;
import com.excell.lms.lmsautovista.View.Fragment.EditCustomerFragment;
import com.excell.lms.lmsautovista.View.Fragment.EvaluationCallingTaskFragment;
import com.excell.lms.lmsautovista.View.Fragment.LeadReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.LocationWiseFragment;
import com.excell.lms.lmsautovista.View.Fragment.MonthlyReportFragment;
import com.excell.lms.lmsautovista.View.Fragment.NewCarStock2Fragment;
import com.excell.lms.lmsautovista.View.Fragment.POCStockFragment;
import com.excell.lms.lmsautovista.View.Fragment.SearchCustomerFragment;
import com.excell.lms.lmsautovista.View.Fragment.TrackerFragment;
import com.excell.lms.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IView.LoginView{

    //RegisterDevice Code
    private static final String TAG = RegisterPushActivity.class.getSimpleName();
    JSONParser jsonParser = new JSONParser();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    SharedPreferences sharedPref;
    String regId,message ;

    @BindView(R.id.toolbar1)
    public Toolbar toolbar1;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer1;
    @BindView(R.id.nav_view)
    public NavigationView navigationView1;

    @BindView(R.id.toolbar1_title)
    TextView toolbar1_title;
    @BindView(R.id.toolbarLocation1_spinner)
    Spinner toolbarLocation1_spinner;

    public TextView txtName,txtDepartment;
    public FloatingActionButton fab;
    //string for navigation darwer option
    String assignNewLead, assignTransLead, addNewLead;

    String  selectedLocationId, selectedLocation;
    Map<String, String> locationMap = new HashMap<>();

    public View navHeader;
    SessionManagement session;
    public Handler mHandler;

    public  static final String TAG_HOME = "Home";
    public static final String TAG_SEARCH_CUSTOMER = "Search Customer";
    public static final String TAG_EDIT_CUSTOMER = "Edit Customer";
    public  static final String TAG_REPORT = "report";
    public  static final String TAG_MONTHLY_REPORT = "Monthly Report";
    public static final String TAG_LEAD_REPORT = "Lead Report";
    public static final String TAG_DSE_REPORT = "Dse Report";
    public static final String TAG_DSE_DAILY_REPORT="Dse Daily Report";
    public static final String TAG_DSE_DAILY_VIEW_REPORT = "DSE Daily Report";
    public static final String TAG_DAILY_PRODUCTIVITY_REPORT = "Daily Productivity Report";
    public static final String TAG_CALLING_TASK = "Calling Task";
    public static final String TAG_TRACKER = "lead_tracker";
    public  static final String TAG_ASSIGN_NEW_LEAD = "Assign New Lead";
    public static final String TAG_ASSIGN_TRANSFERRED_LEAD = "Assign Transferred Lead";
    public  static final String TAG_ADD_NEW_LEAD = "Add New Lead";
    public static final String TAG_NEW_CAR_STOCK = "New Car Stock";
    public static final String TAG_POC_STOCK = "POC Stock";
    public static final String TAG_ADD_MESSAGE = "Send Message to DSE";

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

        checkDeviceRegistration();
        initialiseUI();
        loadNavHeader();
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadDashboardFragment();
        }
    }

    //code for device registration
    public void checkDeviceRegistration(){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(NavigationDrawer.this);
        userPref = sharedPref.getString("user_id", "");
        notificationdata();
    }

    //method for Device registration
    public void notificationdata(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Objects.equals(intent.getAction(), Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                } else if (Objects.equals(intent.getAction(), Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NavigationDrawer.this)
                            .setSmallIcon(R.drawable.notification_logo)
                            .setContentTitle("LMS autovista")
                            .setContentText(message);

                    Intent notificationIntent = new Intent(NavigationDrawer.this, NavigationDrawer.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(NavigationDrawer.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);

                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    assert manager != null;
                    manager.notify(0, builder.build());
                }
            }
        };
        displayFirebaseRegId();
    }

    //method for registration device
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);
        if (!TextUtils.isEmpty(regId))
            new newRegistration().execute();
        else
            new newRegistration().execute();
        //    token_txtView.setText("Firebase Reg Id is not received yet!");
    }

    private class newRegistration extends AsyncTask<String, JSONObject, JSONObject> {
        String token, user_id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id = userPref;
            token = regId;
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.USER_ID, "")));
            params.add(new BasicNameValuePair("token", token));
            String url =  Constants.BASE_URL +  "insertToken";
            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                message = json.getString(TAG_MESSAGE);
                if (success == 1){
                    return json;
                }
                else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject response) {
            try {
                if (!(response == null)) {
                 }
                else {
                }
            } catch (Exception ignored) {
            }
        }
    }

    public void initialiseUI(){
        setSupportActionBar(toolbar1);

        loginPresenter = new LoginPresenter(this);
        dashboardPresenter = new DashboardPresenter(this);

        //sharedPeference
        sharedPredFn();

        mHandler = new Handler();
        // Session class instance
        session = new SessionManagement(getApplicationContext());

        navHeader = navigationView1.getHeaderView(0);

        txtName = (TextView) navHeader.findViewById(R.id.nameHeader);
        txtDepartment = (TextView) navHeader.findViewById(R.id.departmentHeader);

        txtName.setText(SharedPreferenceManager.getInstance(this).getPreference(Constants.USER_NAME, ""));
        txtDepartment.setVisibility(View.GONE);
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

    public void sharedPredFn(){
        pref = PreferenceManager.getDefaultSharedPreferences(NavigationDrawer.this);
        roleStr = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "");
        locationPref =SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.LOCATION_SESSION, "");
        userPref = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.USER_ID, "");
        process_id = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "");
        process_name = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_NAME, "");
        user_name = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.USER_NAME, "");
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

    private void getTimerForDSEDailyReport(){

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
        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            try {
                //   Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
                Map<String, String> countLeadHashMap = new HashMap<>();
                countLeadHashMap.put("user_id", userPref);

                String url = Constants.BASE_URL + Constants.DSE_MESSAGE_FOR_TIME_INTERVAL;
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
                                     //        Toast.makeText(NavigationDrawer.this, "How it possible", Toast.LENGTH_SHORT).show();
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
            //   Toast.makeText(NavigationDrawer.this, " Null POinter Exception", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
        NotificationUtils.clearNotifications(getApplicationContext());

        initialiseUI();
        loadNavHeader();
        setUpNavigationView();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    public void navigationViewAddMessageDefault(){
        String sendMessage;
        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
               }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else  if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                sendMessage = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("send_message_to_dse", "");
                if (sendMessage.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(true);
                } else if (sendMessage.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_send_message).setVisible(false);
                }
            }
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
        navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);
    }

    public void navigationViewSearchCustomer(){
        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
    }

    private void loadNavHeader() {
        try {
            if (!(userPref.equals(""))) {
                txtName.setText(user_name);
            }
            if (!(process_name.equals(""))) {
                txtDepartment.setText(process_name);
            }
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
               // getTimerForDSEDailyReport();
            //    DashboardFragment locationWiseFragment = new DashboardFragment();
                LocationWiseFragment locationWiseFragment = new LocationWiseFragment();
                return locationWiseFragment;
            case 1:
              //  getTimerForDSEDailyReport();
                if ((roleStr.equals("2")) ||(roleStr.equals("3")) ) {
                    if (process_id.equals("9")){
                        toolbar1_title.setText("Calling Task");
                        EvaluationCallingTaskFragment evaluationCallingTaskFragment = new EvaluationCallingTaskFragment();
                        return evaluationCallingTaskFragment;

                    }else{
                        toolbar1_title.setText("Calling Task");
                        CallingTaskFragment callingTaskFragment = new CallingTaskFragment();
                        return callingTaskFragment;
                    }
                }else if ((roleStr.equals("4")) ||(roleStr.equals("5")) ) {
                    toolbar1_title.setText("Calling Task");
                    CallingTaskFragment callingTaskFragment = new CallingTaskFragment();
                    return callingTaskFragment;
                }else if ((roleStr.equals("15")) ||(roleStr.equals("16")) ) {
                    toolbar1_title.setText("Calling Task");
                    EvaluationCallingTaskFragment evaluationCallingTaskFragment = new EvaluationCallingTaskFragment();
                    return evaluationCallingTaskFragment;
                }else {
                    toolbar1_title.setText("All Lead");
                    AllLeadFragment allLeadFragment = new AllLeadFragment();
                    return allLeadFragment;
                }
            case 2:
                toolbar1_title.setText("Search Customer");
           //     getTimerForDSEDailyReport();
                SearchCustomerFragment customerOperationFragment = new SearchCustomerFragment();
                return customerOperationFragment;
            case 3:
                toolbar1_title.setText("Tracker");
          //      getTimerForDSEDailyReport();
                TrackerFragment trackerHomeFragment= new TrackerFragment();
                return trackerHomeFragment;
            case 4:
                toolbar1_title.setText("Daily Report");
          //      getTimerForDSEDailyReport();
                DailyReportFragment dailyReportFragment = new DailyReportFragment();
                return dailyReportFragment;
            case 5:
                toolbar1_title.setText("Monthly Report");
        //        getTimerForDSEDailyReport();
                MonthlyReportFragment monthlyReportFragment = new MonthlyReportFragment();
                return monthlyReportFragment;
            case 6:
                toolbar1_title.setText("DSE Wise Report");
        //        getTimerForDSEDailyReport();
                DSEReportFragment dseWiseReportFragment = new DSEReportFragment();
                return dseWiseReportFragment;
            case 7:
                toolbar1_title.setText("Lead Report");
         //       getTimerForDSEDailyReport();
                LeadReportFragment leadReportFragment = new LeadReportFragment();
                return leadReportFragment;
            case 8:

                if (process_id.equals("9")){
                    toolbar1_title.setText("Add New Campaign");
                }else{
                    toolbar1_title.setText("Add New Lead");
                }
               //      getTimerForDSEDailyReport();
                AddNewLeadFragment addLeadFragment = new AddNewLeadFragment();
                return addLeadFragment;
            case 9:
                if (process_id.equals("9")){
                    toolbar1_title.setText("Assign New Campaign");
                }else{
                    toolbar1_title.setText("Assign New Lead");
                }

        //        getTimerForDSEDailyReport();
                AssignNewLeadFragment assignNewLeadFragment = new AssignNewLeadFragment();
                return assignNewLeadFragment;
            case 10:
                toolbar1_title.setText("Assign Transferred Lead");
                //  getTimerForDSEDailyReport();
                AssignTransferLeadFragment assignTransferedLeadFragment = new AssignTransferLeadFragment();
                return assignTransferedLeadFragment;
            case 11:
                toolbar1_title.setText("Edit Customer Operation");
                //  getTimerForDSEDailyReport();
                EditCustomerFragment editCustomerOperationFragment = new EditCustomerFragment();
                return editCustomerOperationFragment;
            case 12:
                toolbar1_title.setText("New Stock");
                //  getTimerForDSEDailyReport();
                NewCarStock2Fragment newCarStockFragment = new NewCarStock2Fragment();
                return newCarStockFragment;
            case 13:
                toolbar1_title.setText("POC Stock");
                //  getTimerForDSEDailyReport();
                POCStockFragment pocStockFragment = new POCStockFragment();
                return pocStockFragment;
            case 14:
                toolbar1_title.setText("Add Message");
                // getTimerForDSEDailyReport();
                AddMessageFragment addMessageFragment = new AddMessageFragment();
                return addMessageFragment;
            case 15:
                toolbar1_title.setText("DSE Daily Report");
                // getTimerForDSEDailyReport();
                DseDailyReportFragment dseDailyReportFragment = new DseDailyReportFragment();
                return dseDailyReportFragment;
            case 16:
                toolbar1_title.setText("DSE Daily Report View");
                // getTimerForDSEDailyReport();
                DseDailyReportViewFragment dseDailyReportViewFragment = new DseDailyReportViewFragment();
                return dseDailyReportViewFragment;

            case 17:
                toolbar1_title.setText("Daily Productivity Report ");
                // getTimerForDSEDailyReport();
                DailyProductivityReportFragment dailyProductivityReportFragment = new DailyProductivityReportFragment();
                return dailyProductivityReportFragment;
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
                        navigationViewAssignLeadDefault();
                        navigationViewDashboardDefault();
                        navigationViewReportMenuDefault();
                        navigationViewStockDefault();
                        navigationViewSearchCustomer();
                        return true;

                    case R.id.nav_calling_task:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_CALLING_TASK;
                        loadDashboardFragment();
                        navigationViewSearchCustomer();
                        navigationViewAssignLeadDefault();
                        navigationViewDashboardDefault();
                        navigationViewReportMenuDefault();
                        navigationViewStockDefault();
                        navigationViewSearchCustomer();
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
                        navigationViewSearchCustomer();
                        navigationViewAssignLeadDefault();
                        navigationViewDashboardDefault();
                        navigationViewReportMenuDefault();
                        navigationViewStockDefault();
                        navigationViewSearchCustomer();
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

                    case R.id.nav_Daily_Productivity_Report:
                        navItemIndex = 17;
                        CURRENT_TAG = TAG_DAILY_PRODUCTIVITY_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_Lead_report:
                        navItemIndex=7;
                        CURRENT_TAG = TAG_LEAD_REPORT;
                        loadDashboardFragment();
                        return true;

                    case R.id.nav_lead_management_lead:
                        setAllRightLeadManagementCondition();
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

                        navigationViewSearchCustomer();
                        navigationViewAssignLeadDefault();
                        navigationViewDashboardDefault();
                        navigationViewReportMenuDefault();
                        navigationViewStockDefault();
                        navigationViewSearchCustomer();
                        return true;

                   /* case R.id.nav_register_MobileNo:
                        startActivity(new Intent(NavigationDrawer.this, RegisterPushActivity.class));
                        drawer1.closeDrawers();
                        return true;
                    */
                    case R.id.nav_change_password:
                        startActivity(new Intent(NavigationDrawer.this, ChangePasswordActivity.class));
                        drawer1.closeDrawers();
                        return true;

                    case R.id.nav_logout:
                        session.logoutUser();
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
        String searchCustomer, editCustomer;

        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }

            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }

            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }

            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }

            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                if (searchCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                } else if (searchCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                }

                if (editCustomer.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                } else if (editCustomer.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
                if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                    searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                    editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                    if (searchCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                    } else if (searchCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                    }

                    if (editCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                    } else if (editCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                    }

                } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                    searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                    editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                    if (searchCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                    } else if (searchCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                    }

                    if (editCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                    } else if (editCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                    }
                } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                    searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                    editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                    if (searchCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                    } else if (searchCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                    }

                    if (editCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                    } else if (editCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                    }
                }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                    searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                    editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                    if (searchCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                    } else if (searchCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                    }

                    if (editCustomer.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                    } else if (editCustomer.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                    }
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
                    if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                        searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                        editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");
                        if (searchCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                        } else if (searchCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                        }

                        if (editCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                        } else if (editCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                        }

                    } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                        searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                        editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                        if (searchCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                        } else if (searchCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                        }

                        if (editCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                        } else if (editCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                        }
                    } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                        searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                        editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                        if (searchCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                        } else if (searchCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                        }

                        if (editCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                        } else if (editCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                        }
                    }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                        searchCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("search_customer_view", "");
                        editCustomer = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("edit_customer_view", "");

                        if (searchCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(true);
                        } else if (searchCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_search_customer).setVisible(false);
                        }

                        if (editCustomer.equals("1")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(true);
                        } else if (editCustomer.equals("0")) {
                            navigationView1.getMenu().findItem(R.id.nav_edit_customer).setVisible(false);
                        }
                    }
                }
    }

    public void navigationViewStockVisibility(){
        String newstock, userStock;
        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                 if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                newstock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_newcar_view", "");
                if (newstock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(true);
                } else if (newstock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_usedcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                userStock = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("check_stock_evaluationcar_view", "");
                if (userStock.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(true);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                } else if (userStock.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_poc_stock).setVisible(false);
                    navigationView1.getMenu().findItem(R.id.nav_new_car_stock).setVisible(false);
                }
            }
        }
    }

    //setRightConditionfor assign
    public void setRightConditionForAssign(){
        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
               // navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
             //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
            }
        }else if(SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }

            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
             }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
                if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                    if (assignNewLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    } else if (assignNewLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if (assignTransLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    } else if (assignTransLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }

                } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                    if (assignNewLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    } else if (assignNewLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if (assignTransLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    } else if (assignTransLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }
                }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                    if(assignNewLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    }else if (assignNewLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if(assignTransLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    }else if (assignTransLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }
                }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                    //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                    if(assignNewLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    }else if (assignNewLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
                if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                    if (assignNewLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    } else if (assignNewLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if (assignTransLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    } else if (assignTransLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }

                } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                    if (assignNewLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    } else if (assignNewLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if (assignTransLead.equals("1")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    } else if (assignTransLead.equals("0")) {
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }
                }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                    assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                    if(assignNewLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    }else if (assignNewLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                    if(assignTransLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                    }else if (assignTransLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                    }
                }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                    assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                    //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                    if(assignNewLead.equals("1")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                    }else if (assignNewLead.equals("0")){
                        navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                    }
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
                        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                            assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                            assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                            if (assignNewLead.equals("1")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                            } else if (assignNewLead.equals("0")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                            }
                            if (assignTransLead.equals("1")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                            } else if (assignTransLead.equals("0")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                            }
                        } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                            assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                            assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                            if (assignNewLead.equals("1")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                            } else if (assignNewLead.equals("0")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                            }
                            if (assignTransLead.equals("1")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                            } else if (assignTransLead.equals("0")) {
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                            }
                        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                            assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                            assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                            if(assignNewLead.equals("1")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                            }else if (assignNewLead.equals("0")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                            }
                            if(assignTransLead.equals("1")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                            }else if (assignTransLead.equals("0")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                            }
                        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                            navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                            assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                            //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                            if(assignNewLead.equals("1")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                            }else if (assignNewLead.equals("0")){
                                navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                            }
                        }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_newcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_transfer_lead_newcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            } else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_usedcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_usedcar_view", "");

                if (assignNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                } else if (assignNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if (assignTransLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                } else if (assignTransLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_lead_evaluationcar_view", "");
                assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
                if(assignTransLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(true);
                }else if (assignTransLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")){
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                assignNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");
                //   assignTransLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("assign_tracferred_lead_evaluationcar_view", "");
                navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);

                if(assignNewLead.equals("1")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(true);
                }else if (assignNewLead.equals("0")){
                    navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);
                }
            }
        }
        }

    //Setting the right condition for Assign Lead And Add Lead
    public void setAllRightLeadManagementCondition(){
        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
               navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }

        } else   if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
               navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }

        }else   if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }else  if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                 navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }else {
            navigationView1.getMenu().findItem(R.id.nav_assign_transferred_lead).setVisible(false);
            navigationView1.getMenu().findItem(R.id.nav_assign_new_lead).setVisible(false);

            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_newcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_usedcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_evaluationcar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                navigationView1.getMenu().findItem(R.id.nav_assign_lead).setVisible(true);
                addNewLead = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_new_lead_campaigncar_view", "");

                if (addNewLead.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(true);
                } else if (addNewLead.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_add_lead).setVisible(false);
                }
            }
        }
    }

    //Setting the right condition for report
    public void setReportMenuVisibility(){
        String dailyReport, monthlyReport, dsewiseReport, leadReport, dseDailyreport, dseDailyReportView;

        if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("1")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                }else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                }else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                }else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                }else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                }else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                }else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }

        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("2")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }

        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("3")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");


                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }

        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("5")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("4")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }

        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("15")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
            else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
        }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.ROLE_ID, "").equals("16")) {
            if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("6") ) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }

            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("7")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("8")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(false);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }else if (SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference(Constants.PROCESS_ID, "").equals("9")) {
                dailyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("daily_report_view", "");
                monthlyReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("monthly_report_view", "");
                dsewiseReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("dse_report_view", "");
                leadReport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("lead_report_view", "");
                dseDailyreport = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("add_dse_daily_report", "");
                dseDailyReportView = SharedPreferenceManager.getInstance(NavigationDrawer.this).getPreference("view_dse_daily_report", "");

                navigationView1.getMenu().findItem(R.id.nav_Daily_Productivity_Report).setVisible(true);

                if (dailyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
                } else if (dailyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Daily_Report).setVisible(false);
                }

                if (monthlyReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
                } else if (monthlyReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(false);
                }

                if (dsewiseReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
                } else if (dsewiseReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
                }

                if (leadReport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
                } else if (leadReport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
                }

                if (dseDailyreport.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
                } else if (dseDailyreport.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
                }

                if (dseDailyReportView.equals("1")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
                } else if (dseDailyReportView.equals("0")) {
                    navigationView1.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
                }
            }
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
    public void showProcessDashboard(LoginBean jsonObject) {
    }

    @Override
    public void showLocationDashboardCount(LocationWiseDashboardCountBean jsonObject) {
    }

    @Override
    public void showLocationwithoutSeelctionCount(LocationWiseDashboardCountBean jsonObject) {
    }
}
