package com.example.digital.momapp.model.DAO;

import android.support.annotation.NonNull;

import com.example.digital.momapp.model.POJO.Artist;
import com.example.digital.momapp.utils.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOArtist {

    private DatabaseReference mDatabase;


    public DAOArtist() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }


    public void getArtistById(final Integer artistId, final ResultListener<Artist> listenerController){

        mDatabase.child("artists").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Artist artist = snapshot.getValue(Artist.class);
                    if (artist.getArtistId().equals(artistId.toString())) {

                        listenerController.finish(artist);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                databaseError.toException();

            }
        });
    }


}
