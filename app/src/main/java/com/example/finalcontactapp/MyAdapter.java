package com.example.finalcontactapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewAdapter> {

    Context context;
    ArrayList<Contact> contactlist;

    public MyAdapter(Context context, ArrayList<Contact> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_view,parent,false);
        return new MyViewAdapter(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewAdapter holder, int position) {
        Contact contact=contactlist.get(position);
        //holder.imageView.setImageResource(contact.getPhoto());
        holder.NameDisplay.setText(contact.getName());
        holder.CircleDisplay.setText(String.valueOf(contact.getCircle()));
        String initials= String.valueOf(contact.getName().charAt(0));
        holder.Initials.setText(initials.toUpperCase());

        //holder.Editbtn = holder.itemView.findViewById(R.id.editbtn);
        // Assuming imageList is an ArrayList or an array of image resource IDs

//        int imageResource = (int) imageList.get(position); // Replace 'position' with the appropriate index
//        holder.Editbtn.setImageResource(imageResource);

        holder.NameDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ContactProfile.class);
                //intent.putExtra("imageview",contact.getPhoto());
                String initials= String.valueOf(contact.getName().charAt(0));

                intent.putExtra("name",contact.getName());
                intent.putExtra("phno",contact.getPhoneNumber());
                //intent.putExtra("Initial",initials);
                intent.putExtra("circle",contact.getCircle());
                intent.putExtra("nickname",contact.getnickname());
               // intent.putExtra("id",contact.getId());

                view.getContext().startActivity(intent);

            }
        });
        holder.NameDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(view.getContext(),UpdateContact.class);
                //intent.putExtra("imageview",contact.getPhoto());
                //String initials= String.valueOf(contact.getName().charAt(0));
                updateIntent.putExtra("name",contact.getName());
                updateIntent.putExtra("phno",contact.getPhoneNumber());
                //intent.putExtra("Initial",initials);
                updateIntent.putExtra("circle",contact.getCircle());
                updateIntent.putExtra("nickname",contact.getnickname());
                updateIntent.putExtra("id",contact.getId());

                view.getContext().startActivity(updateIntent);

            }
        });


    }
//    int positionToUpdate = Contact.class.getModifiers();/* the position of the item to update */;
//    Contact itemToUpdate = contactlist.get(positionToUpdate);
//    itemToUpdate.setPropertyToUpdate(newValue);
//
//   MyAdapter.notifyItemChanged(positionToUpdate);



    @Override
    public int getItemCount() {

        return contactlist.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        TextView NameDisplay,CircleDisplay,Initials;
        //RelativeLayout NewContactPg;


        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            NameDisplay = itemView.findViewById(R.id.namedisplay);
            CircleDisplay=itemView.findViewById(R.id.circle);
            Initials=itemView.findViewById(R.id.initials);
            //NewContactPg =itemView.findViewById(R.id.newContactPg);






        }
    }
}
