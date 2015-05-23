package com.example.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
	// private Map<String, String > _dico = new HashMap<String, String>();
	private String _request;
	private JSONObject _parser;
	private String _name;

	public Movie(){ }
	
	public Movie(String request) {
		_request = request;
		try {
			_parser = new JSONObject(_request);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		getName();
	}

	private void getName() {
		try {
			_name = _parser.getString("Title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return _name;
	}

	public String getPlot() {
		String result = "";
		try {
			result = _parser.getString("Plot");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getPoster() {
		String result = "";
		try {
			result = _parser.getString("Poster");
			System.out.println("result " + result + " " + result.length());
			if (result.equals("N/A")) {
				String request = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" +
								_name.replace(" ", "+").replace("\n", "") + "+poster";
				System.out.println(request);
				result = ObjectRequest.sendRequest(request);
				JSONObject jobjectResponse = new JSONObject(result);
				JSONObject jobject = jobjectResponse.getJSONObject("responseData");
				JSONArray jArray = jobject.getJSONArray("results");
				JSONObject oneObject = jArray.getJSONObject(0);
				result = oneObject.getString("url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;

	}

}
