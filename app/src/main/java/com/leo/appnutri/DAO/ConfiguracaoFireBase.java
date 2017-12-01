package com.leo.appnutri.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by Leo on 01/12/2017.
 */

public class ConfiguracaoFireBase {
    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    //Referencia
    public static DatabaseReference getFirebase () {

        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();

        }
        return referenciaFirebase;
    }

    //Autenticação
    public static FirebaseAuth getFirebaseautenticacao (){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();

        }
        return autenticacao;

    }
}
