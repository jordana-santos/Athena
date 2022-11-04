package com.olympus.athena;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter {

    List<Categoria> list;
    MainActivity mainActivity;
    public CategoriaAdapter(MainActivity mainActivity, List<Categoria> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoria_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Categoria c = list.get(position);
        TextView tvCategoriaName = holder.itemView.findViewById(R.id.tvCategoriaName);
        tvCategoriaName.setText(c.nome);

        ImageView imCategoria = holder.itemView.findViewById(R.id.imCategoria);
        imCategoria.setImageBitmap(c.imagem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO criar o código do onclick
                // TODO criar book list item
                // TODO adapter book list item
                // TODO criar fragment do perfil
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
