package com.example.finalcontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
    TextView name,phoneNum,circle,initial;
    ImageButton backbtn;
    ImageButton editbtn;
   // ImageView imageView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
        name=(TextView) findViewById(R.id.name);
        phoneNum=(TextView) findViewById(R.id.number);
        circle=(TextView) findViewById(R.id.circle);
        backbtn=(ImageButton) findViewById(R.id.backbtn);


        editbtn=(ImageButton) findViewById(R.id.editbtn);
//        editbtn.setImageResource(R.drawable.baseline_edit_24);
//        if (editbtn!= null) {
//            editbtn.setImageResource(R.drawable.baseline_edit_24); // Set the image resource
//        } else {
//            // Handle the case where myImageButton is null (e.g., log an error or take appropriate action)
//        }

        initial = (TextView) findViewById(R.id.profileDP);

        name.setText(getIntent().getExtras().getString("name"));
        circle.setText("Circle "+getIntent().getExtras().getString("circle"));

//        int img = getIntent().getIntExtra("imageview",0);
//        imageView.setImageResource(img);

        //String initials =getIntent().getExtras().getString("Initial");
        initial.setText(String.valueOf(getIntent().getExtras().getString("name").charAt(0)).toUpperCase());

        phoneNum.setText(getIntent().getExtras().getString("phno"));
//        ph = getIntent().getExtras().getInt("phno");
//        phoneNum.setText(Long.toString(ph));

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(ContactProfile.this, "back", Toast.LENGTH_SHORT).show();
                startActivity(goback);
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(view.getContext(),UpdateContact.class);
                String nicknm =getIntent().getExtras().getString("nickname");
                update.putExtra("nickname",nicknm);

                String nm = getIntent().getExtras().getString("name");
                update.putExtra("name",nm);

                String circle =getIntent().getExtras().getString("circle");
                update.putExtra("circle",circle);

                String number =getIntent().getExtras().getString("phno");
                update.putExtra("phonenum",number);

                long id =getIntent().getExtras().getLong("id");
                update.putExtra("id",id);

                //view.getContext().startActivity(update);
                startActivity(update);

            }
        });




    }
}