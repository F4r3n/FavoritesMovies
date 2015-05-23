package com.example.movie;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
	
	private HashMap<String, String > _dico = new HashMap<String, String>();
	private String _request;
	private JSONObject parser;
	
	public Movie(String request) {
		_request = request;
		try {
			parser = new JSONObject(request);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	

}
