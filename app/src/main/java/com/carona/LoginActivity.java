package com.carona;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.carona.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText txtUser,txtPass;
    String username,password;
    private FirebaseAuth mAuth;
    private final String TAG = "CARONA";

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        txtUser = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);


    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser != null){
           Intent intent = new Intent(this, HomeActivity.class);
           startActivity(intent);
       }
    }

    private boolean validateForm(){

        boolean valid =true;

        String email = txtUser.getText().toString();

        if(TextUtils.isEmpty(email)){
            txtUser.setError("Preencha");
            valid=false;
        }else {
            txtUser.setError(null);
        }


        String password = txtPass.getText().toString();

        if(TextUtils.isEmpty(password)){
            txtPass.setError("Preencha");
            valid=false;
        }else {
            txtPass.setError(null);
        }

        return valid;
    }

    public void clickLogin(View view) {



        username = txtUser.getText().toString();
        password = txtPass.getText().toString();

        if(!validateForm()){
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            user = mAuth.getCurrentUser();

                            Log.d(TAG, "signInWithEmail:done", task.getException());
                            Toast.makeText(LoginActivity.this, "Credenciais certas.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);


                        }  else
                        {

                                // If sign in fails, display a message to the user.
                                Log.d(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Credenciais erradas.",
                                        Toast.LENGTH_SHORT).show();

                            }


                        // ...
                    }
                });





    }
}
