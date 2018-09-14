package com.example.jerry.jojocharacters;

import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CharacterDetailsActivity extends AppCompatActivity {

    TextView nametextView;
    TextView standTextView;
    TextView duwangTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        nametextView = (TextView) findViewById(R.id.nameTextView);
        standTextView = (TextView) findViewById(R.id.standTextView);
        duwangTextView = (TextView) findViewById(R.id.duwangTextView);

        String name = (String) getIntent().getExtras().get("characterName");
        nametextView.setText(name);
    }
}
