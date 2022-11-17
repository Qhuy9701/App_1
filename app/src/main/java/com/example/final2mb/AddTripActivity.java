package com.example.final2mb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTripActivity extends AppCompatActivity {

EditText name_input,destination_input,date_input,risk_input,description_input;
Button add_button;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_add);
	
	name_input = findViewById(R.id.name_input);
	destination_input = findViewById(R.id.destination_input);
	date_input = findViewById(R.id.date_input);
	risk_input = findViewById(R.id.risk_input);
	description_input = findViewById(R.id.description_input);
	
	add_button = findViewById(R.id.add_button);
	add_button.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			 {
				//check validate null show mess
				if (name_input.getText().toString().isEmpty() || destination_input.getText().toString().isEmpty() || date_input.getText().toString().isEmpty() || risk_input.getText().toString().isEmpty() || description_input.getText().toString().isEmpty()) {
					//toast make can't null
					Toast.makeText(AddTripActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
				}
				else
				{
					MyDatabaseHelper myDB = new MyDatabaseHelper(AddTripActivity.this);
			    	myDB.addTrip(name_input.getText().toString().trim(),
					destination_input.getText().toString().trim(),
					date_input.getText().toString().trim(),
					risk_input.getText().toString().trim(),
					description_input.getText().toString().trim());
					Intent intent = new Intent(AddTripActivity.this, MainActivity.class);
					startActivity(intent);
				}
			 }
	};
	});
}
}