package com.example.kinderapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Sarah on 19.10.14.
 */
public class neuKrankheiten extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neukrankheiten);

        Button datum = (Button) findViewById(R.id.datum);
        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinner_dosierung);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dosierung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final LinearLayout ll_container = (LinearLayout) findViewById(R.id.newMedField);

        //NEUES MEDIKAMENTEN FELD HINZUFÜGEN
        Button add2 = (Button) findViewById(R.id.add2);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TextView tv1 = new TextView(getApplicationContext());
                //tv1.setText("TEST");
                LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.medcontainer, null);
                ll_container.addView(ll);
            }
        });

//        //CALENDER VIEW
//        CalendarView cw1 = (CalendarView) findViewById(R.id.cw1);
//        cw1.setDate(2014);
    }
    private void showDatePickerDialog() {
        // Datum von heute auslesen
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialog erzeugen (hat 5 Parameter):
        // Context (this), Listener, 3x initiales Datum
        DatePickerDialog myFancyDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), year + "-" + monthOfYear + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
                    }
                }, mYear, mMonth, mDay);

        // DatePickerDialog anschauen
        myFancyDatePicker.show();
    }
    }



