package com.example.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private String movieName;
	private EditText text;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
		text = (EditText) findViewById(R.id.movieName);
		searchButton = (Button) findViewById(R.id.Search);
		searchButton.setOnClickListener(handler);
	}

	View.OnClickListener handler = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			prepareRequest();
			if(movieName.equals("")) {
				return;	
			}
			Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
			
			String url = "http://www.omdbapi.com/?t="+movieName+"&y=&plot=short&r=json";
			myIntent.putExtra("postSearch", ObjectRequest.sendRequest(url)); 
			
			MainActivity.this.startActivity(myIntent);
		}
	};
	
	private void prepareRequest() {
		movieName = text.getText().toString().replace(" ", "+").replace("\n", "").replace("\r", "");
		
	}	
	
}
