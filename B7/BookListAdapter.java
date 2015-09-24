package com.example.bookrecommender;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class BookListAdapter extends BaseAdapter {
	private ArrayList<BookBean> list;
	private LayoutInflater inflater;
	private DatabaseHelper databaseHelper;
	
	BookListAdapter(Context context,ArrayList<BookBean> list){
		this.list=list;
		inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		databaseHelper = new DatabaseHelper(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public BookBean getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.list_item_book_rlayout, null);
		
		TextView textViewBookName=(TextView)convertView.findViewById(R.id.textViewBookName);
		TextView textViewReleaseDate=(TextView)convertView.findViewById(R.id.textViewReleaseDate);
		TextView textViewRating=(TextView)convertView.findViewById(R.id.textViewRating);
		
		//Set Data
		
		final BookBean dataBean=list.get(position);
		textViewBookName.setText( dataBean.getBookname());
		textViewReleaseDate.setText("Release Date : " + dataBean.getReleaseDate());
		textViewRating.setText("Rating : "+dataBean.getRating());
		return convertView;
	}

}
