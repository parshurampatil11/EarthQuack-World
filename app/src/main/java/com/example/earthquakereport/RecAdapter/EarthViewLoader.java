package com.example.earthquakereport.RecAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakereport.R;

public class EarthViewLoader extends RecyclerView.ViewHolder {

    TextView textView1,textView2,textView3,textView4,textView5;
    CardView cardView;
    public EarthViewLoader(@NonNull View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        textView1 = (TextView) itemView.findViewById(R.id.floatVal);
        textView2 = (TextView) itemView.findViewById(R.id.stringName);
        textView3 = (TextView) itemView.findViewById(R.id.stringName1);
        textView4 = (TextView) itemView.findViewById(R.id.stringDate);
        textView5 = (TextView) itemView.findViewById(R.id.stringTime);
    }
}
