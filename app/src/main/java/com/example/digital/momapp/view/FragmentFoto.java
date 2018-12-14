package com.example.digital.momapp.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digital.momapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFoto extends Fragment {


    FirebaseStorage storage = FirebaseStorage.getInstance();
    private ImageView imageView;
    private static final Integer PEDIDO_PERMISO_CAMARA= 1;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_foto, container, false);


        imageView =view.findViewById(R.id.imageview_principal);

        Button buttonSubirImagen = view.findViewById(R.id.botonSubir);
        buttonSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference referenciaRaiz = storage.getReference();
                final StorageReference referenciaImagenes = referenciaRaiz.child("imagenes");
                StorageReference referenciaUsuario = referenciaImagenes.child("imagenesDeUsuario");

                final StorageReference referenciaImagenPug = referenciaUsuario.child(new Date().getTime()+".jpg");

                byte[] bytesImagen = convertirImagenABytes(imageView);
                UploadTask uploadTask = referenciaImagenPug.putBytes(bytesImagen);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Subida Ok", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        Button buttonSacarFoto =view. findViewById(R.id.botonSacarFoto);
        buttonSacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCameraPermission();
                sacarFoto();

            }
        });






       return view;
    }
    public void requestCameraPermission(){
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA},
                PEDIDO_PERMISO_CAMARA);
    }
    public byte[] convertirImagenABytes(ImageView imageView){
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        return baos.toByteArray();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PEDIDO_PERMISO_CAMARA&& grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getContext(), "Permiso concedido", Toast.LENGTH_SHORT).show();
            sacarFoto();
        }
    }

    public void sacarFoto(){
        EasyImage.openCamera(this,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                File fotoSacada = imageFiles.get(0);
                //byte[] bytesDeImagen = fotoSacada.getPath().getBytes();
                Bitmap myBitmap= BitmapFactory.decodeFile(fotoSacada.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);

            }
        });
    }

}
