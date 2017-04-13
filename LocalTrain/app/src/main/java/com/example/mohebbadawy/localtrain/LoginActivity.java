package com.example.mohebbadawy.localtrain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    MyDataBase db;

    EditText uname;
    EditText pswrd;
    Button register;
    Button login ;
    Button back;

    ProgressDialog progDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new MyDataBase(this);

        uname = (EditText)findViewById(R.id.userNameTxt);
        pswrd = (EditText)findViewById(R.id.passTxt);
        register = (Button)findViewById(R.id.registerBtn);
        login = (Button) findViewById(R.id.loginBtn_LoginForm);
        back = (Button) findViewById(R.id.backBtn);




        //Login Button Action :
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = uname.getText().toString();
                String password = pswrd.getText().toString();

                String id;
                String pass;

                if(userid.length() > 0 && userid.contains(" "))
                {
                    Toast.makeText(LoginActivity.this, "No spaces allowed ! ", Toast.LENGTH_SHORT).show();
                }

                else if(!userid.isEmpty())
                {
                    if(!password.isEmpty()){

                        Cursor cursor = db.query("User", new String[]{"ID , PASSWORD "}, "ID=" + userid);



                        if (cursor.moveToNext()) {
                                id = cursor.getString(0);
                                pass = cursor.getString(1);

                        if(id.equals(userid))
                        {
                            if(pass.equals(password))
                            {
                                 progDialog= ProgressDialog.show(LoginActivity.this, "Logging!", "Please Wait ... ");


                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(10000);
                                            progDialog.dismiss();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });



                                pswrd.setText("");
                                uname.setText("");


                                Intent in = new Intent(LoginActivity.this , OptionsActivity.class);

                                in.putExtra("user_id",id);
                                
                                startActivity(in);

//                                onBackPressed();


                            }else{
                                Toast.makeText(LoginActivity.this, "Wrong Pass entered!", Toast.LENGTH_SHORT).show();

                            }
                        }else{

                            Toast.makeText(LoginActivity.this, "Wrong ID entered!", Toast.LENGTH_SHORT).show();
                        }

                        }else{

                            Toast.makeText(LoginActivity.this, "Wrong ID and Password!!", Toast.LENGTH_SHORT).show();

                        }


                    }else{
                        Toast.makeText(LoginActivity.this, "Fill in your password!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Fill in your username!", Toast.LENGTH_SHORT).show();
                }



            }
        });




        ////////////////////////////////////////////////////////////////////////////////////////////
        // Register button action :

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(LoginActivity.this , RegisterActivity.class);

                startActivityForResult(in , 10);


            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        // Back Button :

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(LoginActivity.this, "Farewell! ", Toast.LENGTH_LONG).show();
                finish();

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==10){
            if(resultCode==RESULT_OK) {

                Bundle bundle = data.getExtras();
                String name = bundle.getString("username");
                Toast.makeText(LoginActivity.this, " Congratulations Mr : " + name + "!  Now you can log in . ", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(LoginActivity.this, " Regestration failed ..  ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();


        if(progDialog!=null)
            progDialog.dismiss();


    }
}
