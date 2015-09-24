package com.example.bookrecommender;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class BookRatingAdapter extends BaseAdapter {
	private ArrayList<BookBean> list;
	private LayoutInflater inflater;
	private DatabaseHelper databaseHelper;
	
	BookRatingAdapter(Context context,ArrayList<BookBean> list){
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
		
		/*create views(List item)*/
		convertView=inflater.inflate(R.layout.list_item_book_rating_layout, null);
		
		TextView textView=(TextView)convertView.findViewById(R.id.textView);
		RatingBar ratingBar=(RatingBar)convertView.findViewById(R.id.ratingBar);
		LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
		
		//set Data
		final BookBean dataBean=list.get(position);
		ratingBar.setRating(dataBean.getRating());
		textView.setText(dataBean.getBookname());
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				databaseHelper.rateBook(dataBean.getId(), rating);
			}
		});
		return convertView;
	}

}
