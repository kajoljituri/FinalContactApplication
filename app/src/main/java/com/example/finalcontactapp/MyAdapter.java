package com.example.finalcontactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.NameDisplay.setText(contact.getName());
        holder.PhonenumDisplay.setText(contact.getPhoneNumber());


    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        TextView NameDisplay,PhonenumDisplay;


        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            NameDisplay = itemView.findViewById(R.id.namedisplay);
            PhonenumDisplay=itemView.findViewById(R.id.phonenum);

        }
    }
}
