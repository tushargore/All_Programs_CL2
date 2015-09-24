

package com.example.bookrecommender;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private Spinner searchView;
	private ArrayAdapter<String> searchAdapter, listAdapter;
	private ListView listView;
	private ArrayList<String> dataList;
	private DatabaseHelper databaseHelper;
	private BookListAdapter bookListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// initialize UI components
		searchView = (Spinner) findViewById(R.id.searchView);
		listView = (ListView) findViewById(R.id.list);
		searchAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.searchCategory));
		
		searchView.setAdapter(searchAdapter);


		databaseHelper = new DatabaseHelper(getApplicationContext());
		ArrayList<BookBean> list = databaseHelper.getBookList(searchView.getSelectedItem().toString());
		bookListAdapter = new BookListAdapter(getApplicationContext(), list);
		listView.setAdapter(bookListAdapter);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_add_book) {
			Intent intent=new Intent(this,AddNewBookActivity.class);
			startActivity(intent);
			finish();
		}else if(id == R.id.action_rate_book){
			Intent intent=new Intent(this,BookRatingActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Find book as per search text
	 * 
	 * @param bookType
	 */
	private void findBookList(String bookType) {
		String[] booklist = getBooklistfromResources(bookType);
		listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, booklist);
		listView.setAdapter(listAdapter);
	}

	/**
	 * get list of books from resources
	 * 
	 * @param bookType
	 * @return
	 */
	private String[] getBooklistfromResources(String bookType) {
		String[] booklist = null;

		if (bookType.equals(getString(R.string.novel))) {
			booklist = getResources().getStringArray(R.array.novel);
		} else if (bookType.equals(getString(R.string.fantasy))) {
			booklist = getResources().getStringArray(R.array.fantasy);
		} else if (bookType.equals(getString(R.string.literature))) {
			booklist = getResources().getStringArray(R.array.literature);
		} else if (bookType.equals(getString(R.string.scienceFiction))) {
			booklist = getResources().getStringArray(R.array.scienceFiction);
		} else if (bookType.equals(getString(R.string.java))) {
			booklist = getResources().getStringArray(R.array.java);
		}
		return booklist;

	}

}
