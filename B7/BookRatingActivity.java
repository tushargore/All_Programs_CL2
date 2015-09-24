package com.example.bookrecommender;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class BookRatingActivity extends Activity {

	private BookRatingAdapter bookAdapter;
	private ListView listView;
	private DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_rating);
		//Initialize views
		listView = (ListView) findViewById(R.id.list);
		
		//Initialize database 
		databaseHelper = new DatabaseHelper(getApplicationContext());
		
		//get List from dataBase  
		ArrayList<BookBean> list = databaseHelper
				.getBookList(getString(R.string.newrelease));
		
		//Initialize adapter
		bookAdapter = new BookRatingAdapter(getApplicationContext(), list);
		//set adapter
		listView.setAdapter(bookAdapter);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
