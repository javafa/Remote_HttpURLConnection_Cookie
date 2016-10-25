package com.kodonho.android.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import static com.kodonho.android.remote_httpurlconnection_cookie.Remote.postData;

public class MainActivity extends AppCompatActivity {

    EditText etId;
    EditText etPassword;
    Button btnSignin;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnSignin = (Button) findViewById(R.id.button2);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void signin(){

        Map userInfo = new HashMap();
        userInfo.put("user_id",etId.getText().toString());
        userInfo.put("user_pwd",etPassword.getText().toString());

        new AsyncTask<Map,Void,String>(){
            ProgressDialog progress;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }
            @Override
            protected String doInBackground(Map... params) {
                String result = "";
                String url = "http://192.168.0.177/setCookies.jsp";
                try {
                    result = postData(url, params[0], "POST");
                }catch(Exception e){
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                progress.dismiss();
            }
        }.execute(userInfo);
    }
}
