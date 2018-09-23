package com.example.jerry.notetaker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;


// Custom Adapter class for the Emotion Object

public class EmotionAdapter extends ArrayAdapter<Emotion> {
    private static final String TAG = "EmotionAdapter";
    private Context mContext;
    private int mResource;

    public EmotionAdapter(Context context, int resource, ArrayList<Emotion> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    // Gets the view and attaches it to the list
    // Modifies the textviews of the list item to match adapter_view_layout.xml
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get emotion, and date
        String emotionName = getItem(position).getEmotionName();
        Date date = getItem(position).getDate();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // Get the textview items from the layout, be sure to convert as the view is different
        TextView textViewName = convertView.findViewById(R.id.emotionTextView);
        TextView textViewdate = convertView.findViewById(R.id.dateTextView);

        // Set the textview to the date and time of the instance of the emotion
        textViewName.setText(emotionName);
        textViewdate.setText(date.toString());

        return convertView;
    }
}
