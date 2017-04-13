package com.example.mohebbadawy.localtrain;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bookTicketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bookTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bookTicketFragment extends Fragment {

    View view;
    MyDataBase db;

    String _id;
    int journey_id;
    int ticket_id;
    double userBalance;

    EditText view_ticketID;
    Spinner trainType;
    Spinner arrivalStations;
    Spinner departureStations;
    Spinner trainTime;
    RadioGroup train_Degree;
    RadioGroup journey_Type;
    TextView journey_Cost;
    EditText totalCost;
    EditText balance;
    Button book_btn;

    ArrayAdapter<String>arrayAdapter;
    ArrayList<String>trainType_List ;
    ArrayList<String>arrival_List ;
    ArrayList<String>departure_List ;
    ArrayList<String> trainTime_List;



    String selected_trainType;
    String selected_Arrival;
    String selected_Departure;
    String selected_time;



    String trainDegreeResult;
    String journeyTypeResult;

    boolean train_flag;
    boolean arrival_flag;
    boolean destination_flag;
    boolean time_flag;

    boolean degree_flag;
    boolean journeyType_flag;



    double calculate_cost;

    Cursor cursor;


    private OnFragmentInteractionListener mListener;


    public static bookTicketFragment newInstance() {
        bookTicketFragment fragment = new bookTicketFragment();
        return fragment;
    }

    public bookTicketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_book_ticket, container, false);


        db = new MyDataBase(getActivity());

        view_ticketID=(EditText)view.findViewById(R.id.ticketID_txt_ticketBooking);
        trainType = (Spinner)view.findViewById(R.id.trainType_spinner);
        arrivalStations  = (Spinner)view.findViewById(R.id.arrivalStations_spinner);
        departureStations = (Spinner)view.findViewById(R.id.departureStations_spinner);
        trainTime  = (Spinner)view.findViewById(R.id.trainTime_spinner);
        train_Degree = (RadioGroup)view.findViewById(R.id.trainDegreeTradioGroup);
        journey_Type = (RadioGroup)view.findViewById(R.id.journeyTradioGroup);
        journey_Cost  = (TextView)view.findViewById(R.id.cost_Txt_bookTicket);
        totalCost = (EditText)view.findViewById(R.id.totalCost_bookingTicket_txt);
        balance = (EditText)view.findViewById(R.id.balance_bookingTicket_txt);
        book_btn = (Button)view.findViewById(R.id.bookTicket_btn);

        trainType_List = new ArrayList<>() ;

        journey_id=0;

        ticket_id=0;

        ////////////////////////////////////////////////////////////////////////////////////////////
        //Get Ticket id :

        cursor = db.query("ticket",new String[]{"max(TICKET_ID)"},null);

        if (cursor.moveToNext()) {
            if(cursor==null){
                ticket_id = 1;
            }else {
                ticket_id = cursor.getInt(0)+1;
            }
        }


        view_ticketID.setText(ticket_id+"");


///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Getting user balance :

        cursor = db.query("user",new String[]{"BALANCE"},"ID="+_id);
        if(cursor.moveToNext()){
            userBalance=Double.parseDouble(cursor.getString(0));
            balance.setText(cursor.getString(0));
        }



///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Filling in train types

        cursor = db.query("journey",new String[]{"DISTINCT TRAIN_TYPE"},null);

        System.out.println(cursor.getCount());

        trainType_List.add("--Select--");

        cursor.moveToNext();
        while (!cursor.isAfterLast()){

            trainType_List.add(cursor.getString(0));
            System.out.println(trainType_List);
            cursor.moveToNext();
        }

        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,trainType_List);
        trainType.setAdapter(arrayAdapter);



 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        trainType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_trainType=parent.getAdapter().getItem(position).toString();


                    train_flag=true;


                arrival_List = new ArrayList<>() ;
                departure_List = new ArrayList<>() ;
                trainTime_List = new ArrayList<>();


                if(selected_trainType.equals("--Select--")){

                    arrivalStations.setClickable(false);
                    departureStations.setClickable(false);
                    trainTime.setClickable(false);

                     train_flag=false;
                     arrival_flag=false;
                     destination_flag=false;
                     time_flag=false;



                }else{

                    train_flag=true;



                    arrivalStations.setClickable(true);

                    ////////////////////////////////////////////////////////////////////////////////////////
                    // Filling arrival List

                    cursor = db.query("journey",new String[]{"DISTINCT ARRIVAL"},"TRAIN_TYPE="+"\'"+selected_trainType+"\'");

                    while(cursor.moveToNext())
                    {
                        arrival_List.add(cursor.getString(0));

                    }


                }
                arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrival_List);
                arrivalStations.setAdapter(arrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //



            arrivalStations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                departure_List = new ArrayList<>();

                departureStations.setClickable(true);


                arrival_flag=true;




                selected_Arrival = parent.getAdapter().getItem(position).toString();


                ////////////////////////////////////////////////////////////////////////////////////////
                // Filling Departure List

                cursor = db.query("journey", new String[]{"DISTINCT DESTINATION"}, "TRAIN_TYPE = " + "\'" + selected_trainType + "\'" + "and ARRIVAL = " + "\'" + selected_Arrival + "\'");


                while (cursor.moveToNext()) {

                    departure_List.add(cursor.getString(0));

                }

                arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, departure_List);
                departureStations.setAdapter(arrayAdapter);

            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//




            departureStations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    destination_flag=true;

                    trainTime.setClickable(true);


                    trainTime_List = new ArrayList<>();

                    selected_Departure = parent.getAdapter().getItem(position).toString();


                    ////////////////////////////////////////////////////////////////////////////////////////
                    // Filling Time List

                    cursor = db.query("journey", new String[]{"DISTINCT FIRST_DATE , SECOND_DATE  "}, "TRAIN_TYPE = " + "\'" + selected_trainType + "\'" + "and ARRIVAL = " + "\'" + selected_Arrival + "\'" + "and DESTINATION = " + "\'" + selected_Departure + "\'");


                    while (cursor.moveToNext()) {


                        trainTime_List.add(cursor.getString(0));

                    }

                    arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, trainTime_List);
                    trainTime.setAdapter(arrayAdapter);

                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        //////////////////////////////////////////////////////////////////////////////////////////////
        ///
        trainTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                time_flag = true;

                selected_time = parent.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////
        //


        train_Degree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                 degree_flag = true;

                RadioButton single = (RadioButton)getActivity().findViewById(R.id.firstDegreeType);

                if (single.isChecked())
                {
                    trainDegreeResult="FIRST_DEGREE_COST";
                }else{
                    trainDegreeResult="SECOND_DEGREE_COST";

                }

                journey_Type.clearCheck();

                totalCost.setText("");
                journey_Cost.setText("");


            }



        });

///////////////////////////////////////////////////////////////////////////////////////////////////
        //

        journey_Type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                journeyType_flag = true;

                RadioButton single = (RadioButton)getActivity().findViewById(R.id.singleJourneyType);

                if (single.isChecked())
                {
                    journeyTypeResult="Single";
                }else{
                    journeyTypeResult="Return";

                }

                ///////////////////////////////////////////////////////////////////////////////////////////////////
                //

                if(train_flag && arrival_flag && destination_flag && time_flag && degree_flag&&journeyType_flag ){

                    String cost ="";


                    cursor=db.query("journey",new String[]{"ID"},"TRAIN_TYPE="+ "\'" + selected_trainType + "\'" +"and FIRST_DATE = "+ "\'" + selected_time + "\'" +" and ARRIVAL = " + "\'" + selected_Arrival + "\'" + " and DESTINATION = "+ "\'" + selected_Departure + "\'" );

                    if(cursor.moveToNext()){
                        journey_id=cursor.getInt(0);
                    }else{

                        cursor=db.query("journey",new String[]{"ID"},"TRAIN_TYPE="+ "\'" + selected_trainType + "\'" +"and SECOND_DATE = "+ "\'" + selected_time + "\'" +" and ARRIVAL = " + "\'" + selected_Arrival + "\'" + " and DESTINATION = "+ "\'" + selected_Departure + "\'" );
                        if(cursor.moveToNext()){
                            journey_id=cursor.getInt(0);
                        }
                    }

                    if(journey_id!=0) {

                        if (trainDegreeResult.equals("FIRST_DEGREE_COST")) {

                            cursor = db.query("journey", new String[]{"FIRST_DEGREE_COST"}, "ID=" + journey_id);
                            if (cursor.moveToNext()) {
                                cost = cursor.getString(0);
                                journey_Cost.setText(cost);
                            }

                        }else if(trainDegreeResult.equals("SECOND_DEGREE_COST")){

                            cursor = db.query("journey", new String[]{"SECOND_DEGREE_COST"}, "ID=" + journey_id);
                            if (cursor.moveToNext()) {
                                cost = cursor.getString(0);
                                journey_Cost.setText(cost);
                            }


                        }

                    }

                    calculate_cost=Double.parseDouble(cost);


                    if(journeyTypeResult.equals("Single")) {

                        totalCost.setText(calculate_cost+"");
                    }
                    else{
                        calculate_cost=2*calculate_cost;

                        totalCost.setText(2*calculate_cost+"");
                    }


                }

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////
        // Book Btn;

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(train_flag && arrival_flag && destination_flag && time_flag && degree_flag&&journeyType_flag ) {

                    calculate_cost=userBalance-calculate_cost;

                    if(calculate_cost>0) {

                        balance.setText(calculate_cost+"");

                        db.updateBalance(_id, calculate_cost + "");

                        db.ticket_insertData(_id, journey_id + "");

                        Toast.makeText(getActivity(), "Ticked is booked successfully !", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(getActivity(), "No enough money!", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(getActivity(), "Complete your form ", Toast.LENGTH_SHORT).show();
                }



                }
        });















        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
