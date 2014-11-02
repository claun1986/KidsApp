package com.example.kidsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

//import org.apache.commons.logging.Log;

import java.util.Calendar;


/**
 * Created by Sarah on 19.10.14.
 */
public class neuKrankheiten extends Activity {

    //DATENBANK http://android-developers.de/tutorials-faqs/der-umgang-der-sqlite-datenbank-414.html
    final static String MY_DB_NAME = "MED_DB";
    final static String MY_DB_TABLE = "Med_Name";
    final static String tag="kidsapp";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neukrankheiten);

        SQLiteDatabase myDB= null;
        try {
            myDB = this.openOrCreateDatabase(MY_DB_NAME, MODE_PRIVATE, null);
            Cursor c = myDB.rawQuery("SELECT _id, name || ', ' || name, menge, dosierung, morgens, mittags, abends, beginn, ende FROM "+MY_DB_TABLE, null);

            startManagingCursor(c);
            getListView().setOnCreateContextMenuListener(this);
            //nicht fertig, noch andere felder einfügen und code anpassen
            final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.neukrankheiten, c, new String[] {" _id"}, new int[] {R.id.neuesMedikament});
            adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    final String ColNameModel = cursor.getString(1); //statt theCursor im tutorial
                    ((TextView)view).setText(ColNameModel);
                    return true;
                }
            });
            this.setListAdapter(adapter);
        } finally {
            if(myDB != null)
                myDB.close();
        }




        /*     final ImageButton add_alarm = (ImageButton) findViewById(R.id.alarm_add);
            add_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(null);
                    builder.setMessage("ALARM");
                    builder.setTitle("ALARM");
                    AlertDialog dialog = builder.create();
                }
            });


//Add Alarm Dialog
//add alarm dialog
    private void setAlarm() {
        ImageButton add_alarm = (ImageButton) findViewById(R.id.alarm_add);
        add_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("ALARM");
                builder.setTitle("ALARM");
                AlertDialog dialog = builder.create();
            }
        });
    }
        */
//speichern
    /*    Button speichern = (Button) findViewById(R.id.speichern);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medSpeichern();
            }
        });
    */
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
        AlertDialog.Builder builder = new AlertDialog.Builder(neuKrankheiten.this);
        LayoutInflater inflater = getLayoutInflater();
        View rootView = inflater.inflate(R.layout.medcontainer, null); //root View als Variable gesetzt, um neuKrankheiten.xml??? oder medcontainer? anzusprechen

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

                        LayoutInflater inflater = getLayoutInflater();
                        View rootView = inflater.inflate(R.layout.medcontainer, null); //root View als Variable gesetzt, um neuKrankheiten.xml??? oder medcontainer? anzusprechen

                        //Text Feld erzeugen bei Datum-Auswahl
                        TextView tvBeginn = (TextView) rootView.findViewById(R.id.tvBeginn);
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

                        LayoutInflater inflater = getLayoutInflater();
                        View rootView = inflater.inflate(R.layout.medcontainer, null); //root View als Variable gesetzt, um neuKrankheiten.xml??? oder medcontainer? anzusprechen

                        TextView tvEnde = (TextView) rootView.findViewById(R.id.tvEnde);
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

//Daten auslesen und speichern


    //   private void medSpeichern() {
//DATENBANK

    private void onCreateDBandDBTabled()
    {
        SQLiteDatabase myDB = null;
        try {
            myDB = this.openOrCreateDatabase(MY_DB_NAME, MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + MY_DB_TABLE
                    + " (_id integer primary key autoincrement, name varchar(100), menge varchar(100), dosierung integer(3), morgens boolean, mittags boolean, abends boolean, beginn date(100), ende date(100))"

                    +";");


        } finally {
            if (myDB != null)
                myDB.close();
        }

//Neuen Datensatz hinzufügen

        EditText neuMed = (EditText) findViewById(R.id.neuesMedikament);
        EditText menge = (EditText) findViewById(R.id.menge);
        Spinner dosierung = (Spinner) findViewById(R.id.spinner_dosierung);
        CheckBox morgens = (CheckBox) findViewById(R.id.c1);
        CheckBox mittags = (CheckBox) findViewById(R.id.c2);
        CheckBox abends = (CheckBox) findViewById(R.id.c3);
        TextView beginn = (TextView) findViewById(R.id.tvBeginn);
        TextView ende = (TextView) findViewById(R.id.tvEnde);
        int i = dosierung.getSelectedItemPosition();

        myDB.execSQL("INSERT INTO "+MY_DB_TABLE+" name, menge, dosierung, morgens, mittags, abends, beginn, ende) "
                +"VALUES ('"+neuMed.getText().toString()+"',"+
                "'"+menge.getText().toString()+"',"+
                "'"+i+"',"+
                "'"+morgens.getContext().toString()+"',"+
                "'"+mittags.getContext().toString()+"',"+
                "'"+abends.getContext().toString()+"',"+
                "'"+beginn.getText().toString()+"',"+
                "'"+ende.getText().toString()+"',"+
                "');");
        Log.v(tag, "Insert new Med: " +neuMed.getText().toString() + ", " +menge.getText().toString() + ", " +i+ ", " +morgens.getText().toString()+", " + mittags.getText().toString()+", "+ abends.getText().toString()+", "+ beginn.getText().toString()+", "+ ende.getText().toString());

        if (getIntent().hasExtra("id")) {
            long l = getIntent().getExtras().getLong("id");
            myDB.execSQL("UPDATE "+MY_DB_TABLE+" SET" +
                    "name ='"+neuMed.getText().toString()+"',"+
                    "menge ='"+menge.getText().toString()+"',"+
                    "doesierung ='"+i+"',"+
                    "morgens ='"+morgens.getContext().toString()+"',"+
                    "mittags ='"+mittags.getContext().toString()+"',"+
                    "abends ='"+abends.getContext().toString()+"',"+
                    "beginn ='"+beginn.getText().toString()+"',"+
                    "ende ='"+ende.getText().toString()+"',"+
                    "WHERE _id ="+l+";");
            Log.v(tag, "Insert new Med: " +neuMed.getText().toString() + ", " +menge.getText().toString() + ", " +i+ ", " +morgens.getText().toString()+", " + mittags.getText().toString()+", "+ abends.getText().toString()+", "+ beginn.getText().toString()+", "+ ende.getText().toString() +" updated");
        }


    }



}



