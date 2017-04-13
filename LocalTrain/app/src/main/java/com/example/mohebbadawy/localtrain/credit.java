package com.example.mohebbadawy.localtrain;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link credit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link credit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class credit extends DialogFragment implements View.OnClickListener {

    Button backBtn;
    View form;

    public credit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         form = inflater.inflate(R.layout.fragment_credit, container, false);


        getDialog().setTitle("About us");

        backBtn = (Button)form.findViewById(R.id.bkBtn);


        backBtn.setOnClickListener(this);



        return form;


    }


    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
