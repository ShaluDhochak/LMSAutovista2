package com.excell.lms.lmsautovista.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.excell.lms.lmsautovista.BuildConfig;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.HttpHandler;
import com.excell.lms.lmsautovista.Utils.SessionManagement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    private static String url = Constants.BASE_URL + "get_app_version";
    String VersionUpdate;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialiseUI();
    }

    private void initialiseUI(){
        try {
            new VersionCheck().execute();
        }catch(Exception e){
        Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
    }
}

    private class VersionCheck extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray version = jsonObj.getJSONArray("get_app_version");
                    for (int i = 0; i < version.length(); i++){
                        JSONObject v = version.getJSONObject(i);
                        VersionUpdate = v.getString("version_code");
                    }
                }catch(final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                 runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            String VersionName = BuildConfig.VERSION_NAME;
            String VersionCode =String.valueOf(BuildConfig.VERSION_CODE);

            if (VersionUpdate.equals(VersionCode) || Integer.valueOf(VersionUpdate)< Integer.valueOf(VersionCode)){
                session = new SessionManagement(getApplicationContext());
                session.checkLogin();
                if (session.isLoggedIn()){
                    launchLoginScreen();
                }
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Our App got Update");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setCancelable(false);
                builder.setMessage("New version available, select update to update our app")
                        .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String appName = getPackageName();
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                                }
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    private void launchLoginScreen(){
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, NavigationDrawer.class));
                finish();
            }
        }, secondsDelayed * 3000);
    }
}
