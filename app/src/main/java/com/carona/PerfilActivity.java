package com.carona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class PerfilActivity extends AppCompatActivity {


    //Firebase
    private FirebaseAuth mAuth;
    FirebaseUser userD;
    private ArrayList<User> users;
    TextView nome;


    private static final String TAG = "Perfil";

    private PerfilAdapter perfilAdapter;
    private ViewPager mViewPagerPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();
        userD = mAuth.getCurrentUser();




       //nome=findViewById(R.id.txtNomeCondutor);
        //nome.setText("ola");

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



        //Definir TabBar
        perfilAdapter = new PerfilAdapter(getSupportFragmentManager());
        mViewPagerPerfil = (ViewPager) findViewById(R.id.containerPerfil);
        setupViewPage(mViewPagerPerfil);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsPerfil);
        tabLayout.setupWithViewPager(mViewPagerPerfil);

        //Definir bottomNavigationBar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        Intent intent0 = new Intent(PerfilActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);
                        Intent intent3 = new Intent(PerfilActivity.this, ProcurarViagemActivity.class);
                        startActivity(intent3);

                        break;
                    case R.id.adicionarviagem:
                        Intent intent2 = new Intent(PerfilActivity.this, CriarViagemActivity.class);
                        startActivity(intent2);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.perfil:

                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.definicoes:
                        Intent intent4 = new Intent(PerfilActivity.this, DefActivity.class);
                        startActivity(intent4);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });


    }

    public void Logout (View view){


        //logout
        mAuth.signOut();
        //mAuth.getCurrentUser().getUid();

        //chamar intent login

        Intent i =new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);

    }


    private void setupViewPage(ViewPager viewPager){
        PerfilAdapter adapter = new PerfilAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabMinhasViagensFragment(), "Minhas Viagens");
        adapter.addFragment(new TabPerfilFragment(), "Perfil");
        viewPager.setAdapter(adapter);


    }





}
