package com.example.digital.momapp.model.POJO;

public class Artist {

    private Integer idArtista;
    private String nombreArtista;
    private String nacionalidad;
    private String influenecedBy;

    public Artist(Integer idArtista, String nombreArtista, String nacionalidad, String influenecedBy) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.nacionalidad = nacionalidad;
        this.influenecedBy = influenecedBy;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getInfluenecedBy() {
        return influenecedBy;
    }
}
