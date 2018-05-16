package com.example.user.lmsautovista.View.Activity;

import android.support.v7.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity
{
    /*implements
} NavigationView.OnNavigationItemSelectedListener, IView.LoginView {

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
    String process_id, process_name, selectedLocationId, selectedLocation, roleStr;
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

    public void initialiseUI(){
        setSupportActionBar(toolbar);
        navHeader = navigationView.getHeaderView(0);

        roleStr = SharedPreferenceManager.getInstance(HomeActivity.this).getPreference(Constants.ROLE_ID, "");

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

        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        DashboardFragment dfragment = new DashboardFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dfragment);
        dtransaction.commit();
    }

    public void addNewLeadFragment(){
        toolbar_title.setText("Dashboard");
        navigationView.getMenu().getItem(0).setChecked(true);

    //    navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        AddNewLeadFragment addNewLeadfragment = new AddNewLeadFragment();
        FragmentTransaction addNewLeadtransaction = getSupportFragmentManager().beginTransaction();
        addNewLeadtransaction.replace(R.id.fragment_Container, addNewLeadfragment);
        addNewLeadtransaction.commit();
    }

    public void searchCustomer(){

        toolbar_title.setText("Search Customer");
        navigationView.getMenu().getItem(0).setChecked(true);

        //    navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(false);
        navigationView.getMenu().getItem(19).setVisible(false);
        navigationView.getMenu().getItem(20).setVisible(true);
        navigationView.getMenu().getItem(21).setVisible(true);

        SearchCustomerFragment searchCustomerFragment = new SearchCustomerFragment();
        FragmentTransaction addNewLeadtransaction = getSupportFragmentManager().beginTransaction();
        addNewLeadtransaction.replace(R.id.fragment_Container, searchCustomerFragment);
        addNewLeadtransaction.commit();
    }

    public void editCustomer(){

        toolbar_title.setText("Edit Customer");
        navigationView.getMenu().getItem(0).setChecked(true);

        //    navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(false);
        navigationView.getMenu().getItem(19).setVisible(false);
        navigationView.getMenu().getItem(20).setVisible(true);
        navigationView.getMenu().getItem(21).setVisible(true);

        EditCustomerFragment editCustomerFragment = new EditCustomerFragment();
        FragmentTransaction editCustomerTransaction = getSupportFragmentManager().beginTransaction();
        editCustomerTransaction.replace(R.id.fragment_Container, editCustomerFragment);
        editCustomerTransaction.commit();
    }

    public void callingTashFragment(){
      //  toolbar_title.setText("Calling Task");
        navigationView.getMenu().getItem(0).setChecked(true);

     //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

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

     //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

            toolbar_title.setText("Tracker");
            TrackerFragment cfragment = new TrackerFragment();
            FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
            dtransaction.replace(R.id.fragment_Container, cfragment);
            dtransaction.commit();

    }

    public void newCarStockFragment(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("New Car Stock");
        NewStockFragment cfragment = new NewStockFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, cfragment);
        dtransaction.commit();

    }

    public void dailyReportFragment(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);


        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Daily Report");
        DailyReportFragment dailyReportfragment = new DailyReportFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dailyReportfragment);
        dtransaction.commit();
    }

    public void monthlyReportFragment(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);

        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Monthly Report");
        MonthlyReportFragment dailyReportfragment = new MonthlyReportFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dailyReportfragment);
        dtransaction.commit();
    }

    public void dseWiseReportFragment(){
        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);


        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

      //  String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("DSEWise Report");
        DSEReportFragment dseReportfragment = new DSEReportFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dseReportfragment);
        dtransaction.commit();
    }

    public void leadReportFragment(){
        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);


        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Lead Report");
        LeadReportFragment leadReportfragment = new LeadReportFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, leadReportfragment);
        dtransaction.commit();
    }

    public void dseDailyReport(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);


        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("DSE Daily Report");
        DseDailyReportFragment dseDailyReportfragment = new DseDailyReportFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dseDailyReportfragment);
        dtransaction.commit();

    }
    public void dseDailyReportView(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);


        setReportMenuVisibility();

        navigationView.getMenu().getItem(10).setVisible(false);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("DSE Daily Report View");
        DseDailyReportViewFragment dseDailyReportViewfragment = new DseDailyReportViewFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, dseDailyReportViewfragment);
        dtransaction.commit();

    }

    public void pocCarStockFragment(){

        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("POC Stock");
        POCStockFragment cfragment = new POCStockFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, cfragment);
        dtransaction.commit();

    }

    public void assignNewLeadFragment(){
        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);

        serDefaultReport();

        setLeadManagementVisibility();
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Assign New Lead");
        AssignNewLeadFragment assignNewLeadfragment = new AssignNewLeadFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, assignNewLeadfragment);
        dtransaction.commit();
    }

    public void assignTransferLeadFragment(){
        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);

        serDefaultReport();

        setLeadManagementVisibility();
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Assign Transfer Lead");
        AssignTransferLeadFragment assignTransferLeadfragment = new AssignTransferLeadFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, assignTransferLeadfragment);
        dtransaction.commit();
    }

    public void sendMessageCustomer(){
        navigationView.getMenu().getItem(0).setChecked(true);

        //   navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(0).setVisible(true);
        navigationView.getMenu().getItem(1).setVisible(true);
        navigationView.getMenu().getItem(2).setVisible(true);

        navigationView.getMenu().getItem(3).setVisible(true);
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);

        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);

        navigationView.getMenu().getItem(9).setVisible(false);
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(false);

        navigationView.getMenu().getItem(12).setVisible(false);
        navigationView.getMenu().getItem(13).setVisible(false);
        navigationView.getMenu().getItem(14).setVisible(false);
        navigationView.getMenu().getItem(15).setVisible(true);
        navigationView.getMenu().getItem(16).setVisible(false);

        navigationView.getMenu().getItem(17).setVisible(false);
        navigationView.getMenu().getItem(18).setVisible(true);
        navigationView.getMenu().getItem(19).setVisible(true);
        navigationView.getMenu().getItem(20).setVisible(false);
        navigationView.getMenu().getItem(21).setVisible(false);

        String role = SharedPreferenceManager.getInstance(this).getPreference(Constants.ROLE_ID, "");

        toolbar_title.setText("Send Message ");
        AddMessageFragment addMessagefragment = new AddMessageFragment();
        FragmentTransaction dtransaction = getSupportFragmentManager().beginTransaction();
        dtransaction.replace(R.id.fragment_Container, addMessagefragment);
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

            case R.id.nav_report:
                setReportMenuVisibility();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                break;

            case R.id.nav_Daily_Report :

                setReportMenuVisibility();
                dailyReportFragment();
                closeDrawer();

                break;
            case R.id.nav_Monthly_Report :

                setReportMenuVisibility();
                closeDrawer();
                monthlyReportFragment();
                break;

            case R.id.nav_Dsewise_report :

                setReportMenuVisibility();
                closeDrawer();
                dseWiseReportFragment();
                break;

            case R.id.nav_DseDailywise_report :
                setReportMenuVisibility();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                closeDrawer();
                dseDailyReport();
                break;

            case R.id.nav_DseDailyReportView_report :
                setReportMenuVisibility();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                closeDrawer();
                dseDailyReportView();
                break;

            case R.id.nav_Lead_report:
                setReportMenuVisibility();
                closeDrawer();
                leadReportFragment();
                break;

            case R.id.nav_stock:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(true);
                navigationView.getMenu().getItem(17).setVisible(true);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                break;

            case R.id.nav_new_car_stock:
                newCarStockFragment();
                closeDrawer();
                break;

            case R.id.nav_poc_stock:
                pocCarStockFragment();
                closeDrawer();
                break;

            case R.id.nav_lead_management_lead:
                serDefaultReport();
                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);


                navigationView.getMenu().getItem(11).setVisible(true);
                navigationView.getMenu().getItem(12).setVisible(true);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                break;

            case R.id.nav_add_lead:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(true);
                navigationView.getMenu().getItem(12).setVisible(true);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                addNewLeadFragment();
                closeDrawer();
                break;

            case R.id.nav_assign_lead :
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(true);
                navigationView.getMenu().getItem(12).setVisible(true);
                navigationView.getMenu().getItem(13).setVisible(true);
                navigationView.getMenu().getItem(14).setVisible(true);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);

                break;

            case R.id.nav_assign_new_lead:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(true);
                navigationView.getMenu().getItem(12).setVisible(true);
                navigationView.getMenu().getItem(13).setVisible(true);
                navigationView.getMenu().getItem(14).setVisible(true);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);
                assignNewLeadFragment();

                closeDrawer();
                break;

            case R.id.nav_assign_transferred_lead :

                serDefaultReport();
                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(true);
                navigationView.getMenu().getItem(12).setVisible(true);
                navigationView.getMenu().getItem(13).setVisible(true);
                navigationView.getMenu().getItem(14).setVisible(true);

                navigationView.getMenu().getItem(20).setVisible(false);
                navigationView.getMenu().getItem(21).setVisible(false);

                assignTransferLeadFragment();

                closeDrawer();
                break;

            case R.id.nav_customer_operation:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(0).setChecked(true);
                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(true);
                navigationView.getMenu().getItem(21).setVisible(true);

                break;

            case R.id.nav_search_customer:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(true);
                navigationView.getMenu().getItem(21).setVisible(true);

                searchCustomer();
                closeDrawer();
                break;
            case R.id.nav_edit_customer:

                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(true);
                navigationView.getMenu().getItem(21).setVisible(true);
                editCustomer();
                closeDrawer();
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

            case R.id.nav_send_message:
                serDefaultReport();

                navigationView.getMenu().getItem(16).setVisible(false);
                navigationView.getMenu().getItem(17).setVisible(false);

                navigationView.getMenu().getItem(11).setVisible(false);
                navigationView.getMenu().getItem(12).setVisible(false);
                navigationView.getMenu().getItem(13).setVisible(false);
                navigationView.getMenu().getItem(14).setVisible(false);

                navigationView.getMenu().getItem(20).setVisible(true);
                navigationView.getMenu().getItem(21).setVisible(true);
                sendMessageCustomer();
                closeDrawer();
                break;

        }


     //   closeDrawer();
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void serDefaultReport(){
        navigationView.getMenu().getItem(4).setVisible(false);
        navigationView.getMenu().getItem(5).setVisible(false);
        navigationView.getMenu().getItem(6).setVisible(false);
        navigationView.getMenu().getItem(7).setVisible(false);
        navigationView.getMenu().getItem(8).setVisible(false);
        navigationView.getMenu().getItem(9).setVisible(false);
    }

    //Setting the right condition for report
    public void setReportMenuVisibility(){
        if (roleStr.equals("1")) {
            navigationView.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("2")) { //manager
            navigationView.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("3")) {
            navigationView.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
        }else if (roleStr.equals("5")) {
            navigationView.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Lead_report).setVisible(true);
        }else if (roleStr.equals("4")) {
            navigationView.getMenu().findItem(R.id.nav_Daily_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Monthly_Report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Dsewise_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DseDailywise_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_DseDailyReportView_report).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_Lead_report).setVisible(false);
        }
    }

    private void setLeadManagementVisibility(){
        navigationView.getMenu().getItem(10).setVisible(true);
        navigationView.getMenu().getItem(11).setVisible(true);

        navigationView.getMenu().getItem(12).setVisible(true);
        navigationView.getMenu().getItem(13).setVisible(true);
        navigationView.getMenu().getItem(14).setVisible(true);

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
    */
}

