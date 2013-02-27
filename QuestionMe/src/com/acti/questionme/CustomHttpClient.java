package com.acti.questionme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class CustomHttpClient {
	
	public static String HTTP_CONTENTTYPE_URLENCODED			= "application/x-www-form-urlencoded";
	public static String HTTP_CONTENTTYPE_JSON					= "application/json";
	public static String HTTP_CONTENTTYPE_TEXTPLAIN				= "text/plain";
	
	public static String executeHttpPost(String url, String postParameters , String httpcontenttype) throws IOException 
	{
	
		String result= "";
		InputStream inputStream =null;
		StringBuilder response = new StringBuilder();
		try
		{
			URL postC2dmRegistrationUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) postC2dmRegistrationUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			if(CustomHttpClient.HTTP_CONTENTTYPE_JSON.equalsIgnoreCase(httpcontenttype))
			{
				conn.setRequestProperty("Content-Type", "application/json");
			}
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			Log.i("Tag Here","In CustomHttpClient -"+url +":Params"+postParameters);
			wr.write(postParameters);
			wr.flush();
			inputStream = conn.getInputStream();
			wr.close();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while ((result = bufferedReader.readLine()) != null) 
			{
				response.append(result);
			}
			result=response.toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
				
		return result;
	}

}
