package com.carona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.carona.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Patrícia on 30/03/2018.
 */

public class TabPerfilFragment extends Fragment {


    private ArrayList<User> users;
    private FirebaseAuth mAuth;
    FirebaseUser userD;
    TextView nome, faculdade, curso, email, telefone, marca, modelo, cor, matricula;
    ImageView imageFace;
    String image;

    User userLogged;


    private static final String TAG = "TabPerfil";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmente_perfil,container,false);


        mAuth = FirebaseAuth.getInstance();
        userD = mAuth.getCurrentUser();

        nome=view.findViewById(R.id.txtNomeCondutor);
        curso=view.findViewById(R.id.txtCurso);
        faculdade=view.findViewById(R.id.txtFaculdade);
        email=view.findViewById(R.id.txtEmailPerfil);
        telefone=view.findViewById(R.id.txtTelefone);
        marca=view.findViewById(R.id.txtMarca);
        modelo=view.findViewById(R.id.txtModelo);
        cor=view.findViewById(R.id.txtCor);
        matricula=view.findViewById(R.id.txtMatricula);
        imageFace=view.findViewById(R.id.imgFace);




        //Puxar os dados do utilizador para a frame Perfil (Perfil)
        userLogged = HomeActivity.userLogged;
        nome.setText(userLogged.getNome());
        curso.setText(userLogged.getCurso());
        faculdade.setText(userLogged.getEstabelecimento());
        email.setText(userLogged.getEmail());
        telefone.setText(userLogged.getTelemovel());
        marca.setText(userLogged.getCarro().getMarca_veiculo());
        modelo.setText(userLogged.getCarro().getModelo());
        cor.setText(userLogged.getCarro().getCor());
        matricula.setText(userLogged.getCarro().getMatricula());



        image = userLogged.getUrlImg();
        Glide.with(this).load(image).into(imageFace);

        /*image = userLogged.getUrlImg();
        Glide.with(this).load(image).apply(bitmapTransform(new CircleCrop())).into(imageFace);


        /*DatabaseReference DataBasedados;

        DataBasedados = FirebaseDatabase.getInstance().getReference("Users");

        DataBasedados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();

                for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                    User user = dadosnapshot.getValue(User.class);
                    users.add(user);
                    Log.d("Carona", Integer.toString(users.size())+"tamanho do array user");

                    if(user.getEmail().equals(userD.getEmail())){

                        Log.d("Carona", "user existente");



                    }


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        return view;
    }





}
