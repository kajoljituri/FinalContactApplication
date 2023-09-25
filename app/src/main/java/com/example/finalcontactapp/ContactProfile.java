package com.example.finalcontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactProfile extends AppCompatActivity {
    TextView name,phoneNum,circle;
    ImageButton backbtn,editbtn;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
        name=(TextView) findViewById(R.id.name);
        phoneNum=(TextView) findViewById(R.id.number);
        circle=(TextView) findViewById(R.id.circle);
        backbtn=(ImageButton) findViewById(R.id.backbtn);
        editbtn=(ImageButton) findViewById(R.id.editbtn);
        imageView = (ImageView) findViewById(R.id.image);

        name.setText(getIntent().getExtras().getString("name"));
        circle.setText(getIntent().getExtras().getString("circle"));

        int img = getIntent().getIntExtra("imageview",0);
        imageView.setImageResource(img);



        int ph = getIntent().getExtras().getInt("phno");
        phoneNum.setText(Integer.toString(ph));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(ContactProfile.this, "back", Toast.LENGTH_SHORT).show();
                startActivity(goback);
            }
        });
    }
}