package com.example.jerry.resume;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button workHistoryButton;
        Button callButton;
        Button emailButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enter the new layout
        workHistoryButton = (Button) findViewById(R.id.workHistoryButton);
        callButton = (Button) findViewById(R.id.callButton);
        emailButton = (Button) findViewById(R.id.emailButton);

        workHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is for your app to move from one layout to the next
                Intent goToTheOtherActivity = new Intent(getApplicationContext(), WorkHistoryActivity.class);
                startActivity(goToTheOtherActivity);
            }
        });

        // Call button listener
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myPhoneNumber = Uri.parse("tel:780-555-5555");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, myPhoneNumber);
                startActivity(callIntent);
            }
        });

        // How to send an email
        // plain text means sending only text
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Ayylmao@hotmail.com", "lmao@outlook.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello, this is the subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "This is the body of the text");
                startActivity(emailIntent);
            }
        });
    }
}

