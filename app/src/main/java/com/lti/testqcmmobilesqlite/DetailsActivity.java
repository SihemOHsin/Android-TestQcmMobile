package com.lti.testqcmmobilesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    TextView textDate, textNamePrenom, textClass, textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int finalScore = intent.getIntExtra("FINAL_SCORE", 0);

        textDate = findViewById(R.id.textDate);
        textNamePrenom = findViewById(R.id.textNamePrenom);
        textClass = findViewById(R.id.textClass);
        textScore = findViewById(R.id.textScore);

        textScore.setText("Final Score: " + finalScore);

        Button quitterBtn = findViewById(R.id.btnQuitter);
        Button goToMainBtn = findViewById(R.id.btnGoToMain);
        Button goToListBtn = findViewById(R.id.btnGoToList);
        quitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        goToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        goToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });


        DbHandler dbHandler = new DbHandler(this);

        ArrayList<HashMap<String, String>> userList = dbHandler.getUsers();

        if (userList.size() > 0) {
            HashMap<String, String> user = userList.get(userList.size() - 1);

            textDate.setText("Date: " + user.get("date"));
            textNamePrenom.setText("Nom et Prenom: " + user.get("name") + " " + user.get("prenom"));
            textClass.setText("Class: " + user.get("classe"));

        }
    }
}
