package com.olympus.athena.model;

import android.graphics.Bitmap;

public class Livro {
    public int id;
    public int imgCapa;
    public String titulo, categoria, sinopse, nota;

    public Livro(int id, int imgCapa, String titulo, String categoria, String sinopse, String nota) {
        this.id = id;
        this.imgCapa = imgCapa;
        this.titulo = titulo;
        this.categoria = categoria;
        this.sinopse = sinopse;
        this.nota = nota;
    }
}
