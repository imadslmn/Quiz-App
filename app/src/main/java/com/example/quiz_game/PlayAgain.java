package com.example.quiz_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.color.utilities.Score;


public class PlayAgain extends Activity {

    Button playAgain,exit;
    TextView wrongAnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);
        Intent intent = getIntent();
        if (intent != null) {
            String receivedString = intent.getStringExtra("yourScore");
            String correctAns = intent.getStringExtra("CorrectAns");

            if (receivedString != null) {
                // Find the TextView in your layout
                TextView textViewContent = findViewById(R.id.textViewScore);

                // Set the received string to the TextView
                textViewContent.setText(receivedString);
            }


            if (correctAns != null) {
                // Find the TextView for the correct answer in your layout
                TextView textViewContentCorrectAnswer = findViewById(R.id.textViewCorrectAnswer);

                // Set the correct answer string to the TextView
                textViewContentCorrectAnswer.setText("Correct Answer: " + correctAns);

            }
        }


       //Initialize
        playAgain = (Button) findViewById(R.id.playAgainButton);
        exit = (Button) findViewById(R.id.exit);
        wrongAnsText = (TextView)findViewById(R.id.wrongAns);

        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        try{
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/nontarnishable.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
        } catch (Exception e) {
            // Handle generic Exception
            e.printStackTrace();
            Log.e("FontLoading", "Exception: " + e.getMessage());
            // Show an error message to the user or perform necessary actions
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
