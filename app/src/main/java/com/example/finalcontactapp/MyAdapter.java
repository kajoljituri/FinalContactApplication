package com.example.finalcontactapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.PhonenumDisplay.setText(String.valueOf(contact.getPhoneNumber()));
        if (holder.Circle != null) {
            holder.Circle.setText(contact.getCircle());
        }




        holder.NameDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ContactProfile.class);
                //intent.putExtra("imageview",contact.getPhoto());
                intent.putExtra("name",contact.getName());
                intent.putExtra("phno",contact.getPhoneNumber());

                intent.putExtra("circle",contact.getCircle());
                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return contactlist.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        TextView NameDisplay,PhonenumDisplay,Circle;
        //ImageView imageView;


        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            NameDisplay = itemView.findViewById(R.id.namedisplay);
            PhonenumDisplay=itemView.findViewById(R.id.phonenum);
            Circle=itemView.findViewById(R.id.circle);
            //imageView=itemView.findViewById(R.id.image);



        }
    }
}
