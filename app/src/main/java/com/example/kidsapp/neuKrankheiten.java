package com.example.kidsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by Sarah on 19.10.14.
 */
public class neuKrankheiten extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neukrankheiten);

//dialog button drücken
        Button add2 = (Button) findViewById(R.id.add2);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

 //button notification drücken
        Button not = (Button) findViewById(R.id.not_button);
        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });



     }


 //medcontainer anzeigen
   private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(neuKrankheiten.this);
        View rootView = inflater.inflate(R.layout.medcontainer, null); //root View als Variable gesetzt, um neuKrankheiten.xml anzusprechen

        builder.setView(rootView);
        AlertDialog dialog = builder.create();
        dialog.show();


 //buttons Datepicker
       Button beginn = (Button) rootView.findViewById(R.id.Beginn);
       beginn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDatePickerDialog();
           }
       });

       Button ende = (Button) rootView.findViewById(R.id.Ende);
       ende.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDatePickerDialog1();
           }
       });


//SPINNER
       Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_dosierung);
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dosierung, android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);

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
                        tvBeginn.setText(dayOfMonth + "." + (monthOfYear+1) + "." + year); //+1 weil default 0 ist(monat wird minus 1 angezeigt)

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
                        tvEnde.setText( dayOfMonth + "." + (monthOfYear+1) + "." +year );
                    }
                }, mYear, mMonth, mDay);
        myFancyDatePicker1.show();
    }



 //Notification builder
    private void notification(){
        Notification.Builder mBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_alarm_set)
                .setContentTitle("KidsApp")
                .setContentText("Alarm gesetzt");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());

    }




}



