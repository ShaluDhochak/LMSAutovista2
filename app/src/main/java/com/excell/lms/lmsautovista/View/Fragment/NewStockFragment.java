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

import com.excell.lms.lmsautovista.Model.ModelBean;
import com.excell.lms.lmsautovista.Model.NewCarStockBean;
import com.excell.lms.lmsautovista.Model.NewStockFilterBean;
import com.excell.lms.lmsautovista.Presenter.NewCarStockPresenter;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.NetworkUtilities;
import com.excell.lms.lmsautovista.View.Adapter.NewCarStockAdapter;
import com.excell.lms.lmsautovista.View.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewStockFragment extends Fragment implements IView.NewCarStockView{
    View view;
    NewCarStockPresenter newCarStockPresenter;
    ArrayList<NewCarStockBean.New_Car_Stock> newStockList = new ArrayList<NewCarStockBean.New_Car_Stock>();
    AlertDialog altDialog;

    String selectedLocationDashboard, selectedLocationDashboardId;
    String leadLocationSpinner,selectedLeadLocationId, leadModelSpinner, selectedLeadModelId, fuelTypeSpinner, colorFilterSpinner;

    @BindView(R.id.filterSearchNewStock_TXTview)
    TextView filterSearchNewStock_TXTview;

    @BindView(R.id.newCarStockFilterDetails_ListView)
    RecyclerView newCarStockFilterDetails_ListView;

    //id for filter spinner
    Spinner fuelTypeStockFilter_spinner;
    Spinner locationStockFilter_spinner;
    Spinner colorStockFilter_spinner;
    TextView cancelStockFilter_txtView;
    TextView applyStockFilter_txtView;
    Spinner modelStockFilter_spinner;

    Map<String, String> modelMap = new HashMap<>();

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
            newCarStockPresenter.getNewCarStockList(getActivity());
        } else {
            Toast.makeText(getActivity(), "Check Internet connectivity.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initialiseUI(){
        newCarStockPresenter = new NewCarStockPresenter(this);
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
        // final EditText etName = (EditText) view.findViewById(R.id.etName);

        newCarStockPresenter.getColor(getActivity());
        newCarStockPresenter.getFuelType(getActivity());
        newCarStockPresenter.getLocation(getActivity());
        newCarStockPresenter.getModel(getActivity());

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
               newCarStockPresenter.getnewCarStockList(selectedLeadModelId,leadLocationSpinner,colorFilterSpinner, fuelTypeSpinner, getActivity());
            }
        });
        builder.setView(view);
        altDialog = builder.create();
        altDialog.show();
    }

    @Override
    public void showNewCarStockList(NewCarStockBean jsonObject) {
        try{
        newStockList.clear();
        newStockList.addAll(jsonObject.getNew_car_stock());

        NewCarStockAdapter dashboardAdapter = new NewCarStockAdapter(getActivity(),jsonObject.getNew_car_stock());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newCarStockFilterDetails_ListView.setLayoutManager(mLayoutManager);
        newCarStockFilterDetails_ListView.setItemAnimator(new DefaultItemAnimator());
        newCarStockFilterDetails_ListView.setAdapter(dashboardAdapter);
        }catch(Exception e){
        }
    }

    @Override
    public void shownewCarModelSpinner(ModelBean jsonObject) {
        try {
            ArrayList<String> modelArrayList = new ArrayList<>();
            modelArrayList.add("Model");
            if (jsonObject.getSelect_car_model().size() > 0) {
                for (int i = 0; i < jsonObject.getSelect_car_model().size(); i++) {
                    try {
                        modelArrayList.add(jsonObject.getSelect_car_model().get(i).getModel_name());
                        modelMap.put(jsonObject.getSelect_car_model().get(i).getModel_id(), jsonObject.getSelect_car_model().get(i).getModel_name());
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

                    for (Map.Entry<String, String> e : modelMap.entrySet()) {
                        Object key = e.getKey();
                        Object value = e.getValue();
                        if (leadModelSpinner.equals("Model")){
                            selectedLeadModelId= "";
                        }else{
                            if(value.equals(leadModelSpinner)) {
                                selectedLeadModelId = (String) key;
                                Log.i("Selected Model : ",selectedLeadModelId);
                            }
                        }
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
    public void showNewCarLocationSpinner(NewStockFilterBean jsonObject) {
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
            locationStockFilter_spinner.setAdapter(locationDashboardArrayAdapter);

            locationStockFilter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leadLocationSpinner = (String) parent.getItemAtPosition(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void showNewCarColorSpinner(NewStockFilterBean jsonObject) {
        try {
            ArrayList<String> colorArrayList = new ArrayList<>();
            colorArrayList.add("Color");
            if (jsonObject.getNew_stock_color().size() > 0) {
                for (int i = 0; i < jsonObject.getNew_stock_color().size(); i++) {
                    try {
                        colorArrayList.add(jsonObject.getNew_stock_color().get(i).getColor());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> colorArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, colorArrayList);
            colorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            colorStockFilter_spinner.setAdapter(colorArrayAdapter);

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
    public void showNewCarFuelTypeSpinner(NewStockFilterBean jsonObject) {
        try {
            ArrayList<String> fuelTypeArrayList = new ArrayList<>();
            fuelTypeArrayList.add("Fuel Type");
            if (jsonObject.getNew_stock_fuel_type().size() > 0) {
                for (int i = 0; i < jsonObject.getNew_stock_fuel_type().size(); i++) {
                    try {
                        fuelTypeArrayList.add(jsonObject.getNew_stock_fuel_type().get(i).getFuel_type());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            ArrayAdapter<String> fuelTypeArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fuelTypeArrayList);
            fuelTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fuelTypeStockFilter_spinner.setAdapter(fuelTypeArrayAdapter);

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
}
