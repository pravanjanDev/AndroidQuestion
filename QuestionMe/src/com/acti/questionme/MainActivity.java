package com.acti.questionme;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

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
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenOnSubmit();
		
	}
	

	private void addListenOnSubmit() {
		
		context		=this;
		button		=(Button)findViewById(R.id.submit);
		email		=(EditText)findViewById(R.id.email);
		firstname	=(EditText)findViewById(R.id.firstname);
		lastname	=(EditText)findViewById(R.id.lastname);
			
			
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(!"".equals(email.getText().toString()) && 
							!"".equals(firstname.getText().toString()) &&!"".equals(firstname.getText().toString())){
						new RegisterTask().execute();
						
					}else{
						Toast.makeText(MainActivity.this,"All fields are mandatory!", Toast.LENGTH_LONG).show();
					}
					
					
				}
			});
			
	}
	
	private class RegisterTask extends AsyncTask<Void, Void, Boolean>{
		
		ProgressDialog lPd = new ProgressDialog(MainActivity.this);

		@Override
		protected Boolean doInBackground(Void... params) {
			boolean Status=false;
			try {
				if(!"".equals(email.getText().toString().trim()))
				{
					
					Log.i(getClass().getName(),"Email IN Async-"+email.getText());
					String lUrl = "http://staging-adaptive-questionme.appspot.com/QuestionController/RegisterNewUser";
					String lParams = "email="+email.getText().toString().trim()+"&firstName="+firstname.getText().toString().trim()+"&lastName="+lastname.getText().toString().trim();
					String lHttpResponse = CustomHttpClient.executeHttpPost(lUrl, lParams, CustomHttpClient.HTTP_CONTENTTYPE_URLENCODED);
					Log.i(getClass().getName(), "lParams - "+lParams );
					Log.i(getClass().getName(), "lHttpResponse - "+lHttpResponse );
					
					try {
						JSONObject json=new JSONObject(lHttpResponse);
						Status=Boolean.parseBoolean((String) json.get("success"));
						Log.i(getClass().getName(), "Status -"+Status);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Status;
			
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
				Intent intent = new Intent(context, UserPageActivity.class);
//		    	EditText editText = (EditText) findViewById(R.id.edit_message);
//		    	String message = editText.getText().toString();
//		    	intent.putExtra(EXTRA_MESSAGE, message);
		    	startActivity(intent);
				Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(MainActivity.this, "Unable to login", Toast.LENGTH_LONG).show();
			}
		}
		
		
	}

	

}
