package com.finalproject.joshandnick.finalproject;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Josh Longawa on 12/5/2017.
 */

public class SpellInfoDialog extends DialogFragment {
    SpellsModel.MyObject spell;
    TextView levelAndSchool;
    TextView castTime;
    TextView range;
    TextView components;
    TextView duration;
    TextView concentration;
    TextView ritual;
    TextView playerClass;
    TextView description;
    TextView materials;
    TextView source;

    Button closeButton;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.spell_info_dialog, container,false);

        //setting all widget variables
        getDialog().setTitle(spell.name);
        levelAndSchool = (TextView) rootView.findViewById(R.id.levelAndSchoolText);
        castTime = (TextView) rootView.findViewById(R.id.castTimeText);
        range = (TextView) rootView.findViewById(R.id.rangeText);
        components = (TextView) rootView.findViewById(R.id.componentsText);
        duration = (TextView) rootView.findViewById(R.id.durationText);
        concentration = (TextView) rootView.findViewById(R.id.concentrationText);
        ritual = (TextView) rootView.findViewById(R.id.ritualText);
        playerClass = (TextView) rootView.findViewById(R.id.classText);
        description = (TextView) rootView.findViewById(R.id.descriptionText);
        materials = (TextView) rootView.findViewById(R.id.materialsText);
        source = (TextView) rootView.findViewById(R.id.sourceText);

        //setting text
        levelAndSchool.setText(spell.level + " " + spell.school);
        castTime.setText(spell.castTime);
        range.setText(spell.range);
        components.setText(spell.components);
        duration.setText(spell.duration);
        concentration.setText(spell.concentration);
        ritual.setText(spell.ritual);
        playerClass.setText(spell.playerClass);
        description.setText(Html.fromHtml(spell.desc).toString());
        materials.setText(spell.material);
        source.setText(spell.page);

        closeButton = (Button) rootView.findViewById(R.id.closeButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return rootView;
    }

    //seter method for spell
    public void setSpell(SpellsModel.MyObject spell) {
        this.spell = spell;
    }
}
