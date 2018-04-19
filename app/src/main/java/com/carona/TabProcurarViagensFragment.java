package com.carona;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.carona.R;

import java.util.Calendar;

/**
 * Created by Patrícia on 29/03/2018.
 */

public class TabProcurarViagensFragment extends Fragment {

    private static final String TAG = "Tab2";
    private TextView editData, editHora;
    private DatePickerDialog.OnDateSetListener mdataPartida;
    private TimePickerDialog.OnTimeSetListener mHoraPartida;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procurar_viagem,container,false);

        editData = view.findViewById(R.id.txtDataProcurar);
        editHora = view.findViewById(R.id.txtHoraProcurar);

        //Criar pop-up da data
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog popupDia
                        = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdataPartida,ano,mes,dia);

                popupDia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                popupDia.show();
            }
        });

        mdataPartida = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia){
                mes = mes + 1;
                String dataPopUp = dia + "/" + mes + "/" + ano;

                editData.setText(dataPopUp);
            }

        };


        //Criar a pop up hora da viagem
        editHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cl = Calendar.getInstance();
                int hora = cl.get(Calendar.HOUR);
                int minutos = cl.get(Calendar.MINUTE);

                TimePickerDialog popupHora = new TimePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mHoraPartida,hora,minutos, DateFormat.is24HourFormat(getContext()));
                popupHora.show();
            }
        });
        mHoraPartida = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int minuto) {

                String horaPopUp = hora + ":" + minuto;
                editHora.setText(horaPopUp);
            }
        };


        return view;
    }
}