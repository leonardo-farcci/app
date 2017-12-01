package com.leo.appnutri.ACTIVITY;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.leo.appnutri.DAO.ConfiguracaoFireBase;
import com.leo.appnutri.Entidades.Usuario;
import com.leo.appnutri.Main;
import com.leo.appnutri.R;

public class Login extends AppCompatActivity {

    private FirebaseAuth autentica;
    private Button btnLogin, btnCadastrar;
    private EditText email, password;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.loginId);
        btnCadastrar = (Button) findViewById(R.id.cadastrarId);
        email = (EditText) findViewById(R.id.emailId);
        password = (EditText) findViewById(R.id.senhaId);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")){
                    usuario = new Usuario();
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(password.getText().toString());
                    validar();

                }else{
                    Toast.makeText(Login.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastrar();
            }
        });


    }

    private void validar(){
        autentica = ConfiguracaoFireBase.getFirebaseautenticacao();
        autentica.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(Login.this, "Logado!", Toast.LENGTH_SHORT).show();
                    abrirMain();
                }else{
                    Toast.makeText(Login.this, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirMain(){
        Intent abrirMain = new Intent(Login.this, Main.class);
        startActivity(abrirMain);
    }

    public void abrirCadastrar(){
        Intent intentAbrirTelaCadastrar = new Intent(Login.this, Cadastrar.class);
        startActivity(intentAbrirTelaCadastrar);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }


    @Override
    protected void onStop() {
        super.onStop();
    }


}
