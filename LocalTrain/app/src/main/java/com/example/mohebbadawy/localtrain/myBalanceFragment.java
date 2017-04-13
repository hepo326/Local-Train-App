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
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link myBalanceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link myBalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myBalanceFragment extends Fragment {

    View view;

    MyDataBase db;
    Cursor cursor;

    String _id="";

    EditText userID;
    EditText userBalance;
    EditText cardNum;
    EditText pass;
    EditText amount;
    Button addBalance;

    double balance;
    double calc_balance;

    String cardNumData;
    String passwordData;
    String newBalance;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myBalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myBalanceFragment newInstance() {
        myBalanceFragment fragment = new myBalanceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public myBalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_balance, container, false);

        db = new MyDataBase(getActivity());


        userID = (EditText)view.findViewById(R.id.userID_myBalance);
        userBalance = (EditText)view.findViewById(R.id.balance_myBalance);
        cardNum  = (EditText)view.findViewById(R.id.cardNum_myBalance);
        pass = (EditText)view.findViewById(R.id.cvv_myBalance);
        amount  = (EditText)view.findViewById(R.id.amount_myBalance);
        addBalance = (Button)view.findViewById(R.id.addBalance_btn_myBalance);
        userID.setText(_id);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Getting user balance :

        cursor = db.query("user",new String[]{"BALANCE"},"ID="+_id);
        if(cursor.moveToNext()){
            balance=Double.parseDouble(cursor.getString(0));
            userBalance.setText(cursor.getString(0));
        }



///////////////////////////////////////////////////////////////////////////////////////////////////////////
        addBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardNumData=cardNum.getText().toString();
                passwordData=pass.getText().toString();
                newBalance=amount.getText().toString();

                if(!cardNumData.isEmpty()&&!passwordData.isEmpty()&&!newBalance.isEmpty()){

                    if(cardNumData.length()<15)
                    {
                        Toast.makeText(getActivity(), "Enter the card num which contains 15 Digits", Toast.LENGTH_SHORT).show();
                    }else{

                        calc_balance=balance+Double.parseDouble(newBalance);
                        db.updateBalance(_id, calc_balance + "");
                        Toast.makeText(getActivity(), "Operation done successfully!", Toast.LENGTH_SHORT).show();
                        userBalance.setText(calc_balance+"");
                        pass.setText("");
                        cardNum.setText("");
                        amount.setText("");



                    }


                }else{

                    Toast.makeText(getActivity(), "Fill empty data .. ", Toast.LENGTH_SHORT).show();
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
