package com.example.mohebbadawy.localtrain;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link viewTicketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link viewTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewTicketFragment extends Fragment {

    View view;
    MyDataBase db;

    TableLayout ticketTable;
    EditText trainID;
    EditText type;
    EditText arrival;
    EditText destination;
//    EditText date;


    String key ="";
    EditText search_key;
    Button searchBtn;

    private OnFragmentInteractionListener mListener;


    public static viewTicketFragment newInstance() {
        viewTicketFragment fragment = new viewTicketFragment();
        return fragment;
    }

    public viewTicketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view= inflater.inflate(R.layout.fragment_view_ticket, container, false);

        db = new MyDataBase(getActivity());

        ticketTable = (TableLayout)view.findViewById(R.id.ticketdetails_table);
        trainID = (EditText)view.findViewById(R.id.trainID_Txt_TicketView) ;
        type = (EditText)view.findViewById(R.id.trainType_Txt_TicketView) ;
        arrival = (EditText)view.findViewById(R.id.arrival_Txt_TicketView);
        destination = (EditText)view.findViewById(R.id.destination_Txt_TicketView) ;
//        date = (EditText)view.findViewById(R.id.date_Txt_TicketView) ;

         search_key = (EditText)view.findViewById(R.id.searchKey);
         searchBtn = (Button)view.findViewById(R.id.search_Btn_TicketView) ;

        ticketTable.setVisibility(View.INVISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                key = search_key.getText().toString();

                if(!key.isEmpty()) {

                    ticketTable.setVisibility(View.VISIBLE);

                    Cursor cursor = db.query("journey",new String[]{"*"} , "ID=(select TRAIN_ID from ticket where TICKET_ID="+key+" )");

                    if(cursor.moveToNext()){

                        trainID.setText(cursor.getString(1));
                        type.setText(cursor.getString(2));
                        arrival.setText(cursor.getString(7));
                        destination.setText(cursor.getString(8));
//                        date.setText(cursor.getString(5));

                    }else{

                        trainID.setText("-");
                        type.setText("-");
                        arrival.setText("-");
                        destination.setText("-");
//                        date.setText(cursor.getString(5));


                    }

                }else{
                    Toast.makeText(getActivity(), "Enter a search key!", Toast.LENGTH_SHORT).show();
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
