package com.example.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
	// private Map<String, String > _dico = new HashMap<String, String>();
	private String _id;
	private JSONObject _parser;
	private String _name;
	private String _plot;
	private String _image;
	private String _year;
	private String _imdbRating;

	public Movie() {
	}

	public Movie(String id) {
		_id = id;
		search();
	}
	
	public String getYear() {
		return _year;
	}
	
	public String getRating() {
		return _imdbRating;
	}

	private void search() {
		// http://www.omdbapi.com/?i=tt0076759&plot=short&r=json
		String search = "http://www.omdbapi.com/?i=" + _id
				+ "&plot=short&r=json";

		String resultQuery = ObjectRequest.sendRequest(search);
		try {
			_parser = new JSONObject(resultQuery);
			_name = _parser.getString("Title");
			_plot = _parser.getString("Plot");
			_year = _parser.getString("Year");
			_imdbRating = _parser.getString("imdbRating");


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getImage();
	}

	public String getTitle() {
		return _name;
	}

	public String getPlot() {
		return _plot;
	}

	public String getPoster() {
		return _image;
	}

	private void getImage() {
		String result = "";
		try {
			result = _parser.getString("Poster");
			System.out.println("result " + result + " " + result.length());
			if (result.equals("N/A")) {
				String request = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="
						+ _name.replace(" ", "+").replace("\n", "") + "+poster";
				System.out.println(request);
				result = ObjectRequest.sendRequest(request);
				JSONObject jobjectResponse = new JSONObject(result);
				JSONObject jobject = jobjectResponse
						.getJSONObject("responseData");
				JSONArray jArray = jobject.getJSONArray("results");
				JSONObject oneObject = jArray.getJSONObject(0);
				result = oneObject.getString("url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		_image = result;
	}

}
