package com.example.digital.momapp.controller;

import com.example.digital.momapp.model.DAO.DAOArtist;
import com.example.digital.momapp.model.POJO.Artist;
import com.example.digital.momapp.utils.ResultListener;

public class ArtistController {

    public void getArtist(Integer idArtista, final ResultListener<Artist> listenerView){
        DAOArtist daoArtist = new DAOArtist();
        daoArtist.getArtistById(idArtista, new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                listenerView.finish(result);
            }
        });
    }
}
