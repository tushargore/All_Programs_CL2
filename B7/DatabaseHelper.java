package com.example.bookrecommender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.R.id;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class being used to perform database operations
 * 
 * @author bharat
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = DatabaseHelper.class.getName();
	public static final String DATABASE_NAME = "BOOKSYSTEM";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_QUERY = "CREATE TABLE BOOKSYSTEM( ID INTEGER PRIMARY KEY AUTOINCREMENT, BOOKNAME TEXT NOT NULL, AUTHER TEXT NOT NULL, RATING REAL,RELEASEDATE TEXT,DESC TEXT,CATEGORY TEXT)";

	private static DatabaseHelper sDbInstance = null;
	private Context context;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public static DatabaseHelper getInstance(Context context) {
		if (sDbInstance == null) {
			sDbInstance = new DatabaseHelper(context);
		}
		return sDbInstance;
	}

	/**
	 * Method to execute SQL Query
	 * 
	 * @param s
	 *            SQL Query
	 * @return cursor of returned results
	 */
	public synchronized Cursor ExecuteRawSql(String s) {// Select Query
		try {
			SQLiteDatabase sqLiteDb = getReadableDatabase();
			Cursor cursor = sqLiteDb.rawQuery(s, null);
			return cursor;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to execute SQL Query
	 * 
	 * @param s
	 *            SQL Query
	 * @return true if query was successfully executed.
	 */
	public synchronized boolean ExecuteSql(String s) {// Update Query
		try {
			Log.d(TAG, "Actual Query--->>" + s);
			SQLiteDatabase sqLiteDb = getWritableDatabase();
			sqLiteDb.execSQL(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveData(String startTime, String stopTime, int stepCount) {
		String qry = "INSERT INTO PEDODATA (STARTTIME,STOPTIME,STEPS) VALUES('"
				+ startTime + "','" + stopTime + "','" + stepCount + "');";
		return ExecuteSql(qry);
	}

	public ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		String qry = "SELECT STARTTIME,STOPTIME,STEPS FROM PEDODATA ORDER BY ID DESC";
		Cursor cursor = ExecuteRawSql(qry);
		if (cursor != null && cursor.moveToFirst()) {
			do {
				String startTime = cursor.getString(0);
				String stopTime = cursor.getString(1);
				int stepCount = cursor.getInt(2);

				String time = "From : " + startTime + " \nTo : " + stopTime;
				String steps = " \nSteps : " + stepCount;
				list.add(time + steps);

			} while (cursor.moveToNext());
		}
		return list;
	}

	public boolean addNewBook(String name, String category, String auther) {
		String qry = "INSERT INTO BOOKSYSTEM (BOOKNAME,CATEGORY,AUTHER ,RELEASEDATE) VALUES('"
				+ name + "','" + category + "','" + auther + "','"+getDateTime()+"');";
		return ExecuteSql(qry);
	}

	public boolean rateBook(int bookId, float rating) {
		String qry = "UPDATE BOOKSYSTEM SET RATING=" + rating + " WHERE id="
				+ bookId + "";
		return ExecuteSql(qry);
	}

	public ArrayList<BookBean> getBookList(String category) {

		ArrayList<BookBean> list = new ArrayList<BookBean>();
		String qry;

		if (category.equals(context.getString(R.string.toprated))) {
			qry = "SELECT ID,BOOKNAME,AUTHER,RELEASEDATE,RATING,CATEGORY FROM BOOKSYSTEM ORDER BY RATING DESC";
		} else {
			qry = "SELECT ID,BOOKNAME,AUTHER,RELEASEDATE,RATING,CATEGORY FROM BOOKSYSTEM ORDER BY RELEASEDATE DESC";
		}

		Cursor cursor = ExecuteRawSql(qry);
		if (cursor != null && cursor.moveToFirst()) {
			do {
				BookBean bookName = new BookBean();
				bookName.setId(cursor.getInt(0));
				bookName.setBookname(cursor.getString(1));
				bookName.setAuther(cursor.getString(2));
				bookName.setReleaseDate(cursor.getString(3));
				bookName.setRating(cursor.getFloat(4));
				bookName.setCategories(cursor.getString(5));
				list.add(bookName);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public String getDateTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",
				Locale.US);
		String formattedDate = formatter.format(c.getTime());
		String s = formattedDate;
		return s;
	}

}
