package com.example.kidsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sarah on 13.10.14.
 */
public class screen2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        final Intent intent1 = new Intent(this, screen2_1.class);
        TextView Krankheiten = (TextView) findViewById(R.id.Krankheiten);
        Krankheiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });


    }
}
