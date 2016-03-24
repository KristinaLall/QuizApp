package com.example.w0274203.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Quiz_Page extends Activity {

    //make objects for controls
    private TextView textView_Definition;
    private TextView textView_Name;
    private Button btn_term1;
    private Button btn_term2;
    private Button btn_term3;
    private Button btn_term4;

    private Quiz_BusinessLogic newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__page);

        //connect xml and objects
        textView_Name = (TextView) findViewById(R.id.textView_Name);
        textView_Definition = (TextView) findViewById(R.id.textView_Definition);
        btn_term1 = (Button) findViewById(R.id.btn_term1);
        btn_term2 = (Button) findViewById(R.id.btn_term2);
        btn_term3 = (Button) findViewById(R.id.btn_term3);
        btn_term4 = (Button) findViewById(R.id.btn_term4);

        newQuiz = new Quiz_BusinessLogic(this);

        newQuiz.initialize();

        displayName(); //display the name by using intent and bundles

        loadNextQuestion();

        //Listeners
        View.OnClickListener oclBtnTerm1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion(btn_term1);
            }
        };//end oclBtnTerm1

        View.OnClickListener oclBtnTerm2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion(btn_term2);
            }
        };//end oclBtnTerm2

        View.OnClickListener oclBtnTerm3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion(btn_term3);
            }
        };//end oclBtnTerm3

        View.OnClickListener oclBtnTerm4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion(btn_term4);
            }
        };//end oclBtnTerm4

        //connect listeners to controls
        btn_term1.setOnClickListener(oclBtnTerm1);
        btn_term2.setOnClickListener(oclBtnTerm2);
        btn_term3.setOnClickListener(oclBtnTerm3);
        btn_term4.setOnClickListener(oclBtnTerm4);

    }//end onCreate method

    private void loadNextQuestion() {
        //Checking if there are questions left in the arraylist.
        if (!newQuiz.hasNextQuestion())
        {
            String stringScore = newQuiz.getFinalScore();
            Intent i = new Intent("Quiz_Score");
            Bundle extras = new Bundle();
            extras.putString("Score",stringScore );
            i.putExtras(extras);
            startActivityForResult(i, 1);
            finish();
        }//end if
        else //If there are questions left, load them.
        {
            //display question and add correct answer to ArrayList answers.
            textView_Definition.setText(newQuiz.getCurrentQuestion());

            ArrayList<String> answers = newQuiz.getPoolAnswers();

            btn_term1.setText(answers.get(0));
            btn_term2.setText(answers.get(1));
            btn_term3.setText(answers.get(2));
            btn_term4.setText(answers.get(3));
        }//end else

    }//end loadNextQuestion

    private void answerQuestion(Button btn_term)
    {
        if (newQuiz.answerQuestion(btn_term.getText().toString()))
            Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getBaseContext(),"Incorrect!",Toast.LENGTH_SHORT).show();

        loadNextQuestion();
    }//end answerQuestion

    private void displayName() {
        String myStuff="";
        Bundle extras = getIntent().getExtras();
        //Getting the name from the Bundle
        if(extras != null) //if bundle has content
        {
            myStuff = extras.getString("Name");
            textView_Name.setText("Welcome, " + myStuff + "!");
        }
    }//end answerQuestion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz__page, menu);
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
}
