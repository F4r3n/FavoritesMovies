package com.example.movie;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieResearch {
	private String _nameSearch;
	private LinkedList<Movie> movies  = new LinkedList<Movie>(); 
	
	public MovieResearch(String nameSearch) {
		_nameSearch = nameSearch;
		search();
	}
	
	public LinkedList<Movie> getArray() {
		return movies;
	}
	
	private void search() {
		String search = "http://www.imdb.com/xml/find?json=1&nr=1&tt=on&q="+_nameSearch;
		String resultQuery = ObjectRequest.sendRequest(search);
		JSONArray popularTitles = null;
		try {
			JSONObject o = new JSONObject(resultQuery);
			popularTitles = o.getJSONArray("title_popular");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		for(int i=0; i< popularTitles.length();i++) {
			try {
				JSONObject jobject = popularTitles.getJSONObject(i);
				String id = jobject.getString("id");
				movies.add(new Movie(id));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
