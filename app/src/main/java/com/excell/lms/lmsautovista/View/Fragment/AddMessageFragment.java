package com.excell.lms.lmsautovista.View.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.MessageListBean;
import com.excell.lms.lmsautovista.Presenter.AddMessagePresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.Constants;
import com.excell.lms.lmsautovista.Utils.JSONParser;
import com.excell.lms.lmsautovista.View.Activity.SubmitMessageActivity;
import com.excell.lms.lmsautovista.View.Adapter.MessageListAdapter;
import com.excell.lms.lmsautovista.View.IView;
import com.github.clans.fab.FloatingActionButton;

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


public class AddMessageFragment extends Fragment implements IView.IAddMessageView {

    View view;

    @BindView(R.id.addMessage_fab)
    FloatingActionButton addMessage_fab;

    @BindView(R.id.listOfmsg_lv)
    ListView listOfmsg_lv;

    @BindView(R.id.deleteMessage_txtView)
    TextView deleteMessage_txtView;

    AddMessagePresenter addMessagePresenter;

    MessageListAdapter messageListAdapter;
    SharedPreferences pref;
    ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    JSONArray deleteJSONArray;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    ArrayList<MessageListBean.Message_List> messageArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_message, container, false);

        ButterKnife.bind(this, view);
        initialiseUI();
        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseUI(){

        addMessagePresenter = new AddMessagePresenter(this);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading All Message...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }

    @Override
    public void onResume(){
        super.onResume();
        addMessagePresenter.getAddViewMessageList(getActivity());
    }

    @OnClick(R.id.deleteMessage_txtView)
    public void deleteMessage(){
        new DeleteNewMessage().execute();
    }

    @OnClick(R.id.addMessage_fab)
    public void addMessage(){
        Intent addMessageIntent =new Intent(getActivity(), SubmitMessageActivity.class);
        startActivity(addMessageIntent);
    }

    @Override
    public void showAddViewMessagelist(MessageListBean jsonObject) {
        pDialog.dismiss();
        messageArrayList.clear();
        messageArrayList.addAll(jsonObject.getMessage_list());
        messageListAdapter = new MessageListAdapter(getActivity(),jsonObject.getMessage_list());
        listOfmsg_lv.setAdapter(messageListAdapter);
        messageListAdapter.notifyDataSetChanged();
    }

    private class DeleteNewMessage extends AsyncTask<String, JSONObject, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            deleteJSONArray = messageListAdapter.getSelectedMessageId();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Deleting Message...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params;
            params = new ArrayList<>();

            params.add(new BasicNameValuePair("message_id", deleteJSONArray.toString()));

            String url_add_message = Constants.BASE_URL + Constants.DELETE_MESSAGE;
            JSONObject json = jsonParser.makeHttpRequest(url_add_message, "POST", params);
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                  pDialog.dismiss();
                if (success == 1 && message.equals("Successfully Deleted.")) {
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
                    makeText(getActivity(),"Successfully Deleted.", Toast.LENGTH_SHORT).show();
                    addMessagePresenter.getAddViewMessageList(getActivity());
                 //   getAllLeadData();
                }
                else {
                    makeText(getActivity(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                    addMessagePresenter.getAddViewMessageList(getActivity());
                  //  getAllLeadData();
                }
            } catch (Exception ignored) {
            }
        }
    }

}
