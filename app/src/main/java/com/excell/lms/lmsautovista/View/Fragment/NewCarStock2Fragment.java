package com.excell.lms.lmsautovista.View.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.NewCarStock1Bean;
import com.excell.lms.lmsautovista.Model.NewCarStockLocationModel;
import com.excell.lms.lmsautovista.Presenter.NewCarStockFilterPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.NewCarFilterAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCarStock2Fragment extends Fragment implements IView.NewCarStockFilter{

    NewCarStockFilterPresenter newCarStockFilterPresenter;
    ArrayList<NewCarStock1Bean.New_stock_Count> newStockList = new ArrayList<NewCarStock1Bean.New_stock_Count>();
    AlertDialog altDialog;

    String selectedLocationDashboard, selectedLocationDashboardId, selectedMakeId, selectedMfgYear, selectedMfgYearId, selectedOwner, selectedOwnerId;
    String leadLocationSpinner,selectedLeadLocationId, leadModelSpinner,leadMakeSpinner, selectedLeadModelId, visitStatusSpinner, visitStatusSpinnerId;
    String selectedAgeing, selectedAgeingID, selectedPrice, selectedPriceId;

    @BindView(R.id.filterSearchNewStock1_TXTview)
    TextView filterSearchNewStock1_TXTview;

    @BindView(R.id.newCarStockCount1_recyclerView)
    RecyclerView newCarStockCount1_recyclerView;

    //sEARCH fILTER FIRST
    @BindView(R.id.filterSearchMessageNewStock1_TXTview)
    TextView filterSearchMessageNewStock1_TXTview;

    Spinner modelNewStockFilter_spinner, locationNewStockFilter_spinner, priceNewStockFilter_spinner,ageingNewStockFilter_spinner,vehicleStatusNewStockFilter_spinner;

    TextView applyStockFilterNewStock_txtView,cancelStockFilterNewStock_txtView;

    Map<String, String> modelMap = new HashMap<>();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_car_stock2, container, false);
        ButterKnife.bind(this, view);
        initialiseUI();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (NetworkUtilities.isInternet(getActivity())) {
          //  newCarStockFilterPresenter.get(getActivity());
          //  newCarStockFilterPresenter.getNewCarStockEmptyCount(getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialiseUI(){
        newCarStockFilterPresenter = new NewCarStockFilterPresenter(this);
      //  newCarStockFilterPresenter.getNewCarStockEmptyCount(getActivity());
        newCarStockCount1_recyclerView.setVisibility(View.GONE);
        filterSearchMessageNewStock1_TXTview.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.filterSearchNewStock1_TXTview)
    public void filterSearchNewStock(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = li.inflate(R.layout.new_car_stock_filter_layout, null);

        modelNewStockFilter_spinner = (Spinner) view.findViewById(R.id.modelNewStockFilter_spinner);
        locationNewStockFilter_spinner = (Spinner) view.findViewById(R.id.locationNewStockFilter_spinner);
        priceNewStockFilter_spinner = (Spinner) view.findViewById(R.id.priceNewStockFilter_spinner);
        ageingNewStockFilter_spinner = (Spinner) view.findViewById(R.id.ageingNewStockFilter_spinner);
        vehicleStatusNewStockFilter_spinner = (Spinner) view.findViewById(R.id.vehicleStatusNewStockFilter_spinner);

        applyStockFilterNewStock_txtView = (TextView) view.findViewById(R.id.applyStockFilterNewStock_txtView);
        cancelStockFilterNewStock_txtView = (TextView) view.findViewById(R.id.cancelStockFilterNewStock_txtView);

        newCarStockFilterPresenter.getLocation(getActivity());
        newCarStockFilterPresenter.getModel(getActivity());

        filterSearchMessageNewStock1_TXTview.setVisibility(View.GONE);

        getAgeingDetails();
        getPriceDetails();
        getVisitStatusDetails();

        //   TextView cancelStockFilter_txtView = (TextView) view.findViewById(R.id.cancelStockFilter_txtView);
        cancelStockFilterNewStock_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altDialog.dismiss();
            }
        });
        //  TextView applyStockFilter_txtView = (TextView) view.findViewById(R.id.applyStockFilter_txtView);
        applyStockFilterNewStock_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altDialog.dismiss();
                newCarStockFilterPresenter.getNewCarStockCount(leadModelSpinner,leadLocationSpinner,visitStatusSpinnerId,selectedAgeingID, selectedPriceId, getActivity());
            }
        });
        builder.setView(view);
        altDialog = builder.create();
        altDialog.show();
    }

    public void getPriceDetails(){
        ArrayList<String> priceArrayList = new ArrayList<>();
        priceArrayList.add("Price");
        priceArrayList.add("Less than 4 lakh");
        priceArrayList.add("4 to 6 lakh");
        priceArrayList.add("6 to 8 lakh");
        priceArrayList.add("More than 8 lakh");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, priceArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceNewStockFilter_spinner.setAdapter(contactArrayAdapter);

        priceNewStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPrice = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedPrice.equals("Price")){
                    selectedPriceId= "";
                }else if (selectedPrice.equals("Less than 4 lakh")){
                    selectedPriceId = "1";
                }else if (selectedPrice.equals("4 to 6 lakh")){
                    selectedPriceId = "2";
                }else if (selectedPrice.equals("6 to 8 lakh")){
                    selectedPriceId = "3";
                }else if (selectedPrice.equals("More than 8 lakh")){
                    selectedPriceId = "4";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void getVisitStatusDetails(){
        ArrayList<String> visitStatusArrayList = new ArrayList<>();
        visitStatusArrayList.add("Visit Status");
        visitStatusArrayList.add("Free");
        visitStatusArrayList.add("Blocked");
        ArrayAdapter<String> visitStatusArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, visitStatusArrayList);
        visitStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleStatusNewStockFilter_spinner.setAdapter(visitStatusArrayAdapter);

        vehicleStatusNewStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                visitStatusSpinner = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);
                if (visitStatusSpinner.equals("Visit Status")){
                    visitStatusSpinnerId= "";
                }else if (visitStatusSpinner.equals("Free")){
                    visitStatusSpinnerId = "1";
                }else if (visitStatusSpinner.equals("Blocked")){
                    visitStatusSpinnerId = "2";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getAgeingDetails(){
        ArrayList<String> AgeingArrayList = new ArrayList<>();
        AgeingArrayList.add("Ageing");
        AgeingArrayList.add("Before 15 days");
        AgeingArrayList.add("15 to 30 days");
        AgeingArrayList.add("31 to 60 days");
        AgeingArrayList.add("More than 60 days");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, AgeingArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageingNewStockFilter_spinner.setAdapter(contactArrayAdapter);

        ageingNewStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAgeing = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedAgeing.equals("Ageing")){
                    selectedAgeingID= "";
                }else if (selectedAgeing.equals("Before 15 days")){
                    selectedAgeingID = "1";
                }else if (selectedAgeing.equals("15 to 30 days")){
                    selectedAgeingID = "2";
                }else if (selectedAgeing.equals("31 to 60 days")){
                    selectedAgeingID = "3";
                }else if (selectedAgeing.equals("More than 60 days")){
                    selectedAgeingID = "4";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void shownewCarModelSpinner(NewCarStockLocationModel jsonObject) {
        try {
            ArrayList<String> modelArrayList = new ArrayList<>();
            modelArrayList.add("Model");
            if (jsonObject.getNew_stock_model().size() > 0) {
                for (int i = 0; i < jsonObject.getNew_stock_model().size(); i++) {
                    try {
                        modelArrayList.add(jsonObject.getNew_stock_model().get(i).getModel());
                   //     modelMap.put(jsonObject.getNew_stock_model().get(i).getModel(), jsonObject.getNew_stock_model().get(i).getModel_name());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, modelArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modelNewStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            modelNewStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leadModelSpinner = (String) parent.getItemAtPosition(position);
                        if (leadModelSpinner.equals("Model")){
                            leadModelSpinner= "";
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void showNewCarLocationSpinner(NewCarStockLocationModel jsonObject) {
        try {
            ArrayList<String> locationArrayList = new ArrayList<>();
            locationArrayList.add("Location");
            if (jsonObject.getNew_stock_location().size() > 0) {
                for (int i = 0; i < jsonObject.getNew_stock_location().size(); i++) {
                    try {
                        locationArrayList.add(jsonObject.getNew_stock_location().get(i).getAssigned_location());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locationNewStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            locationNewStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leadLocationSpinner = (String) parent.getItemAtPosition(position);
                    if (leadLocationSpinner.equals("Location")){
                        leadLocationSpinner = "";
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void showNewCarStockCountList(NewCarStock1Bean jsonObject) {
        try{
            if (jsonObject.getNew_stock_count().size()>0) {
                newStockList.clear();
                newStockList.addAll(jsonObject.getNew_stock_count());

                NewCarFilterAdapter dashboardAdapter = new NewCarFilterAdapter(getActivity(), jsonObject.getNew_stock_count());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                newCarStockCount1_recyclerView.setVisibility(View.VISIBLE);
                newCarStockCount1_recyclerView.setLayoutManager(mLayoutManager);
                newCarStockCount1_recyclerView.setItemAnimator(new DefaultItemAnimator());
                newCarStockCount1_recyclerView.setAdapter(dashboardAdapter);
            }else{
                Toast.makeText(getActivity(), "No Record Found.", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
        }
    }
}
