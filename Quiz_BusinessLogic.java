package com.example.w0274203.quizapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Quiz_BusinessLogic {

    private int myScore = 0;
    Map<String,String> map = new HashMap<String, String>(); //creating the map
    ArrayList<String> definitions = new ArrayList<String>();
    ArrayList<String> terms = new ArrayList<String>();
    private Context myContext;
    private String correctAnswer;
    private static final String TAG = "MyActivityLog";

    Quiz_BusinessLogic(Context myContext)
    {
        this.myContext = myContext;
    }//end constructor Quiz_Business_Logic

    //Reads the file and populates the hashmap
    public void initialize() {
        InputStream inputFile = myContext.getResources().openRawResource(R.raw.questions);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFile));

        String lineRead = null;
        String[] line;
        String delimiter = ":";

        //Reading from the file and storing the definitions and terms in arraylists
        try{
            while((lineRead = br.readLine())!=null)
            {
                line = lineRead.split(delimiter);
                map.put(line[0],line[1]);
                definitions.add(line[0]);
                terms.add(line[1]);
            }//end while

            Collections.shuffle(definitions); //shuffle the definitions array once

        }catch(IOException e)
        {
            Log.e(TAG,"Error reading from file!"); //writing to Android's logging mechanism
        }
    }//end readFromFile

    public String getCurrentQuestion() {return  definitions.get(0);}

    //Get the answers choices
    public ArrayList<String> getPoolAnswers() {

        ArrayList<String> answers = new ArrayList<String>();
        correctAnswer = map.get(definitions.get(0));
        answers.add(correctAnswer); //adding correct answer to ArrayList answers

        //add 3 random values to answers.
        Random random = new Random();
        while((answers.size() < 4))
        {
            String randomAnswer = terms.get(random.nextInt(terms.size()));
            if(!answers.contains(randomAnswer))
                answers.add(randomAnswer);
        }//end while

        //Shuffle and then display answers on buttons.
        Collections.shuffle(answers);
        return answers;
    }

    public boolean hasNextQuestion()
    {
        return (!definitions.isEmpty());
    }

    public boolean answerQuestion(String answerText)
    {
        boolean isCorrect = answerText.equals(correctAnswer);
        if (isCorrect)
            myScore++;

        definitions.remove(0);
        return isCorrect;
    }

    public String getFinalScore()
    {
        return (myScore + "/" + terms.size());
    }

}//end class Quiz_BusinessLogic
