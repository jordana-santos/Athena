package com.olympus.athena.model;

public class InfoLivro {
    Integer idLivro;
    String titulo, data_publicacao, ISBN, sinopse, edicao,volume, qtd_paginas;

    public InfoLivro(Integer idLivro, String titulo, String data_publicacao, String ISBN, String sinopse, String edicao, String volume, String qtd_paginas) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.ISBN = ISBN;
        this.sinopse = sinopse;
        this.edicao = edicao;
        this.volume = volume;
        this.qtd_paginas = qtd_paginas;
    }

    public InfoLivro() {

    }
}
