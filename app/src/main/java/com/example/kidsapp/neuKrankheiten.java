package com.example.kidsapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Sarah on 19.10.14.
 */
public class neuKrankheiten extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neukrankheiten);

        Button beginn = (Button) findViewById(R.id.Beginn);
        beginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button ende = (Button) findViewById(R.id.Ende);
        ende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
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


//DATE PICKER
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


                        //Text Feld erzeugen bei Datum-Auswahl
                        TextView tvBeginn = (TextView) findViewById(R.id.tvBeginn);
                        tvBeginn.setText(dayOfMonth + "." + monthOfYear + "." + year);

                        //Toast.makeText(getApplicationContext(), year + "-" + monthOfYear + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
                    }
                }, mYear, mMonth, mDay);

        // DatePickerDialog anschauen
        myFancyDatePicker.show();

    }

    private void showDatePickerDialog1() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog myFancyDatePicker1 = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        TextView tvEnde = (TextView) findViewById(R.id.tvEnde);
                        tvEnde.setText( dayOfMonth + "." + monthOfYear + "." +year );
                    }
                }, mYear, mMonth, mDay);
        myFancyDatePicker1.show();
    }
//ALARM Activity öffnen
    final Intent intent1 = new Intent(this, alarm.class);




//SPEICHERN Button speichern = (Button) findViewById(R.id.speichern);



    }



