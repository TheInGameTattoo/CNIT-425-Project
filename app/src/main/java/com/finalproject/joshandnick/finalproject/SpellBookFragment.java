package com.finalproject.joshandnick.finalproject;

import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Josh Longawa on 11/28/2017.
 */

public class SpellBookFragment extends Fragment {
    ArrayList<String> spellList;
    Spinner classSpinner;
    Spinner schoolSpinner;
    String classFilter;
    String schoolFilter;
    String[] playerClass = {"All Classes", "Bard", "Cleric", "Druid", "Paladin", "Ranger", "Ritual Caster", "Sorcerer", "Warlock", "Wizard"};
    String[] school = {"All Schools", "Abjuration", "Conjuration", "Divination", "Enchantment", "Evocation", "Illusion","Necromancy", "Transmutation"};
    Button searchButton;
    EditText searchText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.spell_book_fragment,container,
                false);

        //populates the spellList with the contents of the spell json
        spellList = new ArrayList<String>();
        String myJson = inputStreamToString(getResources().openRawResource(R.raw.spells));
        final SpellsModel myModel = new Gson().fromJson(myJson, SpellsModel.class);

        for(int i = 0; i < myModel.list.size(); i++) {
            spellList.add(myModel.list.get(i).name);
        }

        final ListView listView = (ListView) rootView.findViewById(R.id.listViewJson);

        //sets list view on lick listener to open the dialog pop up
        //makes sure that the spell clicked is the intended spell because of the search funciton
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(spellList.get(i));
                int j;
                for (j = 0; j < myModel.list.size(); j++) {
                    if(myModel.list.get(j).name.equals(spellList.get(i))) {
                        break;
                    }
                }
                showDialog(view, myModel.list.get(j));
            }
        });

        //sets the array adapter and sets teh text color of the array items to black
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, spellList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(listAdapter);

        //sets the spinners for the search options
        classSpinner = (Spinner) rootView.findViewById(R.id.classSpinner);
        schoolSpinner = (Spinner) rootView.findViewById(R.id.schoolSpinner);

        /*
        sets the array adapters for the spinners
        */
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.spinner_view, playerClass);
        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.spinner_view, school);

        classAdapter.setDropDownViewResource(R.layout.spinner_view);
        schoolAdapter.setDropDownViewResource(R.layout.spinner_view);
        classSpinner.setAdapter(classAdapter);
        schoolSpinner.setAdapter(schoolAdapter);


        //on item select listener for the items in the class spinner
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classFilter = playerClass[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //on item select listener for the items in the school spinner
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                schoolFilter = school[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /*
        search buoot set up
        on click listener that clears the spell list view, then populates it
        with the spells that fit the search requirements
         */
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        searchText = (EditText) rootView.findViewById(R.id.searchEditText);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = searchText.getText().toString();
                spellList.clear();
                spellList.addAll(searchSpells(myModel, schoolFilter, classFilter, search));
                listView.setAdapter(listAdapter);
            }
        });
        return rootView;
    }

    //converts and input stream to a string
    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    //search function that filters out unwanted spells
    public ArrayList<String> searchSpells(SpellsModel myModel, String schoolFilter, String classFilter, String searchFilter) {
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < myModel.list.size(); i++) {
            if ((myModel.list.get(i).school.contains(schoolFilter) || schoolFilter.equals("All Schools"))
                    && (myModel.list.get(i).playerClass.contains(classFilter) || classFilter.equals("All Classes"))
                    && (myModel.list.get(i).name.toLowerCase().contains(searchFilter.toLowerCase()) || searchFilter.equals(null) || searchFilter.equals(""))) {
                list.add(myModel.list.get(i).name);
            }
        }

        return list;
    }

    //show dialog funciton that shows the dialog pop up when a spell is clicked
    private void showDialog(View v, SpellsModel.MyObject spell) {
        FragmentManager manager = getActivity().getFragmentManager();
        SpellInfoDialog myDialog = new SpellInfoDialog();
        myDialog.setSpell(spell);
        myDialog.show(manager, "myDialog");
    }
}
