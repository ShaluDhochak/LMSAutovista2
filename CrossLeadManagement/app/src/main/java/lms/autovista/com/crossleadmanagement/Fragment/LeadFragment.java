package lms.autovista.com.crossleadmanagement.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lms.autovista.com.crossleadmanagement.Adapter.AllLeadAdapter;
import lms.autovista.com.crossleadmanagement.Bean.AllLeadBean;
import lms.autovista.com.crossleadmanagement.R;

public class LeadFragment extends Fragment {

    View view;
    RecyclerView allLeadList_rv;

    AllLeadAdapter allLeadAdapter;
    private List<AllLeadBean> allLeadList = new ArrayList<>();

    //Search
    EditText allLeadSearch_editView;
    ImageView searchIconAllLead_imgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_lead, container, false);
        initialiseUI();
        return view;
    }

    private void initialiseUI(){
        allLeadList_rv = (RecyclerView) view.findViewById(R.id.allLeadList_rv);
        allLeadSearch_editView = (EditText) view.findViewById(R.id.allLeadSearch_editView);
        searchIconAllLead_imgView = (ImageView) view.findViewById(R.id.searchIconAllLead_imgView);
    }
}
