package com.example.wazappbd.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wazappbd.MainActivity;
import com.example.wazappbd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public ArrayList<String> list=new ArrayList<>();

    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private mahfiladapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<maolana> exampleList = new ArrayList<>();
    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");


        //Log.d("ekram8",list.toString());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d("ekram5","this is ");
        List<String> picl=new ArrayList<>();
        List<String> namel=new ArrayList<>();
        List<String> uniql=new ArrayList<>();
        DatabaseReference ref = database.getReference("rahim");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // list.clear();
                Log.d("ekram4",snapshot.getChildren().toString());
                String pic="",name="",uniqid="";
                int p=1;
                int z=0;
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                      list.add(snapshot.getValue().toString());
                      pic = (String) snapshot.child(String.valueOf(z)).child("pic").getValue();
                      name = (String) snapshot.child(String.valueOf(z)).child("name").getValue();
                      uniqid = (String) snapshot.child(String.valueOf(z)).child("uniqid").getValue();
                      picl.add(pic);
                      namel.add(name);
                      uniql.add(uniqid);
                      z++;
                      //Log.d("ekram2", snapshot.getValue().toString());
                      p++;
                }
                for(int ip=0;ip<picl.size();ip++) {
                    exampleList.add(new maolana(picl.get(ip), namel.get(ip),uniql.get(ip) ));
                }
                //exampleList.add(new maolana("uddin", "mollah", "maeen"));
                dialog.cancel();
                mRecyclerView = root.findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mAdapter = new mahfiladapter(exampleList);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("ekram",list.toString());


       // textView.setText(ref.toString());
// Attach a listener to read the data at our posts reference






        return root;
    }


    

}

