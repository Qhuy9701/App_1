package com.example.final2mb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

	private Context context;
	private ArrayList trip_id, trip_name, trip_destination, trip_date,trip_risk,trip_description;
CustomAdapter(Context context, ArrayList trip_id, ArrayList trip_name, ArrayList trip_destination,
              ArrayList trip_date ,ArrayList trip_risk ,ArrayList trip_description){
	this.context = context;
	this.trip_id = trip_id;
	this.trip_name = trip_name;
	this.trip_destination = trip_destination;
	this.trip_date = trip_date;
	this.trip_risk = trip_risk;
	this.trip_description = trip_description;
}

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
	LayoutInflater inflater = LayoutInflater.from(context);
	View view = inflater.inflate(R.layout.my_row,parent,false);
	return new MyViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

	holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
	holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
	holder.trip_destination_txt.setText(String.valueOf(trip_destination.get(position)));
	holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
	holder.trip_risk_txt.setText(String.valueOf(trip_risk.get(position)));
	holder.trip_description_txt.setText(String.valueOf(trip_description.get(position)));
	//Recyclerview onClickListener
	holder.mainLayout.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
		Intent intent  = new Intent(context,UpdateActivity.class);
		intent.putExtra("id",String.valueOf(trip_id.get(position)));
		intent.putExtra("name",String.valueOf(trip_name.get(position)));
		intent.putExtra("destination",String.valueOf(trip_destination.get(position)));
		intent.putExtra("date",String.valueOf(trip_date.get(position)));
		intent.putExtra("risk",String.valueOf(trip_risk.get(position)));
		intent.putExtra("description",String.valueOf(trip_description.get(position)));
		context.startActivity(intent);
		}
	});
}

@Override
public int getItemCount() {
	return trip_id.size();
}

public class MyViewHolder extends RecyclerView.ViewHolder {
	TextView trip_id_txt, trip_name_txt, trip_destination_txt, trip_date_txt, trip_risk_txt, trip_description_txt;
	LinearLayout mainLayout;
	public MyViewHolder(@NonNull View itemView) {
		super(itemView);
		trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
		trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
		trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
		trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
		trip_risk_txt = itemView.findViewById(R.id.trip_risk_txt);
		trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
		mainLayout = itemView.findViewById(R.id.mainLayout);
	}
}
}
