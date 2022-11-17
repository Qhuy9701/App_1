package com.example.final2mb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

private Context context;
private static final String DATABASE_NAME = "Trip_db";
private static final int DATABASE_VERSION = 1;

private static final String TABLE_NAME = "trips";
private static final String COLUMN_ID = "_id";
private static final String COLUMN_NAME = "trip_name";
private static final String COLUMN_DESTINATION = "trip_destination";
private static final String COLUMN_DATE = "trip_date";
private static final String COLUMN_RISK = "trip_risk";
private static final String COLUMN_DESCRIPTION = "trip_description";


private static final String TABLE_COST = "cost";
private static final String COLUMN_COSTID = "cost_id";
private static final String COLUMN_TYPE = "type";
private static final String COLUMN_EXPENSE = "expense";
private static final String COLUMN_TIME = "time";
private static final String COLUMN_COMMENT = "comment";


public MyDatabaseHelper(@Nullable Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	this.context = context;
}

@Override
public void onCreate(SQLiteDatabase db) {
	//create two table in database 
	String query = "CREATE TABLE " + TABLE_NAME +
			               " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			               COLUMN_NAME + " TEXT, " +
			               COLUMN_DESTINATION + " TEXT, " +
			               COLUMN_DATE + " TEXT, " +
			               COLUMN_RISK + " TEXT, " +
			               COLUMN_DESCRIPTION + " TEXT);";
	db.execSQL(query);
	
	String query2 = "CREATE TABLE " + TABLE_COST +
			                " (" + COLUMN_COSTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			                COLUMN_ID + "INTEGER,"+
			                COLUMN_TYPE + " TEXT, " +
			                COLUMN_EXPENSE + " TEXT, " +
			                COLUMN_TIME + " TEXT, " +
			                COLUMN_COMMENT + " TEXT);";
	db.execSQL(query2);
}


@Override
public void onUpgrade(SQLiteDatabase db, int i, int i1) {
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	onCreate(db);
	
	//
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_COST);
	onCreate(db);
}

public void addTrip(String name, String destination, String date, String risk, String description) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv = new ContentValues();
	
	cv.put(COLUMN_NAME, name);
	cv.put(COLUMN_DESTINATION, destination);
	cv.put(COLUMN_DATE, date);
	cv.put(COLUMN_RISK, risk);
	cv.put(COLUMN_DESCRIPTION, description);
	long result = db.insert(TABLE_NAME, null, cv);
	if (result == -1) {
		Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
	} else {
		Toast.makeText(context, "Successfully!", Toast.LENGTH_SHORT).show();
	}
}

Cursor readAllDataTrip() {
	String query = "SELECT * FROM " + TABLE_NAME;
	SQLiteDatabase db = this.getReadableDatabase();
	
	Cursor cursor = null;
	//check null
	if (db != null) {
		cursor = db.rawQuery(query, null);
	}
	//return cursor
	return cursor;
}

void deleteTrip(String row_id) {
	SQLiteDatabase db = this.getWritableDatabase();
	long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
	if (result == -1) {
		Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
	} else {
		Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
	}
}

void deleteAll() {
	SQLiteDatabase db = this.getWritableDatabase();
	db.execSQL("DELETE FROM " + TABLE_NAME);
}
}
