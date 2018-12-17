package com.example.digital.momapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.momapp.R;
import com.example.digital.momapp.controller.ArtistController;
import com.example.digital.momapp.model.POJO.Artist;
import com.example.digital.momapp.model.POJO.Paint;
import com.example.digital.momapp.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallePaint extends Fragment {

    public static final String CLAVE_PAINT= "Paint";


    private ImageView imageViewPaint;
    private TextView textViewNombrePintura;
    private TextView textViewNacionalidad;
    private TextView textViewInfluencia;
    private TextView textViewNombreArtista;
    private TextView textViewIdArtista;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_paint, container, false);

        imageViewPaint=view.findViewById(R.id.imagen_detalle_paint);
        textViewNombrePintura=view.findViewById(R.id.nombre_detalle_paint);
        textViewNombreArtista=view.findViewById(R.id.nombre_artista_paint);
        textViewNacionalidad=view.findViewById(R.id.nacionalidad_artista_paint);
        textViewInfluencia=view.findViewById(R.id.influencia_artista_paint);
        textViewIdArtista=view.findViewById(R.id.id_artista_paint);

        Bundle bundle=getArguments();
        Paint paint = (Paint) bundle.getSerializable(CLAVE_PAINT);
        textViewNombrePintura.setText(paint.getNombre());
        Glide.with(getContext()).load(paint.getUrl_imagen()).into(imageViewPaint);
        cargarArtista(paint.getArtistId());




        return view;
    }
    public void cargarArtista(Integer idArtista){
        ArtistController artistController = new ArtistController();
        artistController.getArtist(idArtista, new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                textViewNombreArtista.setText("NombreArtista: "+result.getName());
                textViewNacionalidad.setText("Nacionalidad: "+result.getNationality());
                textViewInfluencia.setText("Influencia: "+result.getInfluenced_by());
                textViewIdArtista.setText("ID Artista: "+result.getArtistId());
            }
        });
    }

}
