package com.golan.amit.masterid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String DEBUGTAG = "AMGO";
    public static final boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  TODO: landing page and redirect gracefully

        Intent i = new Intent(this, ControlCalculateActivity.class);
        startActivity(i);

    }
}
