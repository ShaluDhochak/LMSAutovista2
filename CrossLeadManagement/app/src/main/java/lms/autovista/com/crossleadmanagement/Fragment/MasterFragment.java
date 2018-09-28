package lms.autovista.com.crossleadmanagement.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import lms.autovista.com.crossleadmanagement.Activity.CategoryFormActivity;
import lms.autovista.com.crossleadmanagement.Activity.CompanyFormActivity;
import lms.autovista.com.crossleadmanagement.Activity.ProductFormActivity;
import lms.autovista.com.crossleadmanagement.R;

public class MasterFragment extends Fragment implements View.OnClickListener{
    View view;
    RelativeLayout categoryMaster_rl,companyMaster_rl, productMaster_rl,rightsMaster_rl, incentiveSchemesMaster_rl,incentiveApprovalsMaster_rl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_master, container, false);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        categoryMaster_rl  =(RelativeLayout) view.findViewById(R.id.categoryMaster_rl);
        companyMaster_rl =(RelativeLayout) view.findViewById(R.id.companyMaster_rl);
        productMaster_rl =(RelativeLayout) view.findViewById(R.id.productMaster_rl);
        rightsMaster_rl =(RelativeLayout) view.findViewById(R.id.rightsMaster_rl);
        incentiveSchemesMaster_rl =(RelativeLayout) view.findViewById(R.id.incentiveSchemesMaster_rl);
        incentiveApprovalsMaster_rl =(RelativeLayout) view.findViewById(R.id.incentiveApprovalsMaster_rl);

        categoryMaster_rl.setOnClickListener(this);
        companyMaster_rl.setOnClickListener(this);
        productMaster_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.categoryMaster_rl:
                Intent categoryMasterIntent = new Intent(getActivity(), CategoryFormActivity.class);
                startActivity(categoryMasterIntent);
                break;
            case R.id.companyMaster_rl :
                Intent companyMasterIntent = new Intent(getActivity(), CompanyFormActivity.class);
                startActivity(companyMasterIntent);
                break;
            case R.id.productMaster_rl:
                Intent productMasterIntent = new Intent(getActivity(), ProductFormActivity.class);
                startActivity(productMasterIntent);
                break;
            case R.id.rightsMaster_rl :

                break;
            case R.id.incentiveSchemesMaster_rl:

                break;
            case R.id.incentiveApprovalsMaster_rl :

                break;
        }

    }
}
