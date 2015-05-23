package com.example.movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private ListView _listMovie;
	private Movie _movie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		final ListView listview = (ListView) findViewById(R.id.listMovie);
		String searchResult = getIntent().getStringExtra("postSearch");
		_movie = new Movie(searchResult);
		System.out.println("Movie : "+ _movie.getPlot()+" - "+_movie.getPoster()+ " - "+_movie.getTitle());
		
		final LinkedList<Movie> list = new LinkedList<Movie>();
		list.add(_movie);

		final MovieAdapter adapter = new MovieAdapter(this, R.layout.activity_result, list);
		listview.setAdapter(adapter);
	}
}

class MovieAdapter extends ArrayAdapter<Movie> {
	private final Context _context;
	private LinkedList<Movie> _movies;

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
		TextView viewContent = (TextView) rowView.findViewById(R.id.synopsis);
		
		viewTitle.setText(_movies.get(position).getTitle());
		viewContent.setText(_movies.get(position).getPlot());
		
		try {
		  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(_movies.get(position).getPoster()).getContent());
		  imageView.setImageBitmap(bitmap); 
		} catch (MalformedURLException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}

		return rowView;
	}
}
