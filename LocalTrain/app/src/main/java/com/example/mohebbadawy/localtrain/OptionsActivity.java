package com.example.mohebbadawy.localtrain;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class OptionsActivity extends AppCompatActivity {

    String usr_id ="";

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        spinner = (Spinner)findViewById(R.id.Options_spinner);

        Intent out = this.getIntent();
        Bundle bundle = out.getExtras();
        usr_id = bundle.getString("user_id");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                switch (position) {

                    case 1:
                        detailsFragment frag1 = new detailsFragment();
                        frag1._id =usr_id;
                        fragmentTransaction.replace(R.id.linearLayout_options, frag1, null);
                        fragmentTransaction.commit();
                        break;

                    case 2:


                        bookTicketFragment frag2 = new bookTicketFragment();
                        frag2._id=usr_id;
                        fragmentTransaction.replace(R.id.linearLayout_options, frag2, null);
                        fragmentTransaction.commit();
                        break;



                    case 3:

                        viewTicketFragment frag3 = new viewTicketFragment();
                        fragmentTransaction.replace(R.id.linearLayout_options, frag3, null);
                        fragmentTransaction.commit();
                        break;


                    case 4:

                        myBalanceFragment frag4 = new myBalanceFragment();
                        frag4._id=usr_id;
                        fragmentTransaction.replace(R.id.linearLayout_options, frag4, null);
                        fragmentTransaction.commit();
                        break;


                    case 5:

                        finish();
                        break;
                }


            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
