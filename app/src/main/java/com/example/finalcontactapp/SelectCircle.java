package com.example.finalcontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

public class SelectCircle extends AppCompatActivity {
    TextView circle1Count,circle2Count,circle3Count;
    DBManager dbManager;

    //checkbox

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_circle);

        circle1Count =findViewById(R.id.NoOfContactCircle1);
        circle2Count =findViewById(R.id.NoOfContactCircle2);
        circle3Count =findViewById(R.id.NoOfContactCircle3);

        dbManager.open();
        int circle1 = dbManager.getContactCount("1");
        int circle2 = dbManager.getContactCount("2");
        int circle3 = dbManager.getContactCount("3");
        circle1Count.setText(String.valueOf(circle1));
        circle2Count.setText(String.valueOf(circle2));
        circle3Count.setText(String.valueOf(circle3));








    }
}