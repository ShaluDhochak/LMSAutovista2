package lms.autovista.com.crossleadmanagement.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.R;

public class CategoryFormActivity extends AppCompatActivity implements View.OnClickListener{

    TextView addNewCategory_tv;
    RecyclerView addNewCategory_rv;
    TextView textCenterPlus_txtView,leftHeader_txtView;
    ImageView rightHeader_imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_form);
        initialiseUI();
    }

    private void initialiseUI(){

        textCenterPlus_txtView = (TextView) findViewById(R.id.textCenterPlus_txtView);
        leftHeader_txtView = (TextView) findViewById(R.id.leftHeader_txtView);
        rightHeader_imgView = (ImageView) findViewById(R.id.rightHeader_imgView);
        textCenterPlus_txtView.setText("Category");
        leftHeader_txtView.setText("Back");
        rightHeader_imgView.setOnClickListener(this);
        leftHeader_txtView.setOnClickListener(this);

        addNewCategory_tv = (TextView) findViewById(R.id.addNewCategory_tv);
        addNewCategory_tv.setVisibility(View.GONE);
        addNewCategory_tv.setOnClickListener(this);

        addNewCategory_rv = (RecyclerView) findViewById(R.id.addNewCategory_rv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewCategory_tv:
                getAddCategoryLayout();
                break;
            case R.id.rightHeader_imgView:
                getAddCategoryLayout();
                break;
            case R.id.leftHeader_txtView:
                onBackPressed();
                break;
        }
    }

    private void getAddCategoryLayout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CategoryFormActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View changePasswordDialog = layoutInflater.inflate(R.layout.add_category_layout, null);
        final EditText addCategory_name = changePasswordDialog.findViewById(R.id.addCategory_name);
        final TextView saveAddCategory_txtView = changePasswordDialog.findViewById(R.id.saveAddCategory_txtView);
        final TextView cancelAddCategory_txtView = changePasswordDialog.findViewById(R.id.cancelAddCategory_txtView);

        alertDialogBuilder.setView(changePasswordDialog);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        saveAddCategory_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelAddCategory_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

}
