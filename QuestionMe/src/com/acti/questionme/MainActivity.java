package com.acti.questionme;

import java.io.IOException;

import com.acti.questionme.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {
	public final static String EMAIL 		= "com.ask.survey.EMAIL";
	public final static String FIRSTNAME 	= "com.ask.survey.FIRSTNAME";
	public final static String LASTNAME 	= "com.ask.survey.LASTNAME";

	private Button button;
	private EditText email,firstname,lastname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenOnSubmit();
		
	}
	

	private void addListenOnSubmit() {
		
		button		=(Button)findViewById(R.id.submit);
		email		=(EditText)findViewById(R.id.email);
		firstname	=(EditText)findViewById(R.id.firstname);
		lastname	=(EditText)findViewById(R.id.lastname);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				Toast.makeText(MainActivity.this, "Hi "+firstname.getText()+" "+lastname.getText()+"\n"+email.getText(), Toast.LENGTH_LONG).show();
				
				
//				Intent intent=new Intent(context, UserPageActivity.class);
//				intent.putExtra(EMAIL     ,  email.getText().toString());
//				intent.putExtra(FIRSTNAME ,  firstname.getText().toString());
//				intent.putExtra(LASTNAME  ,	 lastname.getText().toString());
//				startActivity(intent);
				new RegisterTask().execute();
			}
		});
		
		
		
		
		
	}
	
	private class RegisterTask extends AsyncTask<Void, Void, Boolean>{
		
		ProgressDialog lPd = new ProgressDialog(MainActivity.this);

		@Override
		protected Boolean doInBackground(Void... params) {
			
			
			try {
				if(!"".equals(email.getText().toString().trim()))
				{
					String lUrl = "http://localhost:8888/RegisterNewUser";
					String lParams = "email="+email+"&firstname="+firstname+"&lastname="+lastname;
					String lHttpResponse = CustomHttpClient.executeHttpPost(lUrl, lParams, CustomHttpClient.HTTP_CONTENTTYPE_JSON);
					Log.i(getClass().getName(), "lHttpResponse - "+lHttpResponse );
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		
		@Override
		protected void onPreExecute()
		{
			lPd.setMessage("Logging in...");
			lPd.show();
		}
		
		
		@Override
		protected void onPostExecute(Boolean result)
		{
			lPd.dismiss();
			if(result)
			{
				Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_LONG);
			}
			else
			{
				Toast.makeText(MainActivity.this, "Unable to login", Toast.LENGTH_LONG);
			}
		}
		
		
	}

	

}
