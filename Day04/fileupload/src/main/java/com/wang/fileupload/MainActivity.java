package com.wang.fileupload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fileupload(View view) {
        try {
            EditText et_filepath = (EditText) findViewById(R.id.et_filepath);
            String filepath = et_filepath.getText().toString().trim();

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            RequestParams requestParams = new RequestParams();
            requestParams.put("filename", new File(filepath));

            asyncHttpClient.post("http://10.42.0.1:8080/wang/servlet/UploaderServlet",
                    requestParams, new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient
                                .Header[] headers, byte[] responseBody) {
                            Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient
                                .Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
