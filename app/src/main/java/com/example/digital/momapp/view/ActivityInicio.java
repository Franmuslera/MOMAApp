package com.example.digital.momapp.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.digital.momapp.R;

public class ActivityInicio extends AppCompatActivity {
    private String FRAGMENT_INICIO= "FragmentInicio";

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
}
