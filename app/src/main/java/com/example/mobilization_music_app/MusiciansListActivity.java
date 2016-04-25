package com.example.mobilization_music_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MusiciansListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicians_list);

        ListView listView = (ListView) findViewById(R.id.musiciansList);

        // JSON part
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        // Copypasted from Internet
        // Receiving data
        @Override
        protected String doInBackground {
            try {
                URL url = new URL("http://download.cdn.yandex.net/mobilization-2016/artists.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        ArrayList<String> musicians = new ArrayList<String>;

        // String fake_json_string = getResources().getString(R.string.fake_json);
        String fake_json_string = resultJson;
        JSONArray jsonArray = new JSONArray(fake_json_string);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject musicianJson = jsonArray.getJSONObject(i);
            String name = musicianJson.optString("name").toString();
            musicians.add(name);
        }



        // Temp array of musicians names
        final String[] musicians_names = new String[]{
                "Prince", "Marilyn Manson", "Нюша",
                "Дима Билан", "Кристина Орбакайте",
                "Korn", "Katy Perry", "Usher"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, musicians);

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
