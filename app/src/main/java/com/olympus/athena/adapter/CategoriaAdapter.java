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
import com.olympus.athena.model.Categoria;
import com.olympus.athena.util.Config;
import com.olympus.athena.util.ImageCache;

import java.util.List;

public class CategoriaAdapter extends PagingDataAdapter<Categoria, MyViewHolder> {
    MainActivity mainActivity;

    public CategoriaAdapter(MainActivity mainActivity, @NonNull DiffUtil.ItemCallback<Categoria> diffCallBack) {
        super(diffCallBack);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_categoria, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categoria cat = this.getItem(position);
        TextView tvCategoriaName = holder.itemView.findViewById(R.id.tvCategoriaName);
        tvCategoriaName.setText(cat.dscCategoria);

        ImageView imCategoria = holder.itemView.findViewById(R.id.imCategoria);
        ImageCache.loadImageBase64ToImageView(mainActivity, Config.PRODUCTS_APP_URL + "pegar_imagem_categoria.php",cat.codigoCategoria, imCategoria, 100, 80);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO criar o código do onclick
                mainActivity.setarFragmentoListaLivro(cat.codigoCategoria);
            }
        });
    }

}
