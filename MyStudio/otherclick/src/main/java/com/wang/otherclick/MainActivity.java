package com.wang.otherclick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
        Button bt_callphone = (Button) findViewById(R.id.bt_callphone);
        bt_callphone.setOnClickListener(new MyOnclickListener());
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                callphone();
                break;
            case R.id.button2:
                System.out.println("22222222222222222222222");
                break;
            case R.id.button3:
                System.out.println("3333333333333333333333");
                break;
            case R.id.button4:
                Toast toast = Toast.makeText(this, "4444444444444444444444444444444444444444", Toast.LENGTH_SHORT);
                toast.show();
                break;
            default:
                break;
        }
    }

    class MyOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            callphone();
        }
    }

    private void callphone() {
        String number = et_number.getText().toString().trim();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
