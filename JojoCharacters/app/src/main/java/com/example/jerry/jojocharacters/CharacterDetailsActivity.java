package com.example.jerry.jojocharacters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CharacterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        String name = (String) getIntent().getExtras().get("characterName");
    }
}
