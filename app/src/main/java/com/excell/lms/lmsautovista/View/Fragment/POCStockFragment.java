package com.excell.lms.lmsautovista.View.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.excell.lms.lmsautovista.Model.MakeBean;
import com.excell.lms.lmsautovista.Model.POCCarStockCountBean;
import com.excell.lms.lmsautovista.Model.POCSpinnerBean;
import com.excell.lms.lmsautovista.Model.POCStockModel;
import com.excell.lms.lmsautovista.Model.POCarStockBean;
import com.excell.lms.lmsautovista.Presenter.POCStockPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.POCStockCountAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

public class POCStockFragment extends Fragment implements IView.PocCarStockView{

    View view;
    POCStockPresenter pocStockPresenter;
    ArrayList<POCarStockBean.Poc_stock> newStockList = new ArrayList<POCarStockBean.Poc_stock>();
    AlertDialog altDialog;
    ArrayList<POCCarStockCountBean.Poc_Stock_Count> newPocCountList = new ArrayList<POCCarStockCountBean.Poc_Stock_Count>();


    String selectedLocationDashboard, selectedLocationDashboardId, selectedMakeId, selectedMfgYear, selectedMfgYearId, selectedOwner, selectedOwnerId;
    String leadLocationSpinner,selectedLeadLocationId, leadModelSpinner,leadMakeSpinner, selectedLeadModelId, fuelTypeSpinner, colorFilterSpinner;
    String selectedAgeing, selectedAgeingID, selectedPrice, selectedPriceId;

    @BindView(R.id.filterSearchNewStock_TXTview)
    TextView filterSearchNewStock_TXTview;

    @BindView(R.id.newCarStockFilterDetails_ListView)
    RecyclerView newCarStockFilterDetails_ListView;

    @BindView(R.id.pocCount_recyclerView)
    RecyclerView pocCount_recyclerView;

    //filter fields
    @BindView(R.id.filterSearchMessageNewStock_TXTview)
    TextView filterSearchMessageNewStock_TXTview;


    //id for filter spinner
    Spinner fuelTypeStockFilter_spinner;
    Spinner locationStockFilter_spinner;
    Spinner colorStockFilter_spinner;
    TextView cancelStockFilter_txtView;
    TextView applyStockFilter_txtView;
    Spinner modelStockFilter_spinner;
    Spinner makeStockFilter_spinner, mfgYearStockFilter_spinner,ownerStockFilter_spinner,priceStockFilter_spinner,ageingStockFilter_spinner;

    Map<String, String> modelMap = new HashMap<>();
    Map<String, String> makeMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_stock, container, false);
        ButterKnife.bind(this, view);
        initialiseUI();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (NetworkUtilities.isInternet(getActivity())) {
          //  pocStockPresenter.getPocCarStockList(getActivity());
        //    pocStockPresenter.getPocCarStockCount(leadMakeSpinner,leadModelSpinner,leadLocationSpinner,selectedMfgYearId, selectedOwnerId, selectedAgeingID, selectedPriceId, getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialiseUI(){
        pocStockPresenter = new POCStockPresenter(this);
     //   pocStockPresenter.getPocCarStockFirstCount(getActivity());
        filterSearchMessageNewStock_TXTview.setVisibility(View.VISIBLE);
        pocCount_recyclerView.setVisibility(GONE);
    }

    @OnClick(R.id.filterSearchNewStock_TXTview)
    public void filterSearchNewStock(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = li.inflate(R.layout.stock_filter_layout, null);

        fuelTypeStockFilter_spinner = (Spinner) view.findViewById(R.id.fuelTypeStockFilter_spinner);
        locationStockFilter_spinner = (Spinner) view.findViewById(R.id.locationStockFilter_spinner);
        colorStockFilter_spinner = (Spinner) view.findViewById(R.id.colorStockFilter_spinner);
        cancelStockFilter_txtView = (TextView) view.findViewById(R.id.cancelStockFilter_txtView);
        applyStockFilter_txtView = (TextView) view.findViewById(R.id.applyStockFilter_txtView);
        modelStockFilter_spinner = (Spinner) view.findViewById(R.id.modelStockFilter_spinner);
        makeStockFilter_spinner = (Spinner) view.findViewById(R.id.makeStockFilter_spinner);

        mfgYearStockFilter_spinner= (Spinner) view.findViewById(R.id.mfgYearStockFilter_spinner);
        ownerStockFilter_spinner= (Spinner) view.findViewById(R.id.ownerStockFilter_spinner);
        priceStockFilter_spinner= (Spinner) view.findViewById(R.id.priceStockFilter_spinner);
        ageingStockFilter_spinner= (Spinner) view.findViewById(R.id.ageingStockFilter_spinner);

        makeStockFilter_spinner.setVisibility(View.VISIBLE);
        modelStockFilter_spinner.setVisibility(GONE);
        filterSearchMessageNewStock_TXTview.setVisibility(GONE);


        fuelTypeStockFilter_spinner.setVisibility(GONE);
        colorStockFilter_spinner.setVisibility(GONE);

        getMfgYearDetails();
        getPriceDetails();
        getAgeingDetails();
        getOwnerDetails();

        // final EditText etName = (EditText) view.findViewById(R.id.etName);

        pocStockPresenter.getMake(getActivity());
        pocStockPresenter.getColor(getActivity());
        pocStockPresenter.getFuelType(getActivity());
        pocStockPresenter.getLocation(getActivity());
        pocStockPresenter.getModel(selectedMakeId,getActivity());

        //   TextView cancelStockFilter_txtView = (TextView) view.findViewById(R.id.cancelStockFilter_txtView);
        cancelStockFilter_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altDialog.dismiss();
            }
        });
        //  TextView applyStockFilter_txtView = (TextView) view.findViewById(R.id.applyStockFilter_txtView);
        applyStockFilter_txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altDialog.dismiss();
                pocStockPresenter.getPocCarStockCount(leadMakeSpinner,leadModelSpinner,leadLocationSpinner,selectedMfgYearId, selectedOwnerId, selectedAgeingID, selectedPriceId, getActivity());


              //  pocStockPresenter.getPocCarStockList(selectedMakeId,leadModelSpinner,leadLocationSpinner,colorFilterSpinner, fuelTypeSpinner, getActivity());
            }
        });
        builder.setView(view);
        altDialog = builder.create();
        altDialog.show();
    }

    public void getMfgYearDetails(){
        ArrayList<String> mfgYearArrayList = new ArrayList<>();
        mfgYearArrayList.add("Mfg Year");
        mfgYearArrayList.add("Before 2010");
        mfgYearArrayList.add("2010 - 2012");
        mfgYearArrayList.add("2012 - 2015");
        mfgYearArrayList.add("After 2015");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mfgYearArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mfgYearStockFilter_spinner.setAdapter(contactArrayAdapter);

        mfgYearStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMfgYear = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedMfgYear.equals("Mfg Year")){
                    selectedMfgYearId = "";
                }else if (selectedMfgYear.equals("Before 2010")){
                    selectedMfgYearId = "1";
                }else if (selectedMfgYear.equals("2010 - 2012")){
                    selectedMfgYearId = "2";
                }else if (selectedMfgYear.equals("2012 - 2015")){
                    selectedMfgYearId = "3";
                }else if (selectedMfgYear.equals("After 2015")){
                    selectedMfgYearId = "4";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getOwnerDetails(){
        ArrayList<String> ownerArrayList = new ArrayList<>();
        ownerArrayList.add("Owner");
        ownerArrayList.add("First");
        ownerArrayList.add("Second");
        ownerArrayList.add("More than two");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, ownerArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownerStockFilter_spinner.setAdapter(contactArrayAdapter);

        ownerStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOwner = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedOwner.equals("Owner")){
                    selectedOwnerId = "";
                }else if (selectedOwner.equals("First")){
                    selectedOwnerId = "1";
                }else if (selectedOwner.equals("Second")){
                    selectedOwnerId = "2";
                }else if (selectedOwner.equals("More than two")){
                    selectedOwnerId = "3";
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
        ageingStockFilter_spinner.setAdapter(contactArrayAdapter);

        ageingStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void getPriceDetails(){
        ArrayList<String> priceArrayList = new ArrayList<>();
        priceArrayList.add("Price");
        priceArrayList.add("Less than 2 lakh");
        priceArrayList.add("2 to 5 lakh");
        priceArrayList.add("More than 5 lakh");
        ArrayAdapter<String> contactArrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, priceArrayList);
        contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceStockFilter_spinner.setAdapter(contactArrayAdapter);

        priceStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPrice = (String) parent.getItemAtPosition(position);
                //     selectedDateTypeId = String.valueOf(position);

                if (selectedPrice.equals("Price")){
                    selectedPriceId= "";
                }else if (selectedPrice.equals("Less than 2 lakh")){
                    selectedPriceId = "1";
                }else if (selectedPrice.equals("2 to 5 lakh")){
                    selectedPriceId = "2";
                }else if (selectedPrice.equals("More than 5 lakh")){
                    selectedPriceId = "3";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void showPOCCarStockList(POCarStockBean jsonObject) {
       /* try{
        newStockList.clear();
        newStockList.addAll(jsonObject.getPoc_stock());

        POCStockAdapter pocStockAdapter = new POCStockAdapter(getActivity(),jsonObject.getPoc_stock());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newCarStockFilterDetails_ListView.setLayoutManager(mLayoutManager);
        newCarStockFilterDetails_ListView.setItemAnimator(new DefaultItemAnimator());
        newCarStockFilterDetails_ListView.setAdapter(pocStockAdapter);
        }catch(Exception e){
        }*/
    }

    @Override
    public void showPocCarStockCount(POCCarStockCountBean jsonObject) {
            try {
                newPocCountList.clear();
                newPocCountList.addAll(jsonObject.getPoc_stock_count());

                POCStockCountAdapter pocStockAdapter = new POCStockCountAdapter(getActivity(), jsonObject.getPoc_stock_count());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                pocCount_recyclerView.setVisibility(View.VISIBLE);

                pocCount_recyclerView.setLayoutManager(mLayoutManager);
                pocCount_recyclerView.setItemAnimator(new DefaultItemAnimator());
                pocCount_recyclerView.setAdapter(pocStockAdapter);

            } catch (Exception e) {
            }

    }

    @Override
    public void showPOCCarModelSpinner(POCStockModel jsonObject) {
        try {
            ArrayList<String> modelArrayList = new ArrayList<>();
            modelArrayList.clear();
            modelArrayList.add("Model");
            if (jsonObject.getPoc_stock_color().size() > 0) {
                for (int i = 0; i < jsonObject.getPoc_stock_color().size(); i++) {
                    try {
                        modelArrayList.add(jsonObject.getPoc_stock_color().get(i).getModel());
                     } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, modelArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            modelStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

          modelStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leadModelSpinner = (String) parent.getItemAtPosition(position);
                    if (leadModelSpinner.equals("Model")){
                        leadModelSpinner = "";
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
    public void showPOCCarLocationSpinner(POCSpinnerBean jsonObject) {
        try {
            ArrayList<String> locationArrayList = new ArrayList<>();
            locationArrayList.add("Location");
            if (jsonObject.getPoc_stock_location().size() > 0) {
                for (int i = 0; i < jsonObject.getPoc_stock_location().size(); i++) {
                    try {
                        locationArrayList.add(jsonObject.getPoc_stock_location().get(i).getStock_location());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locationStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            locationStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    public void showPOCCarColorSpinner(POCSpinnerBean jsonObject) {
        try {
            ArrayList<String> colorArrayList = new ArrayList<>();
            colorArrayList.add("Color");
            if (jsonObject.getPoc_stock_color().size() > 0) {
                for (int i = 0; i < jsonObject.getPoc_stock_color().size(); i++) {
                    try {
                        colorArrayList.add(jsonObject.getPoc_stock_color().get(i).getColor());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, colorArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            colorStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            colorStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    colorFilterSpinner = (String) parent.getItemAtPosition(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void showPOCCarFuelTypeSpinner(POCSpinnerBean jsonObject) {
        try {
            ArrayList<String> fuelTypeArrayList = new ArrayList<>();
            fuelTypeArrayList.add("Fuel Type");
            if (jsonObject.getPoc_stock_fuel_type().size() > 0) {
                for (int i = 0; i < jsonObject.getPoc_stock_fuel_type().size(); i++) {
                    try {
                        fuelTypeArrayList.add(jsonObject.getPoc_stock_fuel_type().get(i).getFuel_type());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fuelTypeArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fuelTypeStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            fuelTypeStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fuelTypeSpinner = (String) parent.getItemAtPosition(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void showPocMakeSpinner(MakeBean jsonObject) {
        try {
            ArrayList<String> makeArrayList = new ArrayList<>();
            makeArrayList.add("Make");
            if (jsonObject.getSelect_car_make().size() > 0) {
                for (int i = 0; i < jsonObject.getSelect_car_make().size(); i++) {
                    try {
                        makeArrayList.add(jsonObject.getSelect_car_make().get(i).getMake_name());
                        makeMap.put(jsonObject.getSelect_car_make().get(i).getMake_id(), jsonObject.getSelect_car_make().get(i).getMake_name());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> locationDashboardArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, makeArrayList);
            locationDashboardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            makeStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            makeStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leadMakeSpinner = (String) parent.getItemAtPosition(position);

                    for (Map.Entry<String, String> e : makeMap.entrySet()) {
                        Object key = e.getKey();
                        Object value = e.getValue();
                        if(leadMakeSpinner.equals("Make")){
                            selectedMakeId= "";
                            leadMakeSpinner = "";
                        }else if(value.equals(leadMakeSpinner)){
                                selectedMakeId = (String) key;
                                Log.i("Selected Make : ",selectedMakeId);
                        }
                        modelStockFilter_spinner.setVisibility(View.VISIBLE);
                        pocStockPresenter.getModel(selectedMakeId,getActivity());
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }
}
