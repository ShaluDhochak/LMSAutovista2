package com.example.user.lmsautovista.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.user.lmsautovista.R;

import butterknife.BindView;

public class CheckFlowSearchActivity extends AppCompatActivity {

    @BindView(R.id.checkflow_listView)
    ListView checkflow_listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_flow_search);
    }
}
