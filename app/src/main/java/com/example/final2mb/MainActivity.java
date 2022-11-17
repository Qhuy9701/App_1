package com.example.final2mb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

RecyclerView recyclerView ;
FloatingActionButton add_button;

MyDatabaseHelper myDB;
//create list
ArrayList<String> trip_id, trip_name, trip_destination, trip_date,trip_risk,trip_description;
CustomAdapter customAdapter;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	recyclerView = findViewById(R.id.recyclerView);
	//tim id cua nut
	add_button = findViewById(R.id.add_button);
	//khai bao database
	
	
	//lang nghe
	add_button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
		//tao them 1 activity Add
			Intent intent  = new Intent(MainActivity.this, AddTripActivity.class);
			//bat dau add activity
			startActivity(intent);
		}
	});
	
	myDB = new MyDatabaseHelper(MainActivity.this);
	trip_id = new ArrayList<>();
	trip_name = new ArrayList<>();
	trip_destination = new ArrayList<>();
	trip_date = new ArrayList<>();
	trip_risk = new ArrayList<>();
	trip_description = new ArrayList<>();
	displayDataTrip();
	
	customAdapter = new CustomAdapter(MainActivity.this,trip_id,trip_name,trip_destination,trip_date,trip_risk,trip_description);
	recyclerView.setAdapter(customAdapter);
	recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
	
}



void displayDataTrip(){
	Cursor cursor = myDB.readAllDataTrip();
	if(cursor.getCount()==0)
	{
		Toast.makeText(this, "No_Data", Toast.LENGTH_SHORT).show();
	}
	else
	{
		while (cursor.moveToNext()){
			trip_id.add(cursor.getString(0));
			trip_name.add(cursor.getString(1));
			trip_destination.add(cursor.getString(2));
			trip_date.add(cursor.getString(3));
			trip_risk.add(cursor.getString(4));
			trip_description.add(cursor.getString(5));
		}
	}
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.my_menu,menu);
	return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
	if(item.getItemId()==R.id.delete_all)
	{
		Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show();
		MyDatabaseHelper myDB = new MyDatabaseHelper(this);
		myDB.deleteAll();
		Intent intent  = new Intent(MainActivity.this, MainActivity.class);
		//bat dau add activity
		startActivity(intent);
	}
	return super.onOptionsItemSelected(item);
}


}

