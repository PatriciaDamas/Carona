package com.carona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carona.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DefActivity extends AppCompatActivity {

    private static final String TAG = "Definicoes";
    EditText  editNome, editEstabelecimento, editCurso, editTelemovel, editNib;
    EditText editMarca,editModelo,editCor,editMatricula;
    TextView editLugares;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    String novoMarca;
    String novoModelo;
    String novoCor;
    String novoMatricula;
    String novoLugares;

    String novoNome;
    String novoEstabelecimento;
    String novoCurso;
    String novoTelemovel;
    String novoNIB;



    Carro vazioCarro;
    //permissao

    //private Boolean permissao=false;


    //Firebase
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private ArrayList<User> users;
    User userD,userFinal;



    private DefAdapter defAdapter;
    private ViewPager mViewPagerDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_def);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        users = new ArrayList<User>();






        /*DatabaseReference DataBasedados;

        DataBasedados = FirebaseDatabase.getInstance().getReference("Users");

        DataBasedados.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();

                for(DataSnapshot dadosnapshot : dataSnapshot.getChildren()){
                    userD = dadosnapshot.getValue(User.class);
                    users.add(userD);
                    Log.d("Carona", Integer.toString(users.size())+userD.getEmail());

                    if(user.getEmail().equals(userD.getEmail())){

                        Log.d("Carona", "entrei");

                        permissao=true;
                        userFinal = userD;
                    }

                }

                // viagemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        //id dos dados pessoias




        //Definir TabBar
        defAdapter = new DefAdapter(getSupportFragmentManager());
        mViewPagerDef = (ViewPager) findViewById(R.id.containerDef);
        setupViewPage(mViewPagerDef);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsDefinicoes);
        tabLayout.setupWithViewPager(mViewPagerDef);

        //Definir bottomNavigationBar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        Intent intent0 = new Intent(DefActivity.this, HomeActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.procurar:
                        //mTextMessage.setText(R.string.title_dashboard);
                        Intent intent1 = new Intent(DefActivity.this, ProcurarViagemActivity.class);
                        startActivity(intent1);

                        break;
                    case R.id.adicionarviagem:
                        //mTextMessage.setText(R.string.title_notifications);
                        Intent intent2 = new Intent(DefActivity.this, CriarViagemActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.perfil:
                        //mTextMessage.setText(R.string.title_notifications);
                        Intent intent3 = new Intent(DefActivity.this, PerfilActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.definicoes:

                        //mTextMessage.setText(R.string.title_notifications);
                        break;

                }
                return false;
            }
        });


    }

    private void setupViewPage(ViewPager viewPager){
        DefAdapter adapter = new DefAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabDadosPessoaisFragment(), "Dados Pessoais");
        adapter.addFragment(new TabDadosVeiculosFragment(), "Dados do Veículo");
        viewPager.setAdapter(adapter);


    }


    //validar formulário du user
    private boolean validateFormUser(){

        boolean validUser =true;


        String nome = editNome.getText().toString();
        String faculdade = editEstabelecimento.getText().toString();
        String curso = editCurso.getText().toString();
        String contato = editTelemovel.getText().toString();
        String nib = editNib.getText().toString();

        if(TextUtils.isEmpty(nome)){
            editNome.setError("Preencha");
            validUser=false;
        }else {
            editNome.setError(null);
        }

        if(TextUtils.isEmpty(faculdade)){
            editEstabelecimento.setError("Preencha");
            validUser=false;
        }else {
            editEstabelecimento.setError(null);
        }

        if(TextUtils.isEmpty(curso)){
            editCurso.setError("Preencha");
            validUser=false;
        }else {
            editCurso.setError(null);
        }

        if(TextUtils.isEmpty(contato)){
            editTelemovel.setError("Preencha");
            validUser=false;
        }else {
            editTelemovel.setError(null);
        }

        if(TextUtils.isEmpty(nib)){
            editNib.setError("Preencha");
            validUser=false;
        }else {
            editNib.setError(null);
        }



        return validUser;
    }

    public void clickAlterarDadosPessoais(View view){


        DatabaseReference myRef = database.getReference("Users");
        String userDefPessoais;


        editNome = findViewById(R.id.txtNomeCondutor);
        editCurso = findViewById(R.id.txtCurso);
        editEstabelecimento = findViewById(R.id.txtEscola);
        editTelemovel = findViewById(R.id.txtContacto);
        editNib = findViewById(R.id.txtNib);



        novoNome= editNome.getText().toString();
        novoEstabelecimento = editEstabelecimento.getText().toString();
        novoCurso=editCurso.getText().toString();
        novoTelemovel=editTelemovel.getText().toString();
        novoNIB=editNib.getText().toString();

        userDefPessoais =  mAuth.getCurrentUser().getUid();
        Log.d("Carona",novoNome+novoEstabelecimento+novoCurso+novoTelemovel+novoNIB);

        if(!validateFormUser()){
            return;
        }
        else{
            myRef.child(userDefPessoais).child("nome").setValue(novoNome);
            myRef.child(userDefPessoais).child("curso").setValue(novoCurso);
            myRef.child(userDefPessoais).child("estabelecimento").setValue(novoEstabelecimento);
            myRef.child(userDefPessoais).child("telemovel").setValue(novoTelemovel);
            myRef.child(userDefPessoais).child("nib").setValue(novoNIB);
            Toast.makeText(getApplication(), "Dados de perfil alterados",
                    Toast.LENGTH_SHORT).show();
        }



    }




    //validar formulário do veiculo
    private boolean validateFormVeiculo(){

        boolean valid =true;


        String marca = editMarca.getText().toString();
        String modelo = editModelo.getText().toString();
        String cor = editCor.getText().toString();
        String matricula = editMatricula.getText().toString();

        if(TextUtils.isEmpty(marca)){
            editMarca.setError("Preencha");
            valid=false;
        }else {
            editMarca.setError(null);
        }

        if(TextUtils.isEmpty(modelo)){
            editModelo.setError("Preencha");
            valid=false;
        }else {
            editModelo.setError(null);
        }

        if(TextUtils.isEmpty(cor)){
            editCor.setError("Preencha");
            valid=false;
        }else {
            editCor.setError(null);
        }

        if(TextUtils.isEmpty(matricula)){
            editMatricula.setError("Preencha");
            valid=false;
        }else {
            editMatricula.setError(null);
        }



        return valid;
    }

    public void clickAlterarDadosVeiculo(View view){

        DatabaseReference myRef = database.getReference("Users");
        String userCarro;

        //Definir campos do veiculo
        editMarca = findViewById(R.id.txtMarca);
        editModelo = findViewById(R.id.txtModelo);
        editCor = findViewById(R.id.txtCor);
        editMatricula = findViewById(R.id.txtMatricula);
        editLugares = findViewById(R.id.txtVeiculoLugares);



        novoMarca= editMarca.getText().toString();
        novoModelo = editModelo.getText().toString();
        novoCor=editCor.getText().toString();
        novoMatricula=editMatricula.getText().toString();
        novoLugares=editLugares.getText().toString();

        //
        userCarro = mAuth.getCurrentUser().getUid();
        Log.d("Carona",novoMarca+novoModelo+novoCor+novoMatricula+novoLugares);
        Log.d("Carona",userCarro);

        if(!validateFormVeiculo()){
            return;
        }
        else{
            Carro carroNovo = new Carro(novoMarca,novoCor,novoMatricula,novoLugares,novoModelo);
            myRef.child(userCarro).child("carro").setValue(carroNovo);
            Toast.makeText(getApplication(), "Dados do veiculo alterados",
                    Toast.LENGTH_SHORT).show();
        }




    }


}
