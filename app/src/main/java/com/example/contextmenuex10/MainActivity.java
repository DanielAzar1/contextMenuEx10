package com.example.contextmenuex10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity gathers parameters of a series and launches the result screen with these parameters
 */
public class MainActivity extends AppCompatActivity {

    // Variables
    EditText et1, et2;
    Switch sw1;
    Button btn1;
    boolean seriesType;
    int firstNum, difference;

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking
        et1 = findViewById(R.id.num1ET);
        et2 = findViewById(R.id.num2ET);
        sw1 = findViewById(R.id.seriesTypeSwitch);
        btn1 = findViewById(R.id.nxtBtn);


    }

    /**
     * @param view
     * Onclick function that launches Go() function if all variables are valid
     */
    // On click method, here we will check values and send to the result screen
    public void goNext(View view)
    {
        // In case numbers aren't valid
        try
        {
            // Get numbers from ET
            firstNum = Integer.parseInt(String.valueOf(et1.getText()));
            difference = Integer.parseInt(String.valueOf(et2.getText()));
            seriesType = sw1.isChecked();
        }

        catch (NumberFormatException e)
        {
            //Displays error msg and returns
            Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Launches result screen
        go();


    }

    /**
     * Function that launches the result screen with the values entered by the user
     */
    public void go() {
        // Creates new intent with all relevant values
        Intent si = new Intent(this,resultScreen.class);
        si.putExtra("seriesType", seriesType);
        si.putExtra("firstNum", firstNum);
        si.putExtra("difference", difference);

        // Launce resultScreen with startActivityForResult
        startActivityForResult(si, 100);
    }
}