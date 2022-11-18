package com.olympus.athena;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaLivroAdapter extends RecyclerView.Adapter {
    List<Livro> list;
    MainActivity mainActivity;

    public ListaLivroAdapter(MainActivity mainActivity, List<Livro> list){
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lista_livro, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Livro l = list.get(position);

        ImageView imCapa = holder.itemView.findViewById(R.id.imCapaLivro);
        imCapa.setImageBitmap(l.imgCapa);

        TextView tvTitulo = holder.itemView.findViewById(R.id.tvTituloListaLivro);
        tvTitulo.setText(l.titulo);

        TextView tvNota = holder.itemView.findViewById(R.id.tvNotaListaLivro);
        tvNota.setText(l.nota);

        TextView tvCategoria = holder.itemView.findViewById(R.id.tvCategoriaListaLivro);
        tvCategoria.setText(l.categoria);

        TextView tvSinopse = holder.itemView.findViewById(R.id.tvSinopseListaLivro);
        tvSinopse.setText(l.sinopse);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO terminar isso
                mainActivity.navegarInfoLivro(l.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
