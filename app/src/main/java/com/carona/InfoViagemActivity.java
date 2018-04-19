package com.carona;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InfoViagemActivity extends AppCompatActivity {

    private TextView localPartida, localChegada, preco, hora, data, lugaresLivres, totalLugares, txtCondutor, cor, matricula, marca;
    public ImageView imageFace;
    private User condutorViagem;
    private ArrayList<User> users;
    private ArrayList<Viagem> viagens;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String email_confirm="", lugaresDisponiveis = "",hora_confirm="", data_confirm="";
    private List<String>passageiros = new ArrayList<String>();
    private FirebaseAuth mAuth;
    User userLogged;
    Boolean check;
    public String vKey;
    private boolean condutor=false;
    private boolean bPassageiro=false;
    public String image;
    public String contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_viagem);

        check=false;
        users = new ArrayList<User>();
        viagens = new ArrayList<Viagem>();

        //Definir conteudo do layout infoViagem

        localPartida = findViewById(R.id.txtLocalSaida);
        localChegada = findViewById(R.id.txtLocalChegada);
        preco = findViewById(R.id.txtPrecoViagemInfo);
        hora = findViewById(R.id.txtHoraSaida);
        data = findViewById(R.id.txtDataSaida);
        lugaresLivres = findViewById(R.id.txtLugVeiculos);
        totalLugares = findViewById(R.id.txtLotacao);
        txtCondutor = findViewById(R.id.txtNomeCondutor);
        cor = findViewById(R.id.txtCorVeiculo);
        matricula = findViewById(R.id.txtMatVeiculo);
        marca = findViewById(R.id.txtMarcaModelo);
        imageFace=findViewById(R.id.imgInfoViagem);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String chegada = extras.getString("chegada");
            String partida = extras.getString("partida");
            String precoViagem = extras.getString("preco");
            String dataViagem = extras.getString("data");
            String horaViagem = extras.getString("hora");
            String lugaresCarro = extras.getString("lugaresCarro");
            String lugaresLivresCarro = extras.getString("lugaresLivre");
            final String condutor = extras.getString("condutor");
            final String email = extras.getString("email");
            vKey = extras.getString("key");


            email_confirm = email;
            lugaresDisponiveis = lugaresLivresCarro;
            data_confirm = dataViagem;
            hora_confirm = horaViagem;


            //condutor = test2;
            localPartida.setText(partida);
            localChegada.setText(chegada);
            preco.setText(precoViagem);
            data.setText(dataViagem);
            hora.setText(horaViagem);
            lugaresLivres.setText(lugaresLivresCarro);
            totalLugares.setText(lugaresCarro);
            txtCondutor.setText(condutor);



            Log.d("Carona",condutor + "nomeCondutor");

            // codigo que vais buscar detalhes da viagem, como dados do veiculo do condutor
            final DatabaseReference DataBasedados;

            DataBasedados = FirebaseDatabase.getInstance().getReference("Users");

            DataBasedados.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    users.clear();

                    for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                        User userD = dadosnapshot.getValue(User.class);
                        users.add(userD);


                        //Mostra os dados do carro do utilizador que criou a viagem
                        if(email.equals(userD.getEmail())){
                            //condutor = userD;
                            cor.setText(userD.getCarro().getCor());
                            matricula.setText(userD.getCarro().getMatricula());
                            marca.setText(userD.getCarro().getMarca_veiculo()+ ", " + userD.getCarro().getModelo());
                            contato = userD.getTelemovel();
                            if(!imageFace.equals("")){
                                image = userD.getUrlImg();
                                Log.d("Carona", image);

                                Glide.with(getApplication()).load(image).into(imageFace);



                            }

                            Log.d("Carona", userD.getTelemovel());
                        }



                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


        









        //Bottom Navigation

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent5 = new Intent(InfoViagemActivity.this, HomeActivity.class);
                        startActivity(intent5);
                        //mTextMessage.setText(R.string.title_home);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);
                        Intent intent1 = new Intent(InfoViagemActivity.this, ProcurarViagemActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.adicionarviagem:
                        Intent intent2 = new Intent(InfoViagemActivity.this, CriarViagemActivity.class);
                        startActivity(intent2);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.perfil:
                        Intent intent3 = new Intent(InfoViagemActivity.this, PerfilActivity.class);
                        startActivity(intent3);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.definicoes:
                        Intent intent4 = new Intent(InfoViagemActivity.this, DefActivity.class);
                        startActivity(intent4);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });


    }


    public void clickReservarViagem (View view){


        final DatabaseReference myData = database.getReference("Viagens");

        userLogged = HomeActivity.userLogged;
        passageiros.clear();





        myData.child(vKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("Passageiros","entrei");

                Viagem viagem = dataSnapshot.getValue(Viagem.class);
                passageiros =viagem.getPassageiros();


                if(email_confirm.equals(userLogged.getEmail())){

                    condutor=true;

                    //TODO Toast
                    Toast.makeText(InfoViagemActivity.this, "Esta viagem foi registada por si",
                            Toast.LENGTH_LONG).show();


                }

                if(passageiros.get(0).equals("null")){
                    passageiros.clear();

                }

                for (int i = 0; i < passageiros.size(); i++){
                    if(passageiros.get(i).equals(userLogged.getEmail())){
                        bPassageiro = true;
                        //TODO Toast
                        Toast.makeText(InfoViagemActivity.this, "Já se registou nesta viagem",
                                Toast.LENGTH_LONG).show();
                    }
                }

                if(Integer.parseInt(lugaresDisponiveis) > 0 && (!condutor) && (!bPassageiro)){


                    passageiros.add(userLogged.getEmail());
                    myData.child(vKey).child("passageiros").setValue(passageiros);
                    Toast.makeText(InfoViagemActivity.this, "Viagem registada! Consulte o seu Perfil.",
                            Toast.LENGTH_LONG).show();

                }



                Log.d("Passageiros","finish");

            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });

    }
    public void clickLigar (View view){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + contato));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




}
