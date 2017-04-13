package com.example.mohebbadawy.localtrain;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link detailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    View view;

    MyDataBase db;
    String _id ="";
    EditText userid;
    EditText name;
    EditText address;
    EditText mobile;
    EditText age;
    EditText balance;
    EditText gender;

      public static detailsFragment newInstance(String param1, String param2) {
        detailsFragment fragment = new detailsFragment();

        return fragment;
    }

    public detailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);

        db = new MyDataBase(getActivity());

        userid = (EditText)view.findViewById(R.id.userID_Details);
        name = (EditText)view.findViewById(R.id.name_Details);
        address = (EditText)view.findViewById(R.id.address_Details);
        mobile = (EditText)view.findViewById(R.id.mobile_Details);
        age = (EditText)view.findViewById(R.id.age_Details);
        balance = (EditText)view.findViewById(R.id.balance_Details);
        gender =  (EditText)view.findViewById(R.id.gender_Details);

        userid.setText(_id);


        Cursor cursor = db.query("User",new String[]{"*"},"ID="+_id);

        if (cursor.moveToNext()){

            name.setText(cursor.getString(1));
            address.setText(cursor.getString(2));
            mobile.setText(cursor.getString(3));
            age.setText(cursor.getString(4));
            balance.setText(cursor.getString(6));
            gender.setText(cursor.getString(7));

        }else{
            Toast.makeText(getActivity(), "No users found", Toast.LENGTH_SHORT).show();
        }




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
        void onFragmentInteraction(Uri uri);
    }

}
