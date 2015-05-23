package com.example.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

	// private Map<String, String > _dico = new HashMap<String, String>();
	private String _request;
	private JSONObject _parser;
	private String _name;

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
			if (result.equals("")) {
				result = ObjectRequest
						.sendRequest("http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="
								+ _name + "poster");
				JSONArray jArray = _parser.getJSONArray("results");
				JSONObject oneObject = jArray.getJSONObject(0);
				result = oneObject.getString("url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;

	}

}
