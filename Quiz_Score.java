package com.example.w0274203.quizapp;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class Quiz_Score extends Activity {

    //Make Objects for Controls
    TextView textView_ScoreMessage;
    TextView textView_Score;
    TextView textView_DisplayScore;
    Button btn_PlayAgain;
    Button btn_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__score);

        //connect xml and objects
        textView_ScoreMessage = (TextView) findViewById(R.id.textView_ScoreMessage);
        textView_Score = (TextView) findViewById(R.id.textView_Score);
        textView_DisplayScore = (TextView) findViewById(R.id.textView_DisplayScore);
        btn_PlayAgain = (Button) findViewById(R.id.btn_PlayAgain);
        btn_Exit = (Button) findViewById(R.id.btn_Exit);

        String myStuff="";
        Bundle extras = getIntent().getExtras();

        if(extras != null) //if bundle has content
        {
            myStuff = extras.getString("Score");
            if (myStuff.equals("10/10"))
                textView_ScoreMessage.setText("EXCELLENT! ALL CORRECT!");
            else
                textView_ScoreMessage.setText("Thanks for playing!");

            textView_DisplayScore.setText(myStuff);
        }//end if

        //create listeners
        View.OnClickListener oclBtnPlayAgain = new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Go back to Start Page
                Intent i = new Intent("Quiz");
                startActivityForResult(i,1);
                finish();
            }
        };//end oclBtnPlayAgain

        View.OnClickListener oclBtnExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Exit the App
                System.exit(0);
            }
        };//end oclBtnExit

        //connect listeners to controls
        btn_PlayAgain.setOnClickListener(oclBtnPlayAgain);
        btn_Exit.setOnClickListener(oclBtnExit);

    }//end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz__score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//end Activity
