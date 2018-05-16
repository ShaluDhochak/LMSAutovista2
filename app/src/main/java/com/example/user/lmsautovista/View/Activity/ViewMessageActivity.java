package com.example.user.lmsautovista.View.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lmsautovista.Model.ViewMessageListBean;
import com.example.user.lmsautovista.Presenter.ViewMessagePresenter;
import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Adapter.NewMessageViewAdapter;
import com.example.user.lmsautovista.View.IView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewMessageActivity extends AppCompatActivity implements IView.IViewMessageView{

    @BindView(R.id.messageListView_listView)
    ListView messageListView_listView;

    SharedPreferences pref;

    @BindView(R.id.lmsheading_TextView)
    TextView lmsheading_TextView;

    @BindView(R.id.backButton_ImageView)
    ImageView backButton_ImageView;

    ArrayList<ViewMessageListBean.Message_Home> countLocationList = new ArrayList<>();
    NewMessageViewAdapter newMessageViewAdapter;
    ViewMessagePresenter viewMessagePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        ButterKnife.bind(this);
        initialiseUI();

    }

    private void initialiseUI(){

        viewMessagePresenter = new ViewMessagePresenter(this);
        viewMessagePresenter.getViewMessageList(ViewMessageActivity.this);
    }

    @OnClick(R.id.backButton_ImageView)
    public void backbtn(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showViewMessageList(ViewMessageListBean jsonObject) {

        newMessageViewAdapter = new NewMessageViewAdapter(ViewMessageActivity.this,jsonObject.getMessage_home());
        messageListView_listView.setAdapter(newMessageViewAdapter);
        newMessageViewAdapter.notifyDataSetChanged();
    }
}
