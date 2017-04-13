package com.example.mohebbadawy.localtrain;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText Id;
    EditText Name;
    EditText Adrs;
    EditText Mob;
    EditText Age;
    EditText pswrd;
    EditText Balance;
    RadioGroup gender;

    Button clr;
    Button register;
    Button bk;

    String date;


    String username;
    String address;
    String mobile;
    String age;
    String password;
    String  balance;
    String gender_type;

    MyDataBase db;

    int user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new MyDataBase(this);

        Id  = (EditText)findViewById(R.id.userID_txt_Registration);
        Name = (EditText)findViewById(R.id.userName_txt_Registration);
        Adrs = (EditText)findViewById(R.id.address_txt_Registration);
        Mob = (EditText)findViewById(R.id.mobile_txt_Registration);
        Age = (EditText)findViewById(R.id.age_txt_Registration);
        pswrd = (EditText)findViewById(R.id.pass_txt_Registration);
        Balance = (EditText)findViewById(R.id.balance_txt_Registration);

        gender =(RadioGroup)findViewById(R.id.gender_radiogroup_Registration);

        clr = (Button)findViewById(R.id.clear_btn_Registeration);
        register = (Button)findViewById(R.id.reg_btn_Regestration);
        bk = (Button)findViewById(R.id.bk_btn_Regesteration);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //Get id :

        Cursor cursor = db.query("User",new String[]{"max(ID)"},null);

        if (cursor.moveToNext()) {
            if(cursor==null){
                user_id = 1;
            }else {
                user_id = cursor.getInt(0)+1;
            }
        }

        String num = user_id+"";

        Id.setText(num);

        gender_type="";

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
//       Gender group box :

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton male = (RadioButton) findViewById(R.id.male);


                if (male.isChecked()) {
                    gender_type = "male";

                } else {

                    gender_type = "female";
                }


            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////
         // Clear btn

            clr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Name.setText(null);
                    Adrs.setText(null);
                    Mob.setText(null);
                    Age.setText(null);
                    pswrd.setText(null);
                    Balance.setText(null);
                    gender_type="";

                    Toast.makeText(RegisterActivity.this, " Clear operation successfully done ! ", Toast.LENGTH_SHORT).show();
                }
            });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Register Button

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent out = new Intent();

                username = Name.getText().toString();
                address = Adrs.getText().toString();
                mobile = Mob.getText().toString();
                age = Age.getText().toString();
                password = pswrd.getText().toString();
                balance = Balance.getText().toString();

                if(username.length() > 0 && username.contains(" "))
                {
                    Toast.makeText(RegisterActivity.this, "No spaces allowed ! ", Toast.LENGTH_SHORT).show();
                }
                else if( !username.isEmpty() && !address.isEmpty() && !mobile.isEmpty() && !age.isEmpty() && !password.isEmpty() && !balance.isEmpty() &&
                         !gender_type.isEmpty()  )
                {


                        boolean isInserted = db.user_insertData(username,address,mobile,age,password,balance,gender_type);

                        if(isInserted) {

                            Toast.makeText(RegisterActivity.this, " Registration is done successfully . ^_^ ", Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(RegisterActivity.this, " Data not inserted !", Toast.LENGTH_SHORT).show();

                        }

                        out.putExtra("username",username);

                        setResult(RESULT_OK, out);


                        finish();



                }else{
                    Toast.makeText(RegisterActivity.this, " Complete your empty fields ! ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Back Button Action :

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);

                finish();

            }
        });







    }
}
