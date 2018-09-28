package lms.autovista.com.crossleadmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.R;

public class CompanyFormActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textCenterPlus_txtView,leftHeader_txtView;
    ImageView rightHeader_imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_form);
        initialiseUI();
    }

    private void initialiseUI(){
        textCenterPlus_txtView = (TextView) findViewById(R.id.textCenterPlus_txtView);
        leftHeader_txtView = (TextView) findViewById(R.id.leftHeader_txtView);
        rightHeader_imgView = (ImageView) findViewById(R.id.rightHeader_imgView);
        textCenterPlus_txtView.setText("Company");
        leftHeader_txtView.setText("Back");
        rightHeader_imgView.setOnClickListener(this);
        leftHeader_txtView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rightHeader_imgView:
                Intent addCompanyFormIntent = new Intent(this, AddCompanyFormActivity.class);
                startActivity(addCompanyFormIntent);
                break;
            case R.id.leftHeader_txtView:
                onBackPressed();
                break;
        }
    }

    private void getAddCompanyLayout(){

    }
}
