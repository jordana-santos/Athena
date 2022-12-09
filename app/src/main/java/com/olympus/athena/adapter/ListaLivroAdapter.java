package com.olympus.athena.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.olympus.athena.R;
import com.olympus.athena.activity.MainActivity;
import com.olympus.athena.model.Livro;
import com.olympus.athena.util.Config;
import com.olympus.athena.util.ImageCache;

import java.util.List;

public class ListaLivroAdapter extends PagingDataAdapter<Livro, MyViewHolder> {
    MainActivity mainActivity;

    public ListaLivroAdapter(MainActivity mainActivity, @NonNull DiffUtil.ItemCallback<Livro> diffCallBack) {
        super(diffCallBack);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lista_livro, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Livro livro = this.getItem(position);

        TextView tvTituloLivro = holder.itemView.findViewById(R.id.tvTituloListaLivro);
        tvTituloLivro.setText(livro.titulo);

        TextView tvNotaLivro = holder.itemView.findViewById(R.id.tvNotaListaLivro);
        tvNotaLivro.setText(livro.nota);

        TextView tvAutorLivro = holder.itemView.findViewById(R.id.tvAutorListaLivro);
        tvAutorLivro.setText(livro.autor);

        TextView tvSinopseLivro = holder.itemView.findViewById(R.id.tvSinopseListaLivro);
        tvSinopseLivro.setText(livro.sinopse);

        ImageView imCapaLivro = holder.itemView.findViewById(R.id.imCapaListaLivro);
        ImageCache.loadImageBase64ToImageView(mainActivity, Config.PRODUCTS_APP_URL + "pegar_imagem_livro.php", livro.codigoLivro, imCapaLivro, 80, 100);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.navegarInfoLivro(livro.codigoLivro);
            }
        });
    }
}
