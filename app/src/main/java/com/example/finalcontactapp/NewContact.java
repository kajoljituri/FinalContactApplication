package com.example.finalcontactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewContact extends AppCompatActivity {
    TextInputEditText name,phone,nickname;
    AutoCompleteTextView circle;
    ImageButton backbtn;
    Button savebtn;
    //Spinner spinner;

    DatabaseReference dbreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        name =(TextInputEditText) findViewById(R.id.name);
        phone =(TextInputEditText) findViewById(R.id.phone);
        nickname=(TextInputEditText) findViewById(R.id.nickname);

        circle =(AutoCompleteTextView)findViewById(R.id.circle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.circle_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        circle.setAdapter(adapter);

        backbtn=(ImageButton) findViewById(R.id.backbtn);
        savebtn= (Button) findViewById(R.id.savebtn);

        dbreference = FirebaseDatabase.getInstance().getReference().child("Contact");


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Nickname =nickname.getText().toString();
                String Circle = circle.getText().toString();
                //String Circle = circle.getSelectedItem().toString();

                int Phonenum =Integer.parseInt(phone.getText().toString());

                Contact contact = new Contact(Name,Nickname,Circle,Phonenum);
                dbreference.push().setValue(contact);

                Toast.makeText(NewContact.this, " values inserted", Toast.LENGTH_SHORT).show();

                Intent savegoback = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(savegoback);

            }
        });



//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
//                (this,R.array.circle_array,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        circle.setAdapter(adapter);
//        circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String data=adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}