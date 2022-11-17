package com.example.final2mb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

EditText name_input, destination_input, date_input, risk_input, description_input;
Button update_button,delete_button;
String id , name, destination , date, risk , description ;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_update);
	
	name_input = findViewById(R.id.name_input2);
	destination_input = findViewById(R.id.destination_input2);
	date_input = findViewById(R.id.date_input2);
	risk_input = findViewById(R.id.risk_input2);
	description_input = findViewById(R.id.description_input2);
	update_button = findViewById(R.id.update_button);
	update_button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(UpdateActivity.this, AddCost.class);
			startActivity(intent);
		}
	});
	delete_button = findViewById(R.id.delete_button);
	delete_button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
			//dialog show
			myDB.deleteTrip(id);
			Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
			startActivity(intent);
		}
	});
	getData();
}

void getData() {
	if (getIntent().hasExtra("id") &&
			    getIntent().hasExtra("name") &&
			    getIntent().hasExtra("destination") &&
			    getIntent().hasExtra("date") &&
			    getIntent().hasExtra("risk") &&
			    getIntent().hasExtra("description")) {
		//Getting Data from Intent
		id = getIntent().getStringExtra("id");
		name = getIntent().getStringExtra("name");
		destination = getIntent().getStringExtra("destination");
		date = getIntent().getStringExtra("date");
		risk = getIntent().getStringExtra("risk");
		description = getIntent().getStringExtra("description");
		//Setting Intent Data
		name_input.setText(name);
		destination_input.setText(destination);
		date_input.setText(date);
		risk_input.setText(risk);
		description_input.setText(description);
		Log.d("", name+" "+destination+" "+date+" "+risk+"" + description);
	} else {
		Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
	}
}
}
	