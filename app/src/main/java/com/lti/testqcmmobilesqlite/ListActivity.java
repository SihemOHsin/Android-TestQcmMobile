package com.lti.testqcmmobilesqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.listView);

        // Get user  from the database
        DbHandler dbHandler = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = dbHandler.getUsers();

        ArrayList<String> userNames = new ArrayList<>();
        for (HashMap<String, String> user : userList) {
            String name = user.get("name") + " " + user.get("prenom");
            userNames.add(name);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNames);
        listView.setAdapter(arrayAdapter);
    }
}