package com.example.user.lmsautovista.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.lmsautovista.R;
import com.example.user.lmsautovista.View.Activity.SearchLeadViaContactNoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchCustomerFragment extends Fragment {

    View view;
    @BindView(R.id.searchNameContactNoCustomerSearch_edittext)
    EditText searchNameContactNoCustomerSearch_edittext;

    @BindView(R.id.searchNameContcatNo_button)
    Button searchNameContcatNo_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_customer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @OnClick(R.id.searchNameContcatNo_button)
    public void searchButtonClick(){
        try{
        String contact_no = searchNameContactNoCustomerSearch_edittext.getText().toString();
        if (searchNameContactNoCustomerSearch_edittext.getText().length()>0) {
            Intent contactNoSearchIntent = new Intent(getActivity(), SearchLeadViaContactNoActivity.class);
            Bundle searchNoSearchBundle = new Bundle();
            searchNoSearchBundle.putString("contact_no", contact_no);
            contactNoSearchIntent.putExtras(searchNoSearchBundle);
            startActivity(contactNoSearchIntent);
        }
        }catch(Exception e){
        }
    }

}
