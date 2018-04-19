package com.carona;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Patrícia on 07/04/2018.
 */

public class MyAdapterRecycler  extends RecyclerView.Adapter<MyAdapterRecycler.ViewHolder> {


     private ArrayList<Viagem> myViagemList;
        public ArrayList<String> vKeys=TabTodasViagensFragment.vKeys;

     private Context context;


    public MyAdapterRecycler(ArrayList<Viagem> myViagemList){
        this.myViagemList = myViagemList;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viagem_cardview,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapterRecycler.ViewHolder holder, final int position) {

        Viagem viagem = myViagemList.get(position);
        holder.dia.setText(viagem.getData());
        holder.horaPartida.setText(viagem.getHora());
        holder.localChegada.setText(viagem.getLocalChegada());
        holder.localPartida.setText(viagem.getLocalPartida());
        holder.preco.setText(viagem.getPreco() + "€");
        holder.condutor.setText(viagem.getCondutor());
        holder.lugaresLivres.setText(String.valueOf(viagem.getLugaresDisponiveis()) + " livres");
        holder.totalLugares.setText(String.valueOf(viagem.getLugaresCarro()) + " lugares");
        String image = viagem.getImagem();
        Glide.with(context).load(image).into(holder.imageFace);

        holder.btnInfoViagens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoViagemActivity.class);
                intent.putExtra("partida", myViagemList.get(position).getLocalPartida());
                intent.putExtra("chegada", myViagemList.get(position).getLocalChegada());
                intent.putExtra("preco", myViagemList.get(position).getPreco() + "€");
                intent.putExtra("hora", myViagemList.get(position).getHora());
                intent.putExtra("data", myViagemList.get(position).getData());
                intent.putExtra("condutor", myViagemList.get(position).getCondutor());
                intent.putExtra("lugaresCarro", String.valueOf(myViagemList.get(position).getLugaresCarro()) + " lugares");
                intent.putExtra("lugaresLivre", String.valueOf(myViagemList.get(position).getLugaresDisponiveis()));
                intent.putExtra("email",String.valueOf(myViagemList.get(position).getEmail()));
                intent.putExtra("key", vKeys.get(position).toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myViagemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView dia, horaPartida, localPartida, localChegada, preco, lugaresLivres, totalLugares, condutor;
        protected Button btnInfoViagens;
        protected ImageView imageFace;

        public ViewHolder(View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.txtDataViagem);
            horaPartida = itemView.findViewById(R.id.txtHoras);
            localPartida = itemView.findViewById(R.id.txtLocalPartida);
            localChegada = itemView.findViewById(R.id.localChegada);
            preco = itemView.findViewById(R.id.txtPrecoViagem);
            lugaresLivres = itemView.findViewById(R.id.txtLugaresLivres);
            totalLugares = itemView.findViewById(R.id.txtTotalLug);
            condutor = itemView.findViewById(R.id.txtCondutor);
            imageFace = itemView.findViewById(R.id.imgCard);
            //contrução das informações detalhadas das viagens
            btnInfoViagens = itemView.findViewById(R.id.btnObterInfor);

        }
    }
}
