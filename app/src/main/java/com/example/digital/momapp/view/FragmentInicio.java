package com.example.digital.momapp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment {


    private TextView logout;
    private AdapterPaints adapterPaints;
    private List<Paint> listPinturas;
    private List<Paint> listPinturasResultado;
    private RecyclerView recyclerViewPinturas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        logout = view.findViewById(R.id.txtview_logout_from_home);

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
       // recyclerViewPinturas = view.findViewById(R.id.recyclerview_paints);
        //this.adapterPaints= new AdapterPaints();
      //  crearLista();






        return view;
    }
    private List<Paint> crearLista(){

            PaintController paintController = new PaintController();
            paintController.getPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> result) {
                    recyclerViewPinturas.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                    recyclerViewPinturas.setAdapter(adapterPaints);
                    adapterPaints.setResult(result);






                }
            });
            return listPinturas;
        }


}
