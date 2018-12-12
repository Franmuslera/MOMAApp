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

    private String url_imagen="";

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Paint() {

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
