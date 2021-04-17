package com.example.wazappbd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class maolana_adapter extends RecyclerView.Adapter<maolana_adapter.Mahfilclass>{
    private ArrayList<maolana_mahfil> mExampleList;
    private ArrayList<maolana_mahfil> ExampleListfull;


    public static class Mahfilclass extends RecyclerView.ViewHolder {

        public TextView textview1;
        public TextView textview2,textview3;


        public Mahfilclass(View itemView) {
            super(itemView);
            textview1 = itemView.findViewById(R.id.mahfiltype);
            textview2 = itemView.findViewById(R.id.mahfilplace);
            textview3=itemView.findViewById(R.id.mahfildate);
        }
    }

    public maolana_adapter(ArrayList<maolana_mahfil> exampleList) {
        mExampleList = exampleList;
        ExampleListfull = new ArrayList<>(exampleList);
    }

    @Override
    public Mahfilclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mahfil_list, parent, false);
        Mahfilclass evh = new Mahfilclass(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(Mahfilclass holder, int position) {
        maolana_mahfil currentItem = mExampleList.get(position);

        holder.textview1.setText(currentItem.getMahfiltype());
        // movieList.add(currentItem.getText1());
        //currentItem.
        holder.textview2.setText(currentItem.getMahfilplace());
        holder.textview3.setText(currentItem.getMahfildate());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
