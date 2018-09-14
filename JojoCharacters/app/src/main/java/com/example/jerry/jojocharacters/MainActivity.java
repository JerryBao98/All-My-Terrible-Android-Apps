package com.example.jerry.jojocharacters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String characters[] = new String[] {
            "Daniel D'Arby", "Steely Dan", "Kakyoin Noriyaki", "Jeanne-Pierre Polnareff",
            "Koichi Hirose", "Yoshikage Kira", "Terrence D'Arby", "Rudol von Stroheim"
    };

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        // Simple list item 1 is for text only, item 2 is text and picture
        ArrayAdapter<String> characterAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, characters);
        listView.setAdapter(characterAdapter);

        // Keep in mind this refers to whatever class you're in in.
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Tapped");
        Intent moveToDetailIntent = new Intent(getBaseContext(), CharacterDetailsActivity.class);
        moveToDetailIntent.putExtra("characterName", characters[position]);
        startActivity(moveToDetailIntent);
    }
}
