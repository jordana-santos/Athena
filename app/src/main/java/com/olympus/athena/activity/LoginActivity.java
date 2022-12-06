package com.olympus.athena.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.olympus.athena.R;
import com.olympus.athena.model.LoginViewModel;
import com.olympus.athena.util.Config;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new LoginViewModel(getApplication());

        Button button = findViewById(R.id.btnEntrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etEmail = findViewById(R.id.edEmail);
                final String email = etEmail.getText().toString();

                EditText etSenha = findViewById(R.id.edSenha);
                final String senha = etSenha.getText().toString();

                LiveData<Integer> resultLD = loginViewModel.login(email, senha);
                resultLD.observe(LoginActivity.this, new Observer<Integer>() {

                    // Ao ser chamado, o método onChanged informa também qual foi o resultado
                    @Override
                    public void onChanged(Integer codigoPessoa) {
                        // aBoolean contém o resultado do login. Se aBoolean for true, significa
                        // que as infos de login e senha enviadas ao servidor estão certas. Neste
                        // caso, guardamos as infos de login e senha dentro da app através da classe
                        // Config. Essas infos de login e senha precisam ser guardadas dentro da app
                        // para que possam ser usadas quando a app pedir dados ao servidor web que só
                        // podem ser obtidos se o usuário enviar o login e senha.
                        if (codigoPessoa >= 0) {

                            // guarda os dados de login e senha dentro da app
                            Config.setLogin(LoginActivity.this, email);
                            Config.setPassword(LoginActivity.this, senha);
                            Config.setCodigoPessoa(LoginActivity.this, String.valueOf(codigoPessoa));

                            // exibe uma mensagem indicando que o login deu certo
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            // Navega para tela principal
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {

                            // Se o login não deu certo, apenas continuamos na tela de login e
                            // indicamos com uma mensagem ao usuário que o login não deu certo.
                            Toast.makeText(LoginActivity.this, "Não foi possível realizar o login da aplicação", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}