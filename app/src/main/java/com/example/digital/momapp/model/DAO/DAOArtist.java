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

    public void getArtistById(final Integer artistId, final ResultListener<Artist> listenerController){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  for (DataSnapshot data : dataSnapshot.child("artist").getChildren()){
                      Artist artist = data.getValue(Artist.class);


                      if(artist.getIdArtista().equals(artistId.toString())){
                          listenerController.finish(artist);
                      }
                  }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
