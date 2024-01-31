package com.lti.testqcmmobilesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QcmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcm);

        CheckBox checkbox1_1 = findViewById(R.id.checkbox1_1);
        CheckBox checkbox1_2 = findViewById(R.id.checkbox1_2);
        CheckBox checkbox1_3 = findViewById(R.id.checkbox1_3);

        CheckBox checkbox2_1 = findViewById(R.id.checkbox2_1);
        CheckBox checkbox2_2 = findViewById(R.id.checkbox2_2);
        CheckBox checkbox2_3 = findViewById(R.id.checkbox2_3);

        Button btnCalculateScore = findViewById(R.id.btnCalculateScore);

        btnCalculateScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scoreQuestion1 = calculateScoreQuestion1(checkbox1_1.isChecked(), checkbox1_2.isChecked(), checkbox1_3.isChecked());
                int scoreQuestion2 = calculateScoreQuestion2(checkbox2_1.isChecked(), checkbox2_2.isChecked(), checkbox2_3.isChecked());

                int finalScore = (scoreQuestion1 + scoreQuestion2) / 2;

                Toast.makeText(getApplicationContext(), "Score Saved Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(QcmActivity.this, DetailsActivity.class);
                intent.putExtra("FINAL_SCORE", finalScore);
                startActivity(intent);
            }
        });
    }

    private int calculateScoreQuestion1(boolean answer1, boolean answer2, boolean answer3) {
        if (answer1 && !answer2 && !answer3) {
            return 100;
        } else {
            return 0;
        }
    }
    private int calculateScoreQuestion2(boolean affirmation1, boolean affirmation2, boolean affirmation3) {
        int score = 0;

        boolean correctAffirmation1 = affirmation1;
        boolean correctAffirmation2 = affirmation2;
        boolean correctAffirmation3 = !affirmation3;

        if (correctAffirmation1) {
            score += 50;
        }
        if (correctAffirmation2) {
            score += 50;
        }
        if (correctAffirmation3) {
            score += 50;
        }

        if (!correctAffirmation1 && score > 0) {
            score -= 50;
        }
        if (!correctAffirmation2 && score > 0) {
            score -= 50;
        }
        if (!correctAffirmation3 && score > 0) {
            score -= 50;
        }

        return score;
    }
}