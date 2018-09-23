package com.example.jerry.notetaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    int noteId;
    private Spinner emotionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        emotionSpinner = findViewById(R.id.emotionSpinner);
        List<Emotion> emotionList = new ArrayList<>();
        Joy joy = new Joy();
        Fear fear = new Fear();
        emotionList.add(joy);
        emotionList.add(fear);

        ArrayAdapter<Emotion> adapter = new ArrayAdapter<Emotion>(this,
                android.R.layout.simple_spinner_item, emotionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        emotionSpinner.setAdapter(adapter);
        emotionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) parent.getSelectedItem();
                displayEmotion(emotion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        }
        else{
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteId, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.jerry.notetaker", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getSelectedEmotion(View v){
        Emotion emotion = (Emotion) emotionSpinner.getSelectedItem();
        displayEmotion(emotion);
    }

    private void displayEmotion(Emotion emotion){
        String name = emotion.getEmotionName();
        emotion.setDate(new Date(System.currentTimeMillis()));
        Date date = emotion.getDate();
        String nameAndDate = "Feeling some " + name + " on: " + date.toString();

        Toast.makeText(this, nameAndDate, Toast.LENGTH_LONG).show();
    }
}
