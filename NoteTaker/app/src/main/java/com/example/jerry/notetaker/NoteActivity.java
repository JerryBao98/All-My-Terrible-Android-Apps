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

    int emotionId;
    private Spinner emotionSpinner;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Get the Intent, as well as the comment field: edit text
        final EditText editText = findViewById(R.id.editText);
        Intent intent = getIntent();

        // Get the id of the object you are using
        emotionId = intent.getIntExtra("emotionId", -1);
        emotionSpinner = findViewById(R.id.emotionSpinner);

        // Get the save and cancel buttons
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Populate the spinner as well as the comment section
        if (emotionId != -1){
            Emotion emotion = MainActivity.emotionsArrayList.get(emotionId);
            List<Emotion> singleList = new ArrayList<>();
            singleList.add(emotion);

            ArrayAdapter<Emotion> adapter = populateSpinner(singleList);
            emotionSpinner.setAdapter(adapter);
            editText.setText(MainActivity.emotionsArrayList.get(emotionId).getComment());
        }
        // If we are adding in a new emotion
        else{
            ArrayAdapter<Emotion> adapter = populateSpinner(populateAllEmotions());
            emotionSpinner.setAdapter(adapter);
            Emotion emotion = getEmotionFromSpinner();
            MainActivity.emotionsArrayList.add(emotion);
            //Joy placeHolder = new Joy();
            //placeHolder.setDate(new Date(System.currentTimeMillis()));
            //MainActivity.emotionsArrayList.add(placeHolder);
            //emotionId = MainActivity.emotionsArrayList.size() - 1;
        }

        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Emotion emotion = MainActivity.emotionsArrayList.get(emotionId);
                emotion.setComment(String.valueOf(s));

                //MainActivity.notes.set(emotionId, String.valueOf(s));
                //MainActivity.arrayAdapter.notifyDataSetChanged();

                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.jerry.notetaker", Context.MODE_PRIVATE);
                //HashSet<String> set = new HashSet<>(MainActivity.notes);
                //sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion emotion = (Emotion) emotionSpinner.getSelectedItem();
                onSave(emotion, editText.getText().toString());
                openMainActivity();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

    }

    // On pressing the save button, set the comment and date of the emotion
    public void onSave(Emotion emotion, String comment){
        emotion.setComment(comment);
        emotion.setDate(new Date(System.currentTimeMillis()));
    }

    public Emotion getEmotionFromSpinner(){
        Emotion emotion = (Emotion) emotionSpinner.getSelectedItem();
        return emotion;
    }

    private void displayEmotion(Emotion emotion){
        String name = emotion.getEmotionName();
        emotion.setDate(new Date(System.currentTimeMillis()));
        Date date = emotion.getDate();
        String nameAndDate = "Feeling some " + name + " on: " + date.toString();
        Toast.makeText(this, nameAndDate, Toast.LENGTH_LONG).show();
    }

    // Adds all possible emotions to a list
    // returns that list as output
    // Called only when a NEW emotion instance is added
    private List<Emotion> populateAllEmotions(){
        List<Emotion> emotionList = new ArrayList<>();
        Joy joy = new Joy();
        Fear fear = new Fear();
        emotionList.add(joy);
        emotionList.add(fear);
        return emotionList;
    }

    // Populates the spinner
    // List of emotions as input and an array adapter of those emotions as the output
    private ArrayAdapter<Emotion> populateSpinner(List<Emotion> emotionList){
        ArrayAdapter<Emotion> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, emotionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
