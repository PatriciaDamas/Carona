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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrícia on 30/03/2018.
 */

public class TabMinhasViagensFragment extends Fragment {

    private ArrayList<Viagem> viagens;
    private List<String>passageiros;
    private RecyclerView rv;
    private static final String TAG = "TabMinhasViagens";
    private Button btnTeste;

    User userLogged;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minhas_viagens,container,false);

        rv = (RecyclerView) view.findViewById(R.id.recyclerViagensProgramadas);
        rv.setHasFixedSize(true);

        viagens = new ArrayList<Viagem>();
        passageiros = new ArrayList<String>();
        //verificar user logado

        userLogged =  HomeActivity.userLogged;

        final AdapterRecyclerViagensProgramadas viagemAdapter= new AdapterRecyclerViagensProgramadas(viagens);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(viagemAdapter);
        DatabaseReference DataBasedados;

        DataBasedados = FirebaseDatabase.getInstance().getReference("Viagens");

        DataBasedados.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                viagens.clear();


                for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                    Viagem viagem = dadosnapshot.getValue(Viagem.class);
                    passageiros =viagem.getPassageiros();
                    Log.d("Passageiros", passageiros.get(0).toString());



                    if(userLogged.getEmail().equals(viagem.getEmail())){
                        viagens.add(viagem);
                   }

                    for(int i = 0; i<passageiros.size();i++){

                        if(userLogged.getEmail().equals(passageiros.get(i).toString())){
                            viagens.add(viagem);
                        }

                    }





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
