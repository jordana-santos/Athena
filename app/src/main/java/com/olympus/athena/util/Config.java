package com.olympus.athena.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe de suporte as configurações da app
 */
public class Config {

    // endereço base do servidor web
    public static String PRODUCTS_APP_URL = "http://10.0.2.2/Athena_php/";

    /**
     * Salva o login no espaço reservado da app
     * @param context contexto da app
     * @param login o login
     */
    public static void setLogin(Context context, String login) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    /**
     * Obtem o login
     * @param context
     * @return
     */
    public static String getLogin(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("login", "");
    }

    /**
     * @param context
     * @param password
     */
    public static void setPassword(Context context, String password) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("password", password).commit();
    }

    /**
     * @param context
     * @return
     */
    public static String getPassword(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("password", "");
    }

    /**
     * Salva o login no espaço reservado da app
     * @param context contexto da app
     * @param login o login
     */
    public static void setCodigoPessoa(Context context, String codigoPessoa) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("codigoPessoa", codigoPessoa).commit();
    }

    /**
     * Obtem o login
     * @param context
     * @return
     */
    public static String getCodigoPessoa(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("codigoPessoa", "");
    }
}
