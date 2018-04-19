package com.carona;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Patrícia on 15/04/2018.
 */

public class AdapterRecyclerViagensProgramadas extends RecyclerView.Adapter<AdapterRecyclerViagensProgramadas.ViewHolder> {

    private ArrayList<Viagem> myViagemRegistadaList;
    private Context context;

    public AdapterRecyclerViagensProgramadas(ArrayList<Viagem> myViagemRegistadaList){
        this.myViagemRegistadaList = myViagemRegistadaList;

    }

    @Override
    public AdapterRecyclerViagensProgramadas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.minhasviagens_cardview,parent,false);
        return new AdapterRecyclerViagensProgramadas.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViagensProgramadas.ViewHolder holder, final int position) {

        Viagem viagem = myViagemRegistadaList.get(position);
        holder.dia.setText(viagem.getData());
        holder.horaPartida.setText(viagem.getHora());
        holder.localChegada.setText(viagem.getLocalChegada());
        holder.localPartida.setText(viagem.getLocalPartida());
        holder.preco.setText(viagem.getPreco() + "€");
        holder.lugaresLivres.setText(String.valueOf(viagem.getLugaresDisponiveis()) + " livres");
        holder.totalLugares.setText(String.valueOf(viagem.getLugaresCarro()) + " lugares");




        if(viagem.getPassageiros().get(0).equals("null")){

            TextView textView = new TextView(context);
            textView.setText("Ainda não tem reservas na sua viagem");
            textView.setTextColor(Color.rgb(2,52,54));
            textView.setTextSize(14);
            textView.setPadding(20,5,10,5);

            holder.linear.addView(textView);
        }
        else{
            for(int i = 0; i<viagem.getPassageiros().size(); i++){
                //holder.passageiro.setText(viagem.getPassageiros().get(i));
                TextView textView = new TextView(context);
                textView.setText(viagem.getPassageiros().get(i));
                textView.setTextColor(Color.rgb(2,52,54));
                textView.setTextSize(14);
                textView.setPadding(30,5,10,5);
                holder.linear.addView(textView);
            }
        }



    }

    @Override
    public int getItemCount() {
        return myViagemRegistadaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView dia, horaPartida, localPartida, localChegada, preco, lugaresLivres, totalLugares, condutor;
        protected LinearLayout linear ;

        public ViewHolder(View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.txtDataMiinhasViagens);
            horaPartida = itemView.findViewById(R.id.txtHoraMinhasViagens);
            localPartida = itemView.findViewById(R.id.txtPartidaMinhasViagens);
            localChegada = itemView.findViewById(R.id.txtChegadaMinhasViagens);
            preco = itemView.findViewById(R.id.txtPrecoMinhasViagens);
            lugaresLivres = itemView.findViewById(R.id.txtLugViagemReservada);
            totalLugares = itemView.findViewById(R.id.txtTotalLugViagem);

            linear = itemView.findViewById(R.id.linearPassageiros);

        }
    }
}
