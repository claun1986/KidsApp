package com.example.kinderapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;

/**
 * Created by Sarah on 19.10.14.
 */
public class neuKrankheiten extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neukrankheiten);

        //SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinner_dosierung);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dosierung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //NEUES MEDIKAMENTEN FELD HINZUFÜGEN
        Button add2 = (Button) findViewById(R.id.add2);
        //add2.setOnClickListener();



    }


}
