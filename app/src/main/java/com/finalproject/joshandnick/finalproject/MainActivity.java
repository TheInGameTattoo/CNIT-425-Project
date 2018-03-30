package com.finalproject.joshandnick.finalproject;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/*
*  Josh Longawa & Nick Martino
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method that changes the mainFragment to display the spellFragment layout
    public void spellFragmentChange() {
        SpellBookFragment sellBookFragment = new SpellBookFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFragment, sellBookFragment, "FragmentName");
        transaction.commit();
    }
    // method that changes the mainFragment to display the diceFragment layout
    public void diceFragmentChange() {
        DiceFragment diceFragment = new DiceFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFragment, diceFragment, "FragmentName");
        transaction.commit();
    }
    // method that changes the mainFragment to display the notesFragment layout
    public void notesFragmentChange() {
        NotesFragment notesFragment = new NotesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFragment, notesFragment, "FragmentName");
        transaction.commit();
    }
}
