package com.example.jerry.resume;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button workHistoryButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enter the new layout
        workHistoryButton = (Button) findViewById(R.id.workHistoryButton);
        workHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is for your app to move from one layout to the next
                Intent goToTheOtherActivity = new Intent(getApplicationContext(), WorkHistoryActivity.class);
                startActivity(goToTheOtherActivity);
            }
        });
    }
}

