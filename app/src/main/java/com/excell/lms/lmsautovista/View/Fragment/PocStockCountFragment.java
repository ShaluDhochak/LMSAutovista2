package com.excell.lms.lmsautovista.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.excell.lms.lmsautovista.Model.PocStockCountBean;
import com.excell.lms.lmsautovista.R;
import com.excell.lms.lmsautovista.Utils.GSONRequest;
import com.excell.lms.lmsautovista.Utils.HorizontalScroll;
import com.excell.lms.lmsautovista.Utils.Utilities;
import com.excell.lms.lmsautovista.Utils.VerticalScroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PocStockCountFragment extends Fragment implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener {

    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;
    RelativeLayout relativeLayoutMain;

    RelativeLayout relativeLayoutA;
    RelativeLayout relativeLayoutB;
    RelativeLayout relativeLayoutC;
    RelativeLayout relativeLayoutD;

    TableLayout tableLayoutA;
    TableLayout tableLayoutB;
    TableLayout tableLayoutC;
    TableLayout tableLayoutD;

    TableRow tableRow;
    TableRow tableRowB;

    HorizontalScroll horizontalScrollViewB;
    HorizontalScroll horizontalScrollViewD;

    VerticalScroll scrollViewC;
    VerticalScroll scrollViewD;

    ArrayList<String> tableHeading= new ArrayList<>();

    /*
         This is for counting how many columns are added in the row.
    */
    int tableColumnCountB= 0;

    /*
         This is for counting how many row is added.
    */
    int tableRowCountC= 0;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_poc_stock_count, container, false);
        initialiseUI();
        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseUI(){
        tableHeading.add("Make");
        tableHeading.add("Model");
        tableHeading.add("Mfg Year (before 2010)");
        tableHeading.add("Mfg Year (2010 - 2012)");
        tableHeading.add("Mfg Year (2012 - 2015)");
        tableHeading.add("Mfg Year (After 2015)");

        tableHeading.add("Owner 1");
        tableHeading.add("Owner 2");
        tableHeading.add("Owner (More than two)");

        tableHeading.add("Ageing (Before 15 days)");
        tableHeading.add("Ageing (15 to 30 days)");
        tableHeading.add("Ageing (31 to 60 days)");
        tableHeading.add("Ageing (More than 60 days)");

        tableHeading.add("Price (Less than 2 lakh)");
        tableHeading.add("Price (2 to 5 lakh)");
        tableHeading.add("Price (More than 5 lakh)");

        relativeLayoutMain= (RelativeLayout)view.findViewById(R.id.relativeLayoutMain);
        getScreenDimension();
        initializeRelativeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollViewB.setScrollViewListener(this);
        horizontalScrollViewD.setScrollViewListener(this);
        scrollViewC.setScrollViewListener(this);
        scrollViewD.setScrollViewListener(this);
        addRowToTableA();
        initializeRowForTableB();

        /*  There is two unused functions
            Have a look on these functions and try to recreate and use it.
            createCompleteColumn();
            createCompleteRow();
        */
     /*   for(int i=0; i<9; i++){

            // row haeding is entered from here
            addColumnsToTableB("Head" + i, i);
        }
        */

        for(int i=0; i<tableHeading.size(); i++){
            // row haeding is entered from here
            addColumnsToTableB(tableHeading.get(i).toString(), i);
        }
        //here i is gsondata column count
        Map<String,String> appLeadHashMap = new HashMap<>();
        String url ="http://vistacars.in/all_lms/index.php/api/poc_stock_count";

        GSONRequest<PocStockCountBean> gsonRequest = new GSONRequest<PocStockCountBean>(
                Request.Method.POST, url , PocStockCountBean.class,appLeadHashMap,
                new Response.Listener<PocStockCountBean>() {
                    @Override
                    public void onResponse(PocStockCountBean response) {

                        try {
                            if (response.getPoc_stock_count().size() > 0) {

                                for(int i=0; i<response.getPoc_stock_count().size(); i++){
                                    initializeRowForTableD(i);

                                    // coulum text from here
                                    addRowToTableC(String.valueOf(i));
                                    //  for(int j=0; j<tableColumnCountB; j++){
                                    int ik =i;
                                    int size = response.getPoc_stock_count().size();
                                    if (  ik <= response.getPoc_stock_count().size()){
                                        //   addColumnToTableAtD(i, "D "+ ik + " " + j +" " );

                                      //  Toast.makeText(getActivity(), ik +" /" +size, Toast.LENGTH_SHORT).show();

                                        addColumnToTableAtD(ik, response.getPoc_stock_count().get(i).getMake_name());
                                        String submodel =  response.getPoc_stock_count().get(i).getSubmodel() + "( " + response.getPoc_stock_count().get(i).getModel_count() + " )";
                                        addColumnToTableAtD(ik,submodel);
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getMfg_year_1());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getMfg_year_2());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getMfg_year_3());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getMfg_year_4());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getOwner_1());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getOwner_2());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getOwner_3());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getAgeing_1());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getAgeing_2());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getAgeing_3());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getAgeing_4());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getPrice_1());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getPrice_2());
                                        addColumnToTableAtD(ik,response.getPoc_stock_count().get(i).getPrice_3());
                                        // row data is entered from here from gson
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_SHORT).show();
                            }
                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        Utilities.getRequestQueue(getActivity()).add(gsonRequest);
    }

    private void getScreenDimension(){
        WindowManager wm= (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }

    private void initializeRelativeLayout(){
        relativeLayoutA= new RelativeLayout(getActivity());
        relativeLayoutA.setId(R.id.relativeLayoutA);
        relativeLayoutA.setPadding(0,0,0,0);

        relativeLayoutB= new RelativeLayout(getActivity());
        relativeLayoutB.setId(R.id.relativeLayoutB);
        relativeLayoutB.setPadding(0,0,0,0);

        relativeLayoutC= new RelativeLayout(getActivity());
        relativeLayoutC.setId(R.id.relativeLayoutC);
        relativeLayoutC.setPadding(0,0,0,0);

        relativeLayoutD= new RelativeLayout(getActivity());
        relativeLayoutD.setId(R.id.relativeLayoutD);
        relativeLayoutD.setPadding(0,0,0,0);

        relativeLayoutA.setLayoutParams(new RelativeLayout.LayoutParams(SCREEN_WIDTH/5,SCREEN_HEIGHT/15));
        this.relativeLayoutMain.addView(relativeLayoutA);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutB= new RelativeLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/5), SCREEN_HEIGHT/15);
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA);
        relativeLayoutB.setLayoutParams(layoutParamsRelativeLayoutB);
        this.relativeLayoutMain.addView(relativeLayoutB);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutC= new RelativeLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/15));
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA);
        relativeLayoutC.setLayoutParams(layoutParamsRelativeLayoutC);
        this.relativeLayoutMain.addView(relativeLayoutC);

        RelativeLayout.LayoutParams layoutParamsRelativeLayoutD= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/15));
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB);
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC);
        relativeLayoutD.setLayoutParams(layoutParamsRelativeLayoutD);
        this.relativeLayoutMain.addView(relativeLayoutD);
    }

    private void initializeScrollers(){
        horizontalScrollViewB= new HorizontalScroll(getActivity());
        horizontalScrollViewB.setPadding(0,0,0,0);

        horizontalScrollViewD= new HorizontalScroll(getActivity());
        horizontalScrollViewD.setPadding(0,0,0,0);

        scrollViewC= new VerticalScroll(getActivity());
        scrollViewC.setPadding(0,0,0,0);

        scrollViewD= new VerticalScroll(getActivity());
        scrollViewD.setPadding(0,0,0,0);

        horizontalScrollViewB.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/15));
        scrollViewC.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH/5 ,SCREEN_HEIGHT - (SCREEN_HEIGHT/15)));
        scrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/15) ));
        horizontalScrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/15) ));

        this.relativeLayoutB.addView(horizontalScrollViewB);
        this.relativeLayoutC.addView(scrollViewC);
        this.scrollViewD.addView(horizontalScrollViewD);
        this.relativeLayoutD.addView(scrollViewD);
    }

    private  void initializeTableLayout(){
        tableLayoutA= new TableLayout(getActivity());
        tableLayoutA.setPadding(0,0,0,0);
        tableLayoutB= new TableLayout(getActivity());
        tableLayoutB.setPadding(0,0,0,0);
        tableLayoutB.setId(R.id.tableLayoutB);
        tableLayoutC= new TableLayout(getActivity());
        tableLayoutC.setPadding(0,0,0,0);
        tableLayoutD= new TableLayout(getActivity());
        tableLayoutD.setPadding(0,0,0,0);

        TableLayout.LayoutParams layoutParamsTableLayoutA= new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/15);
        tableLayoutA.setLayoutParams(layoutParamsTableLayoutA);
        //  tableLayoutA.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tableLayoutA.setBackgroundColor(getResources().getColor(R.color.darkBlueWebsite));
        this.relativeLayoutA.addView(tableLayoutA);

        TableLayout.LayoutParams layoutParamsTableLayoutB= new TableLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/5), SCREEN_HEIGHT/15);
//        TableLayout.LayoutParams layoutParamsTableLayoutB= new TableLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/3), SCREEN_HEIGHT/15);
        tableLayoutB.setLayoutParams(layoutParamsTableLayoutB);
        tableLayoutB.setBackgroundColor(getResources().getColor(R.color.darkBlueWebsite));
        this.horizontalScrollViewB.addView(tableLayoutB);

        TableLayout.LayoutParams layoutParamsTableLayoutC= new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/15));
        tableLayoutC.setLayoutParams(layoutParamsTableLayoutC);
        tableLayoutC.setBackgroundColor(getResources().getColor(R.color.darkBlueWebsite));
        this.scrollViewC.addView(tableLayoutC);

        TableLayout.LayoutParams layoutParamsTableLayoutD= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tableLayoutD.setLayoutParams(layoutParamsTableLayoutD);
        this.horizontalScrollViewD.addView(tableLayoutD);
    }

    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == horizontalScrollViewB){
            horizontalScrollViewD.scrollTo(x,y);
        }
        else if(scrollView == horizontalScrollViewD){
            horizontalScrollViewB.scrollTo(x, y);
        }
    }

    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollViewC){
            scrollViewD.scrollTo(x,y);
        }
        else if(scrollView == scrollViewD){
            scrollViewC.scrollTo(x,y);
        }
    }

    private void addRowToTableA(){
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/15);
        tableRow.setLayoutParams(layoutParamsTableRow);

        TextView label_date = new TextView(getActivity());
        label_date.setText("Sr. No");
        label_date.setPadding(5, 5, 5, 5);
        label_date.setTextColor(getResources().getColor(R.color.colorPlatinum));
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        // label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.addView(label_date);
        this.tableLayoutA.addView(tableRow);
    }

    private void initializeRowForTableB(){
        tableRowB= new TableRow(getActivity());
        tableRow.setPadding(0,0,0,0);
        this.tableLayoutB.addView(tableRowB);
    }

    private synchronized void addColumnsToTableB(String text, final int id){
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/15);
//        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);

        tableRow.setPadding(3,3,3,3);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getActivity());
        label_date.setText(text);
        label_date.setPadding(5, 2, 5, 2);
        label_date.setGravity(Gravity.CENTER);
        label_date.setTextColor(getResources().getColor(R.color.colorPlatinum));
        //  label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        this.tableRow.addView(label_date);
        this.tableRow.setTag(id);
        this.tableRowB.addView(tableRow);
        tableColumnCountB++;
    }

    @SuppressLint("ResourceAsColor")
    private synchronized void addRowToTableC(String text){
        TableRow tableRow1= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow1= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/15);
        tableRow1.setPadding(3,3,3,4);
        tableRow1.setLayoutParams(layoutParamsTableRow1);

        //set sr no here
        TextView label_date = new TextView(getActivity());
        label_date.setText(text);
        label_date.setPadding(5,5,5,5);
        label_date.setGravity(Gravity.CENTER);
        label_date.setTextColor(getResources().getColor(R.color.colorPlatinum));

        //label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow1.addView(label_date);

        TableRow tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/15);
        tableRow.setPadding(0,0,0,0);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.addView(tableRow1);
        this.tableLayoutC.addView(tableRow, tableRowCountC);
        tableRowCountC++;
    }

    private synchronized void initializeRowForTableD(int pos){
        TableRow tableRowB= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT/15);
        tableRowB.setPadding(0,0,0,0);
        tableRowB.setLayoutParams(layoutParamsTableRow);
        this.tableLayoutD.addView(tableRowB, pos);
    }

    @SuppressLint("ResourceAsColor")
    private synchronized void addColumnToTableAtD(final int rowPos, String text){
        TableRow tableRowAdd= (TableRow) this.tableLayoutD.getChildAt(rowPos);
        tableRow= new TableRow(getActivity());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/3, SCREEN_HEIGHT/15);
        tableRow.setPadding(3,3,3,4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tableRow.setBackground(getResources().getDrawable(R.drawable.cell_background));
        }
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getActivity());
        label_date.setText(text);
        label_date.setTextColor(R.color.edittextcolor);
        //  label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.setTag(label_date);
        this.tableRow.addView(label_date);
        tableRowAdd.addView(tableRow);
    }

    private void createCompleteColumn(String value){
        int i=0;
        int j=tableRowCountC-1;
        for(int k=i; k<=j; k++){
            addColumnToTableAtD(k, value);
        }
    }

    private void createCompleteRow(String value){
        initializeRowForTableD(0);
        int i=0;
        int j=tableColumnCountB-1;
        int pos= tableRowCountC-1;
        for(int k=i; k<=j; k++){
            addColumnToTableAtD(pos, value);
        }
    }


}

