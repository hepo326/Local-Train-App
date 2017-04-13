package com.example.mohebbadawy.localtrain;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button logBtn;
    Button creditBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logBtn = (Button)findViewById(R.id.loginBtn);
        creditBtn = (Button)findViewById(R.id.creditBtn);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(in);

            }
        });



        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
                credit creditView = new credit();
                creditView.show(manager,null);



            }
        });
    }

}
