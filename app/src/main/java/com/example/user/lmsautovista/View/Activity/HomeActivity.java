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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Fragment.DashboardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initialiseUI();

    }

    private void initialiseUI(){
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
       // dashboardFragment();


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
        getMenuInflater().inflate(R.menu.home, menu);
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
                Intent addContactIntent = new Intent(HomeActivity.this, CallingTaskActivity.class);
                startActivity(addContactIntent);
                closeDrawer();
                break;
            case R.id.nav_tracker:

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

}

