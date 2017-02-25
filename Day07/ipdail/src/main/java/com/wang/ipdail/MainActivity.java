package com.wang.ipdail;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("输出");
        Toast.makeText(this, "吐司", Toast.LENGTH_LONG).show();
        et_number = (EditText) findViewById(R.id.et_number);
    }

    public void click(View view) {
        String number = et_number.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);

        sharedPreferences.edit().putString("ipnumber", number).commit();
        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
    }
}
