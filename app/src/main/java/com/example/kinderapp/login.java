package com.example.kinderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Sarah on 21.10.14.
 */
public class login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
//button setzen statt relative layout
        final Intent intent = new Intent(this, screen1.class);
        RelativeLayout Layout = (RelativeLayout) findViewById(R.id.rl2);
        Layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(intent);
                                      }
                                  }

        );
    }


}
