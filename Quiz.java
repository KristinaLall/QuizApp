package com.example.w0274203.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Quiz extends Activity {

    //Make the objects for controls
    private EditText textView_enterName;
    private TextView lbl_enterName;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //connect xml and objects
        textView_enterName = (EditText)findViewById(R.id.textView_enterName);
        lbl_enterName = (TextView) findViewById((R.id.lbl_enterName));
        btn_start = (Button) findViewById(R.id.btn_start);

        //listeners
        View.OnClickListener ocl_btn_start = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameEntered = textView_enterName.getText().toString();
                if (!isValidString(nameEntered))
                    Toast.makeText(getBaseContext(),"Please enter a name!",Toast.LENGTH_SHORT).show();
                else
                {
                    Intent i = new Intent("Quiz_Page");
                    Bundle extras = new Bundle();
                    extras.putString("Name", nameEntered);
                    i.putExtras(extras);
                    startActivityForResult(i, 1);
                    finish();
                }//end else
            }
        };//end ocl_btn_start

        //connect listeners to controls
        btn_start.setOnClickListener(ocl_btn_start);
    }

    public boolean isValidString(String nameEntered)
    {
        if (nameEntered.trim().equals(""))
            return false;
        else
            return true;
    }//end validString

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
