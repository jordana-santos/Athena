package com.olympus.athena.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;
import com.olympus.athena.model.Livro;

public class InfoLivroAdapter extends PagingDataAdapter<Livro, MyViewHolder> {
    MainActivity mainActivity;

    public InfoLivroAdapter(MainActivity mainActivity, @NonNull DiffUtil.ItemCallback<Livro> diffCallBack) {
        super(diffCallBack);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_info_livro, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Livro livro = this.getItem(position);
        TextView titulo = holder.itemView.findViewById(R.id.tvTituloInfoLivro);
        TextView sinopse = holder.itemView.findViewById(R.id.tvSinopse);
        TextView autor = holder.itemView.findViewById(R.id.tvAutorInfo);
        TextView editora = holder.itemView.findViewById(R.id.tvAutorInfo);
        TextView edicao = holder.itemView.findViewById(R.id.tvEdicaoInfo);
        TextView volume = holder.itemView.findViewById(R.id.tvVolumeInfo);
        TextView paginas = holder.itemView.findViewById(R.id.tvPagInfo);
        TextView dataPublicacao = holder.itemView.findViewById(R.id.tvDataPubliInfo);
        TextView ISBN = holder.itemView.findViewById(R.id.tvISBN);
        TextView nota = holder.itemView.findViewById(R.id.tvAvaliacaoInfo);

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

}
