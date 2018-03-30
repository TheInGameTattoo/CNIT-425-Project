package com.finalproject.joshandnick.finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Josh Longawa & Nick Martino
 */

public class DiceFragment extends Fragment {

    //initializing the variables
    String[] dice = {"d4", "d6", "d8", "d10", "d12", "d20", "d100"};
    ArrayList<String> numbers = populateArray();
    Spinner diceSpinner;
    Spinner numbersSpinner;
    String selectedDie;
    int numberOfDice;
    Button rollButton;
    TextView displayText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.dice_fragment,container, false);

        /*
        *this block of code is for setting up the two sipinners
        *sets the array adapters for each of the spinners
        */
        diceSpinner = (Spinner) rootView.findViewById(R.id.diceSpinner);
        numbersSpinner = (Spinner) rootView.findViewById(R.id.numberOfDiceSpinner);
        ArrayAdapter<String> diceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_view, dice);
        ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_view, numbers);
        diceAdapter.setDropDownViewResource(R.layout.spinner_view);
        numberAdapter.setDropDownViewResource(R.layout.spinner_view);
        diceSpinner.setAdapter(diceAdapter);
        numbersSpinner.setAdapter(numberAdapter);


        //Listener for the diceSpinner for when the user selects an item.
        //sets the selectedDie variable to the selected die
        diceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDie = dice[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        //Listener for the numbersSpinner for when the user selects an item.
        //sets the numberOfDice variable to the selected Number
        numbersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numberOfDice = Integer.valueOf(numbers.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /*
         * sets up the rollButton adn the text view that will display the result
         * also sets up an on click listener for the roll button that will run a rollTheDice method
         */
        rollButton = (Button) rootView.findViewById(R.id.roleButton);
        displayText = (TextView) rootView.findViewById(R.id.displayTextView);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayText.setText(Integer.toString(rollTheDice(selectedDie, numberOfDice)));
            }
        });
        return rootView;
    }

    //method that populates the numberOfDice array with 1-100
    public ArrayList<String> populateArray() {
        ArrayList<String> numbers = new ArrayList<String>();

        for (int i = 1; i < 100; i++) {
            numbers.add(Integer.toString(i));
        }
        return numbers;
    }

    //method that parses the value for a roll based on the type of die
    public int rollTheDice(String die, int number) {
        int dieValue = 0;
        int result = 0;
        if (die.equals("d4")) {
            dieValue = 4;
        } else if (die.equals("d6")) {
            dieValue = 6;
        } else if (die.equals("d8")) {
            dieValue = 8;
        } else if (die.equals("d10")) {
            dieValue = 10;
        } else if (die.equals("d12")) {
            dieValue = 12;
        } else if (die.equals("d20")) {
            dieValue = 20;
        } else if (die.equals("d100")) {
            dieValue = 100;
        }

        for (int i = 0; i < number; i++) {
            result += randInt(1, dieValue);
        }

        return result;
    }


    //random function that returns a random integer based on a specified min and max
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
