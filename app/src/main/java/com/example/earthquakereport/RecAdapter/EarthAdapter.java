package com.example.earthquakereport.RecAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakereport.Activity.UrlActivity;
import com.example.earthquakereport.ConnectAll.Model;
import com.example.earthquakereport.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthAdapter extends RecyclerView.Adapter<EarthViewLoader> {

    private ArrayList<Model> arrayList;
    private String conName1,conName2;
    private static final String LOCATION_SEPARATOR = " of ";
    private Context context;

    public EarthAdapter(ArrayList<Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EarthViewLoader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);


        return new EarthViewLoader(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthViewLoader holder, int position) {

        final Model tmp = arrayList.get(position);
        //for setting magnitude in string
        String formatMag = formatMagnitude(arrayList.get(position).getMag());
        holder.textView1.setText(formatMag);


        GradientDrawable gradientDrawable = (GradientDrawable) holder.textView1.getBackground();
        int mag = getMagnitudeColor(arrayList.get(position).getMag());
        gradientDrawable.setColor(mag);

        //for splitting place in two textview
        String string = arrayList.get(position).getPlace();
        if(string.contains(LOCATION_SEPARATOR)){
            String[] parts = string.split(LOCATION_SEPARATOR);
            conName1 = parts[0] + LOCATION_SEPARATOR;
            conName2 = parts[1];
        }

        holder.textView2.setText(conName1);
        holder.textView3.setText(conName2);

        //convert the string into dateof object
        Date dateObj = new Date(arrayList.get(position).getTime());
        String formateDate = formatDate(dateObj);
        holder.textView4.setText(formateDate);
        String formateTime = formattedTime(dateObj);
        holder.textView5.setText(formateTime);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UrlActivity.class);
                intent.putExtra("url",tmp.getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    int getMagnitudeColor(Double mag) {
        int color = (int)Math.floor(mag);
        int magnitudeColor = 0;
        switch (color){
            case 0:
                magnitudeColor = R.color.magnitude0;
                break;
            case 1:
                magnitudeColor = R.color.magnitude1;
                break;
            case 2:
                magnitudeColor = R.color.magnitude2;
                break;
            case 3:
                magnitudeColor = R.color.magnitude3;
                break;
            case 4:
                magnitudeColor = R.color.magnitude4;
                break;
            case 5:
                magnitudeColor = R.color.magnitude5;
                break;
            case 6:
                magnitudeColor = R.color.magnitude6;
                break;
            case 7:
                magnitudeColor = R.color.magnitude7;
                break;
            case 8:
                magnitudeColor = R.color.magnitude8;
                break;
            case 9:
                magnitudeColor = R.color.magnitude9;
                break;
            default:
                magnitudeColor = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(context,magnitudeColor);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private String formattedTime(Date dateObj) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObj);
    }

    private String formatDate(Date dateObj) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObj);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
