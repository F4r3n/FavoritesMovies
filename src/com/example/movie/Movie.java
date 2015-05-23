package com.example.movie;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
	
	//private Map<String, String > _dico = new HashMap<String, String>();
	private String _request;
	private JSONObject _parser;
	
	public Movie(String request) {
		_request = request;
		try {
			_parser = new JSONObject(_request);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getTitle() {
		String result ="";
		try {
			result = _parser.getString("Title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getPlot() {
		String result ="";
		try {
			result = _parser.getString("Plot");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	

}
