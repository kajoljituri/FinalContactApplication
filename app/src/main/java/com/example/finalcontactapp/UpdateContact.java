package com.example.finalcontactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateContact extends AppCompatActivity {
    TextInputEditText name1, phone1, nickname1;
    AutoCompleteTextView circle1;
    ImageButton backbtn;
    Button updatebtn;
    private DBManager dbManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        name1 = (TextInputEditText) findViewById(R.id.name1);
        phone1 = (TextInputEditText) findViewById(R.id.phone1);
        nickname1 = (TextInputEditText) findViewById(R.id.nickname1);
        dbManager = new DBManager(this);
        dbManager.open();

        circle1 = (AutoCompleteTextView) findViewById(R.id.circle1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.circle_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        circle1.setAdapter(adapter);

        backbtn = (ImageButton) findViewById(R.id.backbtn);
        updatebtn = (Button) findViewById(R.id.updatebtn);

        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String newname = intent.getStringExtra("name");
        // display the string into textView
        name1.setText(newname);

        String newphone = intent.getStringExtra("phonenum");
        phone1.setText(newphone);

        String newcircle = intent.getStringExtra("circle");
        circle1.setText(newcircle, false);

        String newnickname = intent.getStringExtra("nickname");
        nickname1.setText(newnickname);
        long ID= intent.getLongExtra("id",1);
 //       Log.v("TAG12345", String.valueOf(ID));


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String oldPhone = getIntent().getStringExtra("phonenum");
//                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//                 Query query = databaseRef.child("Contact").orderByChild("phoneNumber").equalTo(oldPhone);
//                query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        String documentKey = String.valueOf(task.getResult().getValue());
//                        Log.v("TAG123",documentKey);
//                        String key = null;
//                        try {
//                            // Parse the string as a JSON object
//                            JSONObject jsonObject = new JSONObject(documentKey);
//                            // Get the key from the JSON object (assuming there's only one key)
//                            key = jsonObject.keys().next();
//                            // Print or use the key as needed
//                            System.out.println("Key: " + key);
//                        } catch (JSONException e) {
//                            // Handle JSON parsing error
//                            e.printStackTrace();
//                        }
//                        Log.v("TAGK",key);
//                 dbreference.push().setValue(contact);
//                 Toast.makeText(NewContact.this, "values inserted", Toast.LENGTH_SHORT).show();
//                updatedData.put("name", name);// Update other fields as needed// key what is in fire base/  //value above attribute name
//                updatedData.put("nickname", nickname);
//                updatedData.put("phoneNumber", phNum);
//                updatedData.put("circle", circle);
//                Update other fields as needed
//                documentReference.updateChildren(updatedData);
//                DatabaseReference documentReference = databaseRef.child("Contact").child(key);
//                Map<String, Object> updatedData = new HashMap<>();

                String name = name1.getText().toString();
                String nickname = nickname1.getText().toString();
                String phNum = phone1.getText().toString();
                String circle = circle1.getText().toString();


                Contact contact = new Contact(name,phNum, nickname, circle);
               // dbManager.updateTable(contact.getId(), contact.getName(), contact.getnickname(), contact.getCircle(), contact.getPhoneNumber());
                dbManager.updateTable(ID,name,phNum,nickname,circle);
                Log.v("TAG123", dbManager.toString());
                Log.v("Tag789", String.valueOf(ID));

                Toast.makeText(UpdateContact.this, "Updated contact!", Toast.LENGTH_SHORT).show();
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);


            }
        });

    }
}
