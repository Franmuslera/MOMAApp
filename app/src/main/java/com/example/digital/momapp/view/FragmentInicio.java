package com.example.digital.momapp.view;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digital.momapp.R;
import com.example.digital.momapp.controller.PaintController;
import com.example.digital.momapp.model.POJO.Paint;
import com.example.digital.momapp.utils.ResultListener;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment implements AdapterPaints.ListenerAdapterItem {

    public static final String ID_PAINT= "idPaint";
    private TextView logout;
    private TextView foto;
    private AdapterPaints adapterPaints;
    private List<Paint> listPinturas;
    private List<Paint> listPinturasResultado;
    private RecyclerView recyclerViewPinturas;
    private ListenerFragmentInicio listenerFragmentInicio;
    private ListenerClickFoto listenerClickFoto;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        logout = view.findViewById(R.id.txtview_logout_from_home);
        foto= view.findViewById(R.id.button_foto);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getView().getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getContext(), "Deslog", Toast.LENGTH_SHORT).show();

            }
        });
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerClickFoto.cambiarFragment();


            }
        });
        recyclerViewPinturas = view.findViewById(R.id.recyclerview_paints);
        recyclerViewPinturas.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        this.adapterPaints= new AdapterPaints(this);
        recyclerViewPinturas.setAdapter(adapterPaints);
       crearLista();






        return view;
    }





    public void crearLista(){

        PaintController paintController = new PaintController();
        paintController.getPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> result) {
                for (Paint paint: result){
                    llamarImagenPaint(paint);


                }
            }
        });
    }


        public void llamarImagenPaint(final Paint paint){

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference referenceRaiz = storage.getReference();
            final StorageReference referenceImagenes= referenceRaiz.child(paint.getImagen());

            referenceImagenes.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    paint.setUrl_imagen(uri.toString());
                    adapterPaints.addPaint(paint);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });

        }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerFragmentInicio=(ListenerFragmentInicio)context;
        listenerClickFoto = (ListenerClickFoto)context;
    }

    @Override
    public void pinturaSeleccionada(Paint paint) {
        listenerFragmentInicio.informarPaintSeleccionada(paint);
    }


    public interface ListenerFragmentInicio{
          public void informarPaintSeleccionada(Paint paintSeleccionada);
        }

        public interface ListenerClickFoto{
        public void cambiarFragment();

    }



}
