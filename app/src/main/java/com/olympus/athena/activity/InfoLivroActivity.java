package com.olympus.athena.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.olympus.athena.R;
import com.olympus.athena.adapter.InfoLivroAdapter;
import com.olympus.athena.model.InfoViewModel;
import com.olympus.athena.model.Livro;

public class InfoLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_livro);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        InfoViewModel infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        LiveData<Livro> livro = infoViewModel.getBookDetailsLD(id);
        livro.observe(this, new Observer<Livro>() {
            @Override
            public void onChanged(Livro livro) {
                if (livro != null) {
                    TextView titulo = findViewById(R.id.tvTituloInfoLivro);
                    TextView sinopse = findViewById(R.id.tvSinopse);
                    TextView autor = findViewById(R.id.tvAutorInfo);
                    TextView editora = findViewById(R.id.tvAutorInfo);
                    TextView edicao = findViewById(R.id.tvEdicaoInfo);
                    TextView volume = findViewById(R.id.tvVolumeInfo);
                    TextView paginas = findViewById(R.id.tvPagInfo);
                    TextView dataPublicacao = findViewById(R.id.tvDataPubliInfo);
                    TextView ISBN = findViewById(R.id.tvISBN);
                    TextView nota = findViewById(R.id.tvAvaliacaoInfo);

                    titulo.setText(livro.titulo);
                    sinopse.setText(livro.sinopse);
                    autor.setText("Autor: " + livro.autor);
                    editora.setText("Editora: " + livro.editora);
                    edicao.setText("Edição: " + livro.edicao);
                    volume.setText("Volume: " + livro.volume);
                    paginas.setText("Quantidade de páginas: " + livro.qtd_paginas);
                    dataPublicacao.setText("Data de publicação: " + livro.dataPublicacao);
                    ISBN.setText("ISBN:" + livro.ISBN);
                    nota.setText("Nota: " + livro.nota);
                }
                else {
                    Toast.makeText(InfoLivroActivity.this, "Não foi possível obter os detalhes deste item", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}