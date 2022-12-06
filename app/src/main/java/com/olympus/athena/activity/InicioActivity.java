package com.olympus.athena.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.olympus.athena.R;
import com.olympus.athena.util.Config;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Se o usuário ainda não logou, então não existe informação de login guardada na app.
        // Então a app é redirecionada para a tela de login.
        if(Config.getLogin(InicioActivity.this).isEmpty()) {
            Intent i = new Intent(InicioActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        // Se o usuário já logou, então a informação de login está guardada na app. Então
        // a app é redirecionada para a tela principal da app (HomeActivity)
        else {
            Intent i = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}