package com.excell.lms.lmsautovista.View.Activity;

import android.annotation.SuppressLint;
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
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Manager.SharedPreferenceManager;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Config;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.Utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.makeText;

public class RegisterPushActivity extends AppCompatActivity {

    @BindView(R.id.mobileno_editText)
    EditText mobileno_editText;
    @BindView(R.id.registerDevice_btn)
    Button registerDevice_btn;
    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;
    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;
    @BindView(R.id.token_txtView)
    TextView token_txtView;

    private static final String TAG = RegisterPushActivity.class.getSimpleName();

    JSONParser jsonParser = new JSONParser();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    SharedPreferences sharedPref;
    String userPref, regId,message ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_push);
        ButterKnife.bind(this);
        initiailseUI();
    }

    @SuppressLint("SetTextI18n")
    private void initiailseUI(){

        sharedPref = PreferenceManager.getDefaultSharedPreferences(RegisterPushActivity.this);
        userPref = sharedPref.getString("user_id", "");
        lmsheading_TextView.setText("Device Registration");
        notificationdata();
    }

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
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(RegisterPushActivity.this)
                                    .setSmallIcon(R.drawable.notification_logo)
                                    .setContentTitle("LMS autovista")
                                    .setContentText(message);

                    Intent notificationIntent = new Intent(RegisterPushActivity.this, NavigationDrawer.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(RegisterPushActivity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);

                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    assert manager != null;
                    manager.notify(0, builder.build());
                }
            }
        };
        displayFirebaseRegId();
    }

    @SuppressLint("SetTextI18n")
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);
        Log.e(TAG, "Firebase reg id: " + regId);
        token_txtView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(regId))
            token_txtView.setText("Firebase Reg Id: " + regId);
        else
            token_txtView.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @OnClick(R.id.registerDevice_btn)
        public void resgisterDevice(){
            new newRegistration().execute();
        }

    @OnClick(R.id.backButton_ImageView)
        public void onbackpressReg(){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    private class newRegistration extends AsyncTask<String, JSONObject, JSONObject> {
        String mobileNo, token, user_id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user_id = userPref;
            mobileNo = mobileno_editText.getText().toString();
            token = regId;
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("user_id", SharedPreferenceManager.getInstance(RegisterPushActivity.this).getPreference(Constants.USER_ID, "")));
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
                    makeText(RegisterPushActivity.this,message, Toast.LENGTH_SHORT).show();
                }
                else {
                    makeText(RegisterPushActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {
            }
        }
    }

}
