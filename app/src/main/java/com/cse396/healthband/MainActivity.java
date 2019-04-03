package com.cse396.healthband;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase connection success", Toast.LENGTH_LONG).show();

    }


}
