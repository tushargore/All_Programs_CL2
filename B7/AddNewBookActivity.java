package com.example.bookrecommender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewBookActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private EditText editTextBookName, editTextAuther;
	private Spinner spinnerCategories;
	private Button buttonAddNew;
	private DatabaseHelper databaseHelper;
	private ArrayAdapter<String> categoriesAdapter;
	private String bookname;
	private String auther;
	private String categories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_book);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		editTextBookName = (EditText) findViewById(R.id.editTextBookName);
		editTextAuther = (EditText) findViewById(R.id.editTextAuther);
		spinnerCategories = (Spinner) findViewById(R.id.categories);
		buttonAddNew = (Button) findViewById(R.id.buttonAddNew);
		categoriesAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.book_types));
		spinnerCategories.setAdapter(categoriesAdapter);
		
		//set Listeners 
		buttonAddNew.setOnClickListener(this);
		spinnerCategories.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_new_book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (!editTextBookName.getText().toString().equals("")
				|| !editTextAuther.getText().toString().equals("")) {
			bookname = editTextBookName.getText().toString();
			auther = editTextAuther.getText().toString();

			databaseHelper.addNewBook(bookname, categories, auther);
			onBackPressed();

		} else {
			Toast.makeText(getApplicationContext(), "Enter All Fileds",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> listAdapter, View view,
			int position, long id) {
		categories = listAdapter.getAdapter().getItem(position).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
	
	@Override
	public void onBackPressed() {
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
