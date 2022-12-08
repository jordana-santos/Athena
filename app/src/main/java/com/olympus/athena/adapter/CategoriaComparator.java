package com.olympus.athena.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.olympus.athena.model.Categoria;

public class CategoriaComparator extends DiffUtil.ItemCallback<Categoria>{
    // TODO mesmo com o texto da atividade da galeria publica, não consegui entender a aplicação disso
    @Override
    public boolean areItemsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
        return String.valueOf(oldItem.id).equals(newItem.id);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
        return String.valueOf(oldItem.id).equals(newItem.id) &&
                oldItem.nome.equals(newItem.nome);
    }
}