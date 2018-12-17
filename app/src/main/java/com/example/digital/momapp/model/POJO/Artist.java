package com.example.digital.momapp.model.POJO;

public class Artist {

    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;

    public Artist(String influenced_by, String artistId, String name, String nationality) {
        Influenced_by = influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
    }

    public Artist() {
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }
}
