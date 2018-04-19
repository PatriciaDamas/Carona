package com.carona;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.carona.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CriarViagemActivity extends AppCompatActivity {

    User userLogged;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private EditText editPartida, editChegada, editData, editHora,editPreco;
    TextView editLugaresLivres;
    private String partida,chegada,data,hora,preco;
    private SeekBar seekBarLugares;
    private DatePickerDialog.OnDateSetListener mdataPartida;
    private TimePickerDialog.OnTimeSetListener mHoraPartida;
    int lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_viagem);


        userLogged = HomeActivity.userLogged;

        seekBarLugares = (SeekBar) findViewById(R.id.seekbarLugaresLivres);
        editLugaresLivres = findViewById(R.id.txtlugaresCriarViagem);
        editPartida = findViewById(R.id.txtPartida);
        editChegada = findViewById(R.id.txtChegada);
        editData = findViewById(R.id.txtData);
        editHora =  findViewById(R.id.txtHora);
        editPreco = findViewById(R.id.txtPrecoCriarViagem);


        //Passar da Seekbar para a textview
        seekBarLugares.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editLugaresLivres.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Criar pop-up da data
        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog popupDia
                        = new DatePickerDialog(CriarViagemActivity.this,
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

                TimePickerDialog popupHora = new TimePickerDialog(CriarViagemActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mHoraPartida,hora,minutos, DateFormat.is24HourFormat(getApplication()));
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        Intent intent0 = new Intent(CriarViagemActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);
                        Intent intent1 = new Intent(CriarViagemActivity.this, ProcurarViagemActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.adicionarviagem:
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.perfil:
                        Intent intent3 = new Intent(CriarViagemActivity.this, PerfilActivity.class);
                        startActivity(intent3);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.definicoes:
                        Intent intent4 = new Intent(CriarViagemActivity.this, DefActivity.class);
                        startActivity(intent4);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });
    }

    //validar formulário do veiculo
    private boolean validateFormCriarViagem(){

        boolean valid =true;


        String partida = editPartida.getText().toString();
        String chegada = editChegada.getText().toString();
        String data = editData.getText().toString();
        String hora = editHora.getText().toString();
        String preco = editPreco.getText().toString();
        String lugares = editLugaresLivres.getText().toString();

        if(TextUtils.isEmpty(partida)){
            editPartida.setError("Preencha");
            valid=false;
        }else {
            editPartida.setError(null);
        }

        if(TextUtils.isEmpty(chegada)){
            editChegada.setError("Preencha");
            valid=false;
        }else {
            editChegada.setError(null);
        }

        if(TextUtils.isEmpty(data)){
            editData.setError("Preencha");
            valid=false;
        }else {
            editData.setError(null);
        }

        if(TextUtils.isEmpty(hora)){
            editHora.setError("Preencha");
            valid=false;
        }else {
            editHora.setError(null);
        }

        if(TextUtils.isEmpty(preco)){
            editPreco.setError("Preencha");
            valid=false;
        }else {
            editPreco.setError(null);
        }

        if(lugares.equals("0")){
            editLugaresLivres.setError("Preencha");
            valid=false;
        }else {
            editLugaresLivres.setError(null);
        }


        return valid;
    }

    public void clickCriarViagem (View view){

        DatabaseReference myRef = database.getReference("Viagens");

        if(!validateFormCriarViagem()){
            return;
        }
        partida = editPartida.getText().toString();
        chegada = editChegada.getText().toString();
        hora = editHora.getText().toString();
        data = editData.getText().toString();
        lugares = Integer.parseInt(editLugaresLivres.getText().toString());
        preco = editPreco.getText().toString();







        Boolean estadoViagem= true;
        int lugaresCarro= Integer.parseInt(userLogged.getCarro().getLugares());

        List<String> passageiros = new ArrayList<>();

        passageiros.add("null");
        //passageiros.add("jkmlhykgmyhçghyh");


        Viagem viagem = new Viagem( userLogged.getEmail(),userLogged.getUrlImg(),userLogged.getNome(), data,  partida,  chegada,  preco,  hora,  estadoViagem, lugaresCarro, lugares, passageiros);

        String viagemID = myRef.push().getKey();
        myRef.child(viagemID).setValue(viagem);

        Toast.makeText(CriarViagemActivity.this, "Viagem criada, ver perfil",
                Toast.LENGTH_LONG).show();

        finish();

    }
}
