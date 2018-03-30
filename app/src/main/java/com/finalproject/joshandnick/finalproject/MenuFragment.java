package com.finalproject.joshandnick.finalproject;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Nick Martino & Josh Longawa
 */

public class MenuFragment extends Fragment {
    ImageButton spellButton;
    ImageButton diceButton;
    ImageButton noteButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.menu_fragment,container, false);

        spellButton = (ImageButton) rootView.findViewById(R.id.spellBookButton);
        diceButton = (ImageButton) rootView.findViewById(R.id.diceButton);
        noteButton = (ImageButton) rootView.findViewById(R.id.noteButton);

        //On click listeners that communicate to the mainActivity to call a spellFragment change method
        spellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.spellFragmentChange();
                //spellButton.setClickable(false);
                spellButton.setPressed(true);
            }
        });
        //On click listeners that communicate to the mainActivity to call a diceFragment change method
        diceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.diceFragmentChange();
                diceButton.getBackground().setState(new int[]{android.R.attr.state_pressed});
            }
        });
        //On click listeners that communicate to the mainActivity to call a noteFragment change method
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.notesFragmentChange();
                noteButton.getBackground().setState(new int[]{android.R.attr.state_pressed});
            }
        });
        return rootView;
    }
}
