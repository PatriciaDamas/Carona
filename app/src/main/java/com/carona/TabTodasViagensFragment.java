package com.carona;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.carona.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrícia on 29/03/2018.
 */

public class TabTodasViagensFragment extends Fragment {

    private ArrayList<Viagem> viagens;

    private RecyclerView rv;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final String TAG = "Tab1";
    private Button btnTeste;
    User userLogged;


    public static ArrayList<String> vKeys;

    public TabTodasViagensFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todas_viagens,container,false);

        Log.d("Carona", "inicio");
        //btnTeste = (Button) view.findViewById(R.id.btnTodasViagens);
        rv = (RecyclerView) view.findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        viagens = new ArrayList<Viagem>();
        vKeys = new ArrayList<String>();



        //List<String> passageiros = new ArrayList<>();
        //passageiros.add("aaabbbbnnnn");
        //Viagem v1 = new Viagem("07/04/2018", "Vila do Conde", "Lisboa", "2,50€","11:30",true, 2, 1,passageiros);
        //Viagem v2 = new Viagem("19/04/2018", "Vila do Conde", "Porto", "1,50€","12:30",true, 4, 2,passageiros);

        //viagens.add(v1);
        //viagens.add(v2);
        final MyAdapterRecycler viagemAdapter= new MyAdapterRecycler(viagens);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(viagemAdapter);
        Log.d("Carona","fim");

        DatabaseReference DataBasedados;

        DataBasedados = FirebaseDatabase.getInstance().getReference("Viagens");

        DataBasedados.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                viagens.clear();
                vKeys.clear();


                for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                    Viagem viagem = dadosnapshot.getValue(Viagem.class);
                    viagens.add(viagem);
                    vKeys.add(dadosnapshot.getKey());


                    Log.d("Carona", Integer.toString(viagens.size())+" tamanho do array viagens");





                }

                viagemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



            return view;





    }
}
