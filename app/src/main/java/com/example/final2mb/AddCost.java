package com.example.final2mb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCost extends AppCompatActivity {
EditText id_input2;
String id;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_add_cost);
	id_input2 = findViewById(R.id.id_input2);
}
void getData() {
	if (getIntent().hasExtra("id"))
	{
		id = getIntent().getStringExtra("id");
	}
	else
	{
		Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
	}
}
}