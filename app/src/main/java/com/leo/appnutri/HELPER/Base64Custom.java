package com.leo.appnutri.HELPER;

import android.util.Base64;

/**
 * Created by Leo on 01/12/2017.
 */

public class Base64Custom {


    public static String codBase64 (String txt){
        return Base64.encodeToString(txt.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "" );
    }

    public static String decodeBase64 (String txtCodificado){
        return new String(Base64.decode(txtCodificado, Base64.DEFAULT));
    }
}
