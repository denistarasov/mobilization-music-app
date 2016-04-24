package com.example.mobilization_music_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MusiciansListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicians_list);

        ListView listView = (ListView) findViewById(R.id.musiciansList);

        // Temp array of musicians names
        final String[] musicians_names = new String[]{
                "Prince", "Marilyn Manson", "Нюша",
                "Дима Билан", "Кристина Орбакайте",
                "Korn", "Katy Perry", "Usher"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, musicians_names);

        listView.setAdapter(adapter);

        final String LOG_TAG = "myLogs";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "selected button = " + position + ", id = " + id);
                Intent intent = new Intent(MusiciansListActivity.this, MusicianDescriptionActivity.class);
                // Send to MusicianDescriptionActivity the value of the button
                // so it will be used as an Activity label
                intent.putExtra("Musician", musicians_names[position]);
                startActivity(intent);
            }
        });
    }
}
