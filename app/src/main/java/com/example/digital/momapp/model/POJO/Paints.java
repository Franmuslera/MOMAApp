package com.example.digital.momapp.model.POJO;

import java.io.Serializable;

public class Paints implements Serializable{

    private String nombre;
    private String imagen;
    private Integer artistId;

    public Paints() {
        }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public Integer getArtistId() {
        return artistId;
    }
}
