package com.excell.lms.lmsautovista.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.View.Activity.EditCustomerListDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCustomerFragment extends Fragment {

    View view;

    @BindView(R.id.editSearchNameContcatNo_button)
    Button editSearchNameContcatNo_button;

    @BindView(R.id.searchNameContactNoEditCustomerSearch_edittext)
    EditText searchNameContactNoEditCustomerSearch_edittext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_customer, container, false);

        ButterKnife.bind(this, view);

        // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.editSearchNameContcatNo_button)
    public void editSearchCustomer(){
        String contact_no = searchNameContactNoEditCustomerSearch_edittext.getText().toString();
        if (searchNameContactNoEditCustomerSearch_edittext.getText().length()>0) {
            Intent contactNoSearchIntent = new Intent(getActivity(),EditCustomerListDetailActivity.class);
            Bundle searchNoSearchBundle = new Bundle();
            searchNoSearchBundle.putString("contact_no", contact_no);
            contactNoSearchIntent.putExtras(searchNoSearchBundle);
            startActivity(contactNoSearchIntent);
        }
    }

}
