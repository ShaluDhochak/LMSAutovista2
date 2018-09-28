package lms.autovista.com.crossleadmanagement.Activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.R;

public class ProductFormActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView addNewProduct_rv;
    TextView textCenterPlus_txtView,leftHeader_txtView;
    ImageView rightHeader_imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);
        initialiseUI();
    }

    private void initialiseUI(){
        addNewProduct_rv = (RecyclerView) findViewById(R.id.addNewProduct_rv);
        textCenterPlus_txtView = (TextView) findViewById(R.id.textCenterPlus_txtView);
        leftHeader_txtView = (TextView) findViewById(R.id.leftHeader_txtView);
        rightHeader_imgView = (ImageView) findViewById(R.id.rightHeader_imgView);

        textCenterPlus_txtView.setText("Product");
        leftHeader_txtView.setText("Back");
        rightHeader_imgView.setOnClickListener(this);
        leftHeader_txtView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rightHeader_imgView:
                getAddProductLayout();
                break;
            case R.id.leftHeader_txtView:
                onBackPressed();
                break;
        }
    }

    private void getAddProductLayout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProductFormActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View addProductDialog = layoutInflater.inflate(R.layout.add_product_layout, null);
        final Spinner productNameAddProduct_Spinner = addProductDialog.findViewById(R.id.productNameAddProduct_Spinner);
        final EditText addProduct_name = addProductDialog.findViewById(R.id.addProduct_name);
        final TextView cancelAddProduct_txtView = addProductDialog.findViewById(R.id.cancelAddProduct_txtView);
        final TextView saveAddProduct_txtView = addProductDialog.findViewById(R.id.saveAddProduct_txtView);

        alertDialogBuilder.setView(addProductDialog);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        saveAddProduct_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelAddProduct_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
