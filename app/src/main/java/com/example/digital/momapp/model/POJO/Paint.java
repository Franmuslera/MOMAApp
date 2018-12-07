package com.example.digital.momapp.model.POJO;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Paint implements Serializable {
    @SerializedName("name")
    private String nombre;
    @SerializedName("image")
    private String imagen;
    @SerializedName("artistId")
    private Integer artistId;

    public Paint() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
