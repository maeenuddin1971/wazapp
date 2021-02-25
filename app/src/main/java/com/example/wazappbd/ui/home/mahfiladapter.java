package com.example.wazappbd.ui.home;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wazappbd.Maolana_mahfil_where;
import com.example.wazappbd.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class mahfiladapter extends RecyclerView.Adapter<mahfiladapter.Mahfilclass>{
    private ArrayList<maolana> mExampleList;
    private ArrayList<maolana> ExampleListfull;


    public static class Mahfilclass extends RecyclerView.ViewHolder {

        public TextView textview1;
        public TextView textview2,textview3;
        public String imagelink;
        public ImageView mImageView;
        public TextView mTextView1;
        public String uniqid;
        public Mahfilclass(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            uniqid="";

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {




                    Intent intent = new Intent(v.getContext(), Maolana_mahfil_where.class);
                    String gh=mTextView1.getText().toString();


                    intent.putExtra("maolana_name",gh);
                    intent.putExtra("imagelink",imagelink);
                    intent.putExtra("uniqid",uniqid);
                    v.getContext().startActivity(intent);
                    Log.d("unqiq",uniqid);
                }
            });




        }
    }

    public mahfiladapter(ArrayList<maolana> exampleList) {
        mExampleList = exampleList;
        ExampleListfull = new ArrayList<>(exampleList);
    }

    @Override
    public Mahfilclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.maolana_list, parent, false);
        Mahfilclass evh = new Mahfilclass(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(Mahfilclass holder, int position) {
        maolana currentItem = mExampleList.get(position);

        Picasso.get().load(currentItem.getImageResource()).into(holder.mImageView);
        holder.mTextView1.setText(currentItem.getText1());
        holder.uniqid=currentItem.getText2();
        holder.imagelink=currentItem.getImageResource();

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
