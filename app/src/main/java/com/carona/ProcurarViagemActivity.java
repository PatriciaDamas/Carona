package com.carona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class ProcurarViagemActivity extends AppCompatActivity {

    private static final String TAG = "ProcurarViagem";

    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager mViewPager;

    private ArrayList<Viagem> viagens;
    private MyAdapterRecycler viagensAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procurar_viagem);





        //Definir TabBar
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPage(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        //Definir bottomNavigationBar
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

                        Intent intent0 = new Intent(ProcurarViagemActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);

                        break;
                    case R.id.adicionarviagem:
                        Intent intent2 = new Intent(ProcurarViagemActivity.this, CriarViagemActivity.class);
                        startActivity(intent2);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.perfil:
                        Intent intent3 = new Intent(ProcurarViagemActivity.this, PerfilActivity.class);
                        startActivity(intent3);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;
                    case R.id.definicoes:
                        Intent intent4 = new Intent(ProcurarViagemActivity.this, DefActivity.class);
                        startActivity(intent4);
                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });
    }

    public void clickObterInfo (View view){

        //chamar intent login

        Intent i =new Intent(getApplicationContext(),InfoViagemActivity.class);
        startActivity(i);

    }

    public void clickVoltar (View view){
        Intent i =new Intent(getApplicationContext(),ProcurarViagemActivity.class);
        startActivity(i);
    }


    private void setupViewPage(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabTodasViagensFragment(), "Todas as Boleias");
        adapter.addFragment(new TabProcurarViagensFragment(), "Procurar Boleia");
        viewPager.setAdapter(adapter);


    }
}
