package com.itheima.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载了个布局
		setContentView(R.layout.activity_main);
	}

	//开启第一个页面 
	public void click1(View v){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		
	}
	//开启第二个页面 
	public void click2(View v){
		Intent intent = new Intent(this,SecondActivity.class);
		startActivity(intent);
	}
	
	

}
