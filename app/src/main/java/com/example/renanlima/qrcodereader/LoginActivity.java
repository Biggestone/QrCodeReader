package com.example.renanlima.qrcodereader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText)findViewById(R.id.et_user);
        password = (EditText)findViewById(R.id.et_password);
    }

    public void userLogin(View view) {
        AuthenticateUser authenticateUser = new AuthenticateUser();
        authenticateUser.execute(user.getText().toString(),password.getText().toString());

    }

    private class AuthenticateUser extends AsyncTask<String,Void,String>{

        private ProgressDialog mProgressDialog;


        @Override
        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(LoginActivity.this,"Aguarde","Consultando dados...");
        }

        @Override
        protected String doInBackground(String... params) {

            if(params[0].equalsIgnoreCase("usuário")&&params[1].equalsIgnoreCase("1234")){

                return "ok";
            }else{
                return "notOk";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            mProgressDialog.dismiss();

            if(s.equals("ok")){
                Intent intent = new Intent(LoginActivity.this,WatchVideosActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Você não pode realizar login",Toast.LENGTH_LONG).show();
            }
        }
    }


}
