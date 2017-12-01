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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.leo.appnutri.DAO.ConfiguracaoFireBase;
import com.leo.appnutri.Entidades.Usuario;
import com.leo.appnutri.HELPER.Base64Custom;
import com.leo.appnutri.HELPER.Preferencias;
import com.leo.appnutri.R;

public class Cadastrar extends AppCompatActivity {

    private FirebaseAuth autenticar;
    private EditText email;
    private EditText senha;
    private EditText nome;
    private Button btnRegistrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        email = (EditText) findViewById(R.id.emailRegisterId);
        senha = (EditText) findViewById(R.id.senhaRegisterId);
        nome = (EditText) findViewById(R.id.nomeId);
        btnRegistrar = (Button) findViewById(R.id.registrarId);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setNome(nome.getText().toString());

                cadastrarUser();
            }
        });
    }

    private void cadastrarUser(){
        autenticar = ConfiguracaoFireBase.getFirebaseautenticacao();
        autenticar.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(Cadastrar.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Cadastrar.this, "Cadastro efetuado!", Toast.LENGTH_LONG).show();
                    String identifica = Base64Custom.codBase64(usuario.getEmail());
                    FirebaseUser userFireBase = task.getResult().getUser();
                    usuario.setId(identifica);
                    usuario.salvar();

                    Preferencias pref = new Preferencias(Cadastrar.this);
                    pref.salvarUserConfig(identifica, usuario.getNome());
                    abrirLogin();
                }else{
                    String error = "";

                    try{

                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        error = "Digite uma senha de no mínimo 6 caracteres";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        error = "E-mail inválido";
                    }catch (FirebaseAuthUserCollisionException e){
                        error = "E-mail já cadastrado";
                    }catch (Exception e){
                        error = "Erro no cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastrar.this, "Erro! "+error, Toast.LENGTH_LONG).show();
                    abrirLogin();
                }
            }
        });
    }

    public void abrirLogin(){
        Intent intent = new Intent(Cadastrar.this, Login.class);
        startActivity(intent);
        finish();
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
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


}
