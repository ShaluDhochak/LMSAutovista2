package lms.autovista.com.crossleadmanagement.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lms.autovista.com.crossleadmanagement.Activity.ChangePasswordActivity;
import lms.autovista.com.crossleadmanagement.Activity.EditProfileActivity;
import lms.autovista.com.crossleadmanagement.R;


public class MyProfileFragment extends Fragment implements View.OnClickListener{

    TextView changePasswordProfile_txtView, editMyProfileText_tv;
    ImageView editIcon_imgView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        changePasswordProfile_txtView = (TextView) view.findViewById(R.id.changePasswordProfile_txtView);
        editMyProfileText_tv = (TextView) view.findViewById(R.id.editMyProfileText_tv);
        editIcon_imgView = (ImageView) view.findViewById(R.id.editIcon_imgView);

        editIcon_imgView.setOnClickListener(this);
        editMyProfileText_tv.setOnClickListener(this);
        changePasswordProfile_txtView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.changePasswordProfile_txtView:
                Intent changePasswordIntent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(changePasswordIntent);
                break;
            case R.id.editMyProfileText_tv:
                Intent editProfileTxtIntent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(editProfileTxtIntent);
                break;
            case R.id.editIcon_imgView:
                Intent editProfileIntent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(editProfileIntent);
                break;

        }
    }
}
