package com.example.digital.momapp.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.digital.momapp.R;
import com.example.digital.momapp.model.POJO.Paint;

import java.io.Serializable;
import java.util.List;

public class ActivityInicio extends AppCompatActivity implements FragmentInicio.ListenerClickPaint,FragmentInicio.ListenerClickFoto {
    private String FRAGMENT_INICIO= "FragmentInicio";
    private String FRAGMENT_DETALLE= "FragmentDetalle32";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        pegarFragment(new FragmentInicio(),FRAGMENT_INICIO);
    }

    private void pegarFragment(android.support.v4.app.Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_fragment_Inicio,fragment,tag);
        fragmentTransaction.commit();
    }



    @Override
    public void cambiarFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_fragment_Inicio,new FragmentFoto());
        fragmentTransaction.commit();
    }

    @Override
    public void irADetalle(List<Paint> listaPaints, Integer positionPaint) {
        FragmentDetallePaint fragmentDetallePaint= new FragmentDetallePaint();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetallePaint.CLAVE_PAINT, (Serializable) listaPaints);
        bundle.putInt(FragmentDetallePaint.CLAVE_POSITION, positionPaint);
        fragmentDetallePaint.setArguments(bundle);
        pegarFragment(fragmentDetallePaint,FRAGMENT_DETALLE);
    }
}
