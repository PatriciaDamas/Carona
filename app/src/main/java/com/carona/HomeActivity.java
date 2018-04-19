package com.carona;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.carona.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    //Firebase
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private ArrayList<User> users;
    public static User userLogged;
    public Boolean flag;
    TextView userLogin;

    User userCriar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        users = new ArrayList<User>();

        final DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference("Users");
        final String userUID = mAuth.getCurrentUser().getUid();

        userLogin=findViewById(R.id.txtUser);


        //criar user na base de dados
       /*database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userUID).exists()){
                    Log.d("carona","existe");



            }else {

                Carro carro = new Carro("","","","","") ;
                userCriar = new User ("",user.getEmail(), "", "", "", "", "",carro, 0);

                    database.child(userUID.toString()).setValue(userCriar);
                Log.d("Carona", "a criar");
            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */

        // codigo para todos os dados da base de dados
        final DatabaseReference DataBasedados;

        DataBasedados = FirebaseDatabase.getInstance().getReference("Users");

        DataBasedados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();

                flag = false;
                for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                    User userD = dadosnapshot.getValue(User.class);
                    users.add(userD);


                    if(user.getEmail().equals(userD.getEmail())){

                        Log.d("Carona", "user existente");

                        userLogged = userD;

                        userLogin.setText("Bem-vindo " + userLogged.getEmail());
                        flag = true;

                    }



                }
                if(flag==false){

                    Carro carro = new Carro("","","","","") ;
                    userCriar = new User ("","",user.getEmail(), "", "", "", "", "",carro, 0);

                    DataBasedados.child(user.getUid().toString()).setValue(userCriar);
                    Log.d("Carona", "a criar");
                    userLogin.setText("Bem-vindo " + user.getEmail());
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){

            //Extrair os dados do bundle da Intent
            String username = extras.getString("username");
            Log.d("Carona",username);


        }





        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
                {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        //mTextMessage.setText(R.string.title_home);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);
                        Intent intent1 = new Intent(HomeActivity.this, ProcurarViagemActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.adicionarviagem:
                        Intent intent2 = new Intent(HomeActivity.this, CriarViagemActivity.class);
                        startActivity(intent2);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.perfil:
                        Intent intent3 = new Intent(HomeActivity.this, PerfilActivity.class);
                        startActivity(intent3);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.definicoes:
                        Intent intent4 = new Intent(HomeActivity.this, DefActivity.class);
                        startActivity(intent4);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });


    }
     public void clickViagem (View view){


         Intent intent5 = new Intent(HomeActivity.this, CriarViagemActivity.class);
         startActivity(intent5);

     }

     public void clickProcurarViagem (View view){

         Intent intent6 = new Intent(HomeActivity.this, ProcurarViagemActivity.class);
         startActivity(intent6);
     }



}
