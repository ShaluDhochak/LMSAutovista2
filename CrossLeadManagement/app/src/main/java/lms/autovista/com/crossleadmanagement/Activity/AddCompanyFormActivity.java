package lms.autovista.com.crossleadmanagement.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import lms.autovista.com.crossleadmanagement.R;

public class AddCompanyFormActivity extends AppCompatActivity implements View.OnClickListener{
    TextView companyAddNewLead_et,companyRegIdAddCompany_et, activeToCalender_tv, activeFromCalender_tv;
    TextView textCenterText_txtView,rightHeaderText_txtView,leftHeaderText_txtView;

    ImageView activeToCalender_iv, activeFromCalender_iv;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company_form);
        initialiseUI();
    }

    private void initialiseUI(){

        textCenterText_txtView = (TextView) findViewById(R.id.textCenterText_txtView);
        rightHeaderText_txtView = (TextView) findViewById(R.id.rightHeaderText_txtView);
        leftHeaderText_txtView = (TextView) findViewById(R.id.leftHeaderText_txtView);

        activeToCalender_iv = (ImageView) findViewById(R.id.activeToCalender_iv);
        activeFromCalender_iv = (ImageView) findViewById(R.id.activeFromCalender_iv);

        activeToCalender_tv = (TextView) findViewById(R.id.activeToCalender_tv);
        activeFromCalender_tv = (TextView) findViewById(R.id.activeFromCalender_tv);

        textCenterText_txtView.setText("Add Company");
        rightHeaderText_txtView.setText("Done");
        leftHeaderText_txtView.setText("Back");
        leftHeaderText_txtView.setOnClickListener(this);

        activeFromCalender_iv.setOnClickListener(this);
        activeToCalender_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.activeToCalender_iv:
                final Calendar todatecalenderObject = Calendar.getInstance();
                int BookedYear = todatecalenderObject.get(Calendar.YEAR);
                int BookedMonth = todatecalenderObject.get(Calendar.MONTH);
                int BookedDay = todatecalenderObject.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                activeToCalender_tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, BookedYear, BookedMonth, BookedDay);
                datePickerDialog.show();
                break;
            case R.id.activeFromCalender_iv:
                final Calendar fromdatecalenderObj = Calendar.getInstance();
                int year = fromdatecalenderObj.get(Calendar.YEAR);
                int month = fromdatecalenderObj.get(Calendar.MONTH);
                int day = fromdatecalenderObj.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                activeFromCalender_tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datePickerDialog.show();
                break;

            case R.id.leftHeaderText_txtView:
                super.onBackPressed();
                break;
        }
    }
}
