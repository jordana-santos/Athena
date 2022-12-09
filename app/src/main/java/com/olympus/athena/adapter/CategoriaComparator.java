package com.olympus.athena.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.olympus.athena.model.Categoria;

public class CategoriaComparator extends DiffUtil.ItemCallback<Categoria>{

    @Override
    public boolean areItemsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
        return oldItem.codigoCategoria.equals(newItem.codigoCategoria);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
        return oldItem.codigoCategoria.equals(newItem.codigoCategoria);
    }
}