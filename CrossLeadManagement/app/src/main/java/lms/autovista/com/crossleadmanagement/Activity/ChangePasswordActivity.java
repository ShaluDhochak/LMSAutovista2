package lms.autovista.com.crossleadmanagement.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{
    TextView leftHeaderText_txtView,textCenterText_txtView,rightHeaderText_txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initialiseUI();
    }

    private void initialiseUI(){
        leftHeaderText_txtView = (TextView) findViewById(R.id.leftHeaderText_txtView);
        textCenterText_txtView = (TextView) findViewById(R.id.textCenterText_txtView);
        rightHeaderText_txtView = (TextView) findViewById(R.id.rightHeaderText_txtView);

        leftHeaderText_txtView.setText("Back");
        textCenterText_txtView.setText("Change Password");
        rightHeaderText_txtView.setText("Done");

        leftHeaderText_txtView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftHeaderText_txtView:
                super.onBackPressed();
                break;
        }
    }
}
