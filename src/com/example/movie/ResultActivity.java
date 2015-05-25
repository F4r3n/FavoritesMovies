
package com.example.movie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private MovieResearch _movies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		final ListView listview = (ListView) findViewById(R.id.listMovie);
		String searchResult = getIntent().getStringExtra("postSearch");
		_movies = new MovieResearch(searchResult);
		
		LinkedList<Movie> list = new LinkedList<Movie>();
		list = _movies.getArray();
		

		final MovieAdapter adapter = new MovieAdapter(this, R.layout.activity_result, list);
		listview.setAdapter(adapter);
	}
}

class MovieAdapter extends ArrayAdapter<Movie> {
	private final Context _context;
	private LinkedList<Movie> _movies;
	private int _currentPosition = 0;

	public MovieAdapter(Context context, int resource, LinkedList<Movie> movies) {
		super(context, resource,movies);
		_context = context;
		_movies = movies;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item_list, parent, false);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.poster);
		TextView viewTitle = (TextView) rowView.findViewById(R.id.title);
		TextView viewRating = (TextView) rowView.findViewById(R.id.rating);
		TextView viewContent = (TextView) rowView.findViewById(R.id.synopsis);
		
		viewTitle.setText(_movies.get(position).getTitle()+" ("+_movies.get(position).getYear()+") ");
		viewRating.setText(_movies.get(position).getRating());
		viewContent.setText(_movies.get(position).getPlot());
		
		try {
			String url = _movies.get(position).getPoster();
			Bitmap bitmap = BitmapFactory.decodeStream(new URL(url).openStream()); 
			imageView.setImageBitmap(bitmap); 
		} catch (MalformedURLException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
		
		Button trailer = (Button) rowView.findViewById(R.id.trailer);
		trailer.setOnClickListener(trailerListener);
		
		Button share = (Button) rowView.findViewById(R.id.share);
		share.setOnClickListener(shareListener);
		
		Button streaming = (Button) rowView.findViewById(R.id.streaming);
		streaming.setOnClickListener(streamingListener);
		
		_currentPosition = position;
		return rowView;
	}
	
	OnClickListener trailerListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 Intent intent = new Intent(Intent.ACTION_SEARCH);
			 intent.setPackage("com.google.android.youtube");
			 intent.putExtra("query", _movies.get(_currentPosition).getTitle()+" "+_movies.get(_currentPosition).getYear()+" trailer");
			 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 _context.startActivity(intent);
		}
	};
	
	OnClickListener streamingListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
		}
	};
	
	OnClickListener shareListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		     sendIntent.putExtra("sms_body", "FavoritesMovies\n\tTu devrais regarder ce film : "+_movies.get(_currentPosition).getTitle()+", il déchire !"); 
		     sendIntent.setType("vnd.android-dir/mms-sms");
		     _context.startActivity(sendIntent);
		}
	};
	
	
}
