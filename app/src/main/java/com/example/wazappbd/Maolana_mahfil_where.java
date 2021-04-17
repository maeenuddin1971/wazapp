package com.example.wazappbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.wazappbd.ui.home.mahfiladapter;
import com.example.wazappbd.ui.home.maolana;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Maolana_mahfil_where extends AppCompatActivity {
    private RecyclerView mRecycle;
    private RecyclerView.LayoutManager mLayoutManager;
    private maolana_adapter mAdapter;
    public ArrayList<maolana_mahfil> exampleList = new ArrayList<>();

    ImageView hf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maolana_mahfil_where);

        Intent intent = getIntent();
        hf=findViewById(R.id.imge);
        String maolana_name = intent.getStringExtra("maolana_name");

        Log.d("masp",maolana_name);

        String imagelink=intent.getStringExtra("imagelink");
        //Log.d("mollah",imagelink);

        //image=findViewById(R.id.imge);
        //  Log.d("amiim",pic);*/
        Picasso.get().load(imagelink).into(hf);
        String uniqid=intent.getStringExtra("uniqid");
        Log.d("pxa",uniqid);

        exampleList.add(new maolana_mahfil("maeen","uddin","mollah"));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("karim");
        Log.d("rifat","mmm");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // list.clear();

                String mahfiltype = "", mahfildate = "", mahfilplace = "";
                List<String> mtype=new ArrayList<>();
                List<String>mdate=new ArrayList<>();
                List<String>mplace=new ArrayList<>();
                String un="";
                Log.d("rifat",un);
                int p = 1;
                int z = 0;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    //list.add(snapshot.getValue().toString());

                    un= (String) snapshot.child(String.valueOf(z)).child("nid").getValue().toString();

                    if(un.equals(uniqid)) {
                        mahfildate = (String) snapshot.child(String.valueOf(z)).child("mahfildate").getValue();
                        mahfiltype = (String) snapshot.child(String.valueOf(z)).child("mahfiltype").getValue();
                        mahfilplace = (String) snapshot.child(String.valueOf(z)).child("mahfilplace").getValue();
                        mtype.add(mahfiltype);
                        mdate.add(mahfildate);
                        mplace.add(mahfilplace);
                    }
                    z++;
                    //Log.d("ekram2", snapshot.getValue().toString());
                    p++;
                }

                //exampleList.add(new maolana("uddin", "mollah", "maeen"));


                for(int ib=0;ib<mtype.size();ib++)
                {
                    exampleList.add(new maolana_mahfil(mdate.get(ib),mtype.get(ib),mplace.get(ib)));
                }

                mRecycle = findViewById(R.id.mahfilrecycle);
                mRecycle.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(Maolana_mahfil_where.this);
                mAdapter = new maolana_adapter(exampleList);

                mRecycle.setLayoutManager(mLayoutManager);
                mRecycle.setAdapter(mAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }




}
