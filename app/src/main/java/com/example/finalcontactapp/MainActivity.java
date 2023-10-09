package com.example.finalcontactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ImageButton addbtn,editbtn;
    RecyclerView recyclerview;
    private DBManager dbManager;
    FloatingActionButton sendmessage;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Contact> contacts;

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    private static final int SEND_MESSAGE_REQUEST=2;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview =findViewById(R.id.recyclerview);
        contacts =new ArrayList<>();
        myAdapter= new MyAdapter(this,contacts);
        recyclerview.setAdapter(myAdapter);
        editbtn=(ImageButton)findViewById(R.id.editbtn);
        addbtn = (ImageButton) findViewById(R.id.addbtn);
        sendmessage =(FloatingActionButton)findViewById(R.id.message);

        dbManager =new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            System.out.println("Inside cursor-");
           // int id = cursorObject.getInt(cursorObject.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String nickname = cursor.getString(cursor.getColumnIndexOrThrow("nickname"));
            String circle = cursor.getString(cursor.getColumnIndexOrThrow("circle"));
            String phnum = cursor.getString(cursor.getColumnIndexOrThrow("phnum"));
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
//            Log.v("TAGGGG", String.valueOf(id));
            Contact contact = new Contact(name,nickname,circle,phnum);
            contacts.add(contact);



        }
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));



//        database = FirebaseDatabase.getInstance().getReference("Contact");




//        checkPermission(android.Manifest.permission.READ_CONTACTS, READ_CONTACTS_PERMISSIONS_REQUEST);
//        checkPermission(android.Manifest.permission.SEND_SMS, SEND_MESSAGE_REQUEST);

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Contact contact = dataSnapshot.getValue(Contact.class);
//                    contacts.add(contact);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewContact.class);
                startActivity(intent);
            }
        });

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msg = new Intent(getApplicationContext(),SendMessage.class);
                startActivity(msg);

            }
        });


    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            getContactList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(MainActivity.this, "Contacts Permission Granted", Toast.LENGTH_SHORT).show();
                getContactList();
            }
            else {
                Toast.makeText(MainActivity.this, "Contacts Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == SEND_MESSAGE_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Send Message Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Send Message Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


   // LinkedList<Contact> contactList = new LinkedList<>();

    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    private void getContactList() {
        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null) {
            HashSet<String> mobileNoSet = new HashSet<String>();
            try {
                final int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

                final int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name, number;
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex);
                    number = cursor.getString(numberIndex);
                    number = number.replace(" ", "");
                    if (!mobileNoSet.contains(number)) {
                        //Name,Nickname,Circle,Phonenum,initials Integer.parseInt
                        contacts.add(new Contact(name,name,"3",number));
                        mobileNoSet.add(number);
                        Log.d("hvy", "onCreaterrView  Phone Number: name = " + name
                                + " No = " + number);
                    }
                }
            } finally {
                cursor.close();
            }
}
}

}