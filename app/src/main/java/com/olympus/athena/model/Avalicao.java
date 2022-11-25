package com.olympus.athena.model;

public class Avalicao {
    // TODO mudar int de fotos para bitmap
    public int imgUser;
    public String user, nota, data, comentario;

    public Avalicao(int imgUser, String user, String nota, String data, String comentario) {
        this.imgUser = imgUser;
        this.user = user;
        this.nota = nota;
        this.data = data;
        this.comentario = comentario;
    }
}
