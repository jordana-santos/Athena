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

import java.util.List;

public class CategoriaAdapter extends PagingDataAdapter<Categoria, MyViewHolder> {

    List<Categoria> list;
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
        Categoria cat = list.get(position);
        TextView tvCategoriaName = holder.itemView.findViewById(R.id.tvCategoriaName);
        tvCategoriaName.setText(cat.nome);

        ImageView imCategoria = holder.itemView.findViewById(R.id.imCategoria);
        imCategoria.setImageResource(cat.imagem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO criar o código do onclick
                mainActivity.setarFragmentoListaLivro(cat.id);
            }
        });
    }

//    @Override//   public int getItemCount() {
//        return list.size();
//    }
}
