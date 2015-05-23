package com.example.movie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultActivity extends Activity {
	private ListView _listMovie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		final ListView listview = (ListView) findViewById(R.id.listMovie);

		
	    final LinkedList<Movie> list = new LinkedList<Movie>();
	    final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.activity_list_item, list);
	    listview.setAdapter(adapter);
	}
}

class StableArrayAdapter extends ArrayAdapter<Movie> { 
	private HashMap<Movie, Integer> mIdMap = new HashMap<Movie, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId, List<Movie> objects) {
    	super(context, textViewResourceId, objects);
    	for (int i = 0; i < objects.size(); ++i) {
    		mIdMap.put(objects.get(i), i);
    	}
    }

    @Override
    public long getItemId(int position) {
    	Movie item = getItem(position);
    	return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
    	return true;
    }
  }