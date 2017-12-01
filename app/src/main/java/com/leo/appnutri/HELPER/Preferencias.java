package com.leo.appnutri.HELPER;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Leo on 01/12/2017.
 */

public class Preferencias {


    private Context context;
    private SharedPreferences preferences;
    private String nome_arquivo = "appNutri.config";
    private int node = 0;
    private SharedPreferences.Editor editor;

    private final String key = "identificarUserLogado";
    private final String key_name = "nomeUserLogado";

    public Preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(nome_arquivo, node);

        editor = preferences.edit();
    }

    public void salvarUserConfig(String identificadorUser, String nomeUser){
        editor.putString(key, identificadorUser);
        editor.putString(key, nomeUser);
        editor.commit();
    }

    public String getIden(){
        return preferences.getString(key, null);
    }

    public String getName(){
        return preferences.getString(key_name, null);
    }
}
