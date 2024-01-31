package com.lti.testqcmmobilesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText date, name, prenom, classe;
    Button saveBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.txtDate);
        name = findViewById(R.id.txtName);
        prenom = findViewById(R.id.txtPrenom);
        classe = findViewById(R.id.txtClass);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateValue = date.getText().toString();
                String nameValue = name.getText().toString() + "\n";
                String prenomValue = prenom.getText().toString();
                String classeValue = classe.getText().toString();

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(dateValue, nameValue, prenomValue, classeValue);

                intent = new Intent(MainActivity.this, QcmActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
