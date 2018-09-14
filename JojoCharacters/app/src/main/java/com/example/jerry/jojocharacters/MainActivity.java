package com.example.jerry.jojocharacters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String characters[] = new String[] {"D'Arby", "Steely Dan", "Kakyoin", "Polnareff", "Koichi"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        // Simple list item 1 is for text only, item 2 is text and picture
        ArrayAdapter<String> characterAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, characters);
    }
}
