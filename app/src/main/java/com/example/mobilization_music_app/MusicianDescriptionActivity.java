package com.example.mobilization_music_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MusicianDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_description);

        // Activity label is the string-value of the button that user pressed
        String musician_name = getIntent().getStringExtra("Musician");
        setTitle(musician_name);
    }
}