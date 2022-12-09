package com.olympus.athena.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.olympus.athena.model.Livro;

public class LivroComparator extends DiffUtil.ItemCallback<Livro> {
    @Override
    public boolean areItemsTheSame(@NonNull Livro oldItem, @NonNull Livro newItem) {
        return oldItem.codigoLivro.equals(newItem.codigoLivro);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Livro oldItem, @NonNull Livro newItem) {
        return oldItem.codigoLivro.equals(newItem.codigoLivro);
    }
}
