package com.example.user.lmsautovista.View.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.lmsautovista.Manager.SharedPreferenceManager;
import com.example.user.lmsautovista.Model.DSEDailyReportLocationBean;
import com.example.user.lmsautovista.Presenter.SubmitMessagePresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.Utils.Constants;
import com.example.user.lmsautovista.Utils.JSONParser;
import com.example.user.lmsautovista.View.Adapter.LocationAddMessageAdapter;
import com.example.user.lmsautovista.View.IView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.makeText;

public class SubmitMessageActivity extends AppCompatActivity implements IView.ISubmitMessageView{

    @BindView(R.id.submitWriteMessage_btn)
    Button submitWriteMessage_btn;

    @BindView(R.id.writeMessage_et)
    EditText writeMessage_et;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.writemsg_scrollView)
    ScrollView writemsg_scrollView;

    @BindView(R.id.locationDseTlAddMessage_lv)
    ListView locationDseTlAddMessage_lv;

    ArrayList<DSEDailyReportLocationBean.Daily_Dse_Tracker_Location> locationArrayList = new ArrayList<>();
    LocationAddMessageAdapter locationAddMessageAdapter;
    String user_pref, locationPref;

    ProgressDialog pDialog;
    SharedPreferences pref;
    SubmitMessagePresenter submitMessagePresenter;

    JSONParser jsonParser = new JSONParser();
    JSONArray dseTlJSONArray;
    JSONArray dseJSONArray;
    JSONArray location;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_message);
        ButterKnife.bind(this);
        initialiseUI();
    }

    private void initialiseUI(){
        lmsheading_TextView.setText("Send Message");
        submitMessagePresenter = new SubmitMessagePresenter(this);
        submitMessagePresenter.getLocation(SubmitMessageActivity.this);
    }

    @Override
    public void showLocation(DSEDailyReportLocationBean jsonObject) {
        locationAddMessageAdapter = new LocationAddMessageAdapter(SubmitMessageActivity.this,jsonObject.getDaliy_dse_tracker_location());
        locationDseTlAddMessage_lv.setAdapter(locationAddMessageAdapter);
        locationAddMessageAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.submitWriteMessage_btn)
    public void getSubmitButton(){
        if ((!(locationAddMessageAdapter.getSelectedTlArray().size()==0)) || !(locationAddMessageAdapter.getSelectedDseArray().size()==0)){
            if ((writeMessage_et.getText().toString().trim().length()>0) && !(writeMessage_et.getText().toString().equals("Type Message here!!!"))){
                new CreateNewMessage().execute();
            }else{
                Toast.makeText(this, "Please Write some Message First...", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please Select DSE TL or DSE", Toast.LENGTH_SHORT).show();
        }
    }

    private class CreateNewMessage extends AsyncTask<String, JSONObject, JSONObject> {
        String user_id, message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dseJSONArray = locationAddMessageAdapter.getSelectedDse();

            dseTlJSONArray = locationAddMessageAdapter.getSelectedDseTl();
            location = locationAddMessageAdapter.getSelectedLocation();

            user_id = SharedPreferenceManager.getInstance(SubmitMessageActivity.this).getPreference(Constants.USER_ID, "");
            message = writeMessage_et.getText().toString();

            pDialog = new ProgressDialog(SubmitMessageActivity.this);
            pDialog.setMessage("Adding Message...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("tl", dseTlJSONArray.toString()));
            params.add(new BasicNameValuePair("dse", dseJSONArray.toString()));

            params.add(new BasicNameValuePair("location", location.toString()));
            params.add(new BasicNameValuePair("user_id", user_id));
            params.add(new BasicNameValuePair("message", message));

            String url_add_message = Constants.BASE_URL + Constants.ADD_MESSAGE;
            JSONObject json = jsonParser.makeHttpRequest(url_add_message, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                //  pDialog.dismiss();
                if (success == 1 && message.equals("Data successfully Inserted.")) {
                    return json;
                }
                else {
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject response) {
            try {
                pDialog.dismiss();
                if (!(response == null)) {
                    makeText(SubmitMessageActivity.this,"Data successfully Inserted.", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
                else {
                    makeText(SubmitMessageActivity.this, "Already Inserted ", Toast.LENGTH_SHORT).show();
                    clearAll();
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void clearAll(){
        writeMessage_et.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.backButton_ImageView)
    public void backBtn(){
        super.onBackPressed();
    }

}
