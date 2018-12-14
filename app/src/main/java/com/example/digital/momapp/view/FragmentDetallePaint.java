package com.example.digital.momapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public static final String ID_PAINT= "idPaint";



    private TextView textViewNombrePintura;
    private TextView textViewNacionalidad;
    private TextView textViewInfluencia;
    private TextView textViewNombreArtista;
    private Paint paint;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_paint, container, false);

        textViewNombrePintura=view.findViewById(R.id.nombre_detalle_paint);
        textViewNombreArtista=view.findViewById(R.id.nombre_artista_paint);
        textViewNacionalidad=view.findViewById(R.id.nacionalidad_artista_paint);
        textViewInfluencia=view.findViewById(R.id.influencia_artista_paint);

        Bundle bundle=getArguments();
        Integer idArtistPaint = bundle.getInt(CLAVE_PAINT);

       //textViewNombrePintura.setText(paint.getNombre());
        cargarArtista(idArtistPaint);



        return view;
    }
    public void cargarArtista(Integer idArtista){
        ArtistController artistController = new ArtistController();
        artistController.getArtist(idArtista, new ResultListener<Artist>() {
            @Override
            public void finish(Artist result) {
                textViewNombreArtista.setText(result.getNombreArtista());
                textViewNacionalidad.setText(result.getNacionalidad());
                textViewInfluencia.setText(result.getNacionalidad());
            }
        });
    }

}
