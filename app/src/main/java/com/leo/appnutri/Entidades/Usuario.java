package com.leo.appnutri.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.leo.appnutri.DAO.ConfiguracaoFireBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leo on 01/12/2017.
 */

public class Usuario implements Parcelable{

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {}

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private Usuario(Parcel from){
        nome = from.readString();
        email = from.readString();
        senha = from.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(email);
        parcel.writeString(senha);
    }

    public void salvar(){
        DatabaseReference referenceFireb = ConfiguracaoFireBase.getFirebase();
        referenceFireb.child("usuario").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUser = new HashMap<>();

        hashMapUser.put("id", getId());
        hashMapUser.put("nome", getNome());
        hashMapUser.put("email", getEmail());
        hashMapUser.put("senha", getSenha());

        return hashMapUser;
    }
}
