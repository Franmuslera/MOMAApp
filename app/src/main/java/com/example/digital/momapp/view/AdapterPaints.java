package com.example.digital.momapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.digital.momapp.R;
import com.example.digital.momapp.model.POJO.Paint;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AdapterPaints extends RecyclerView.Adapter{
    private List<Paint> listaPinturas;
    private ListenerAdapterItem listenerAdapterItem;

    public AdapterPaints(ListenerAdapterItem listener,List<Paint> lista){
        this.listenerAdapterItem = listener;
        this.listaPinturas= lista;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewCelda = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_pinturas,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(viewCelda);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Paint paint = listaPinturas.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.BindPaint(paint);
    }



    @Override
    public int getItemCount() {
        return listaPinturas.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nombrePintura;
        private FirebaseStorage firebaseStorage;
        private ImageView imageViewPaint;


        public ItemViewHolder(View itemView) {
            super(itemView);
            nombrePintura = itemView.findViewById(R.id.textview_nombre_pintura);
            imageViewPaint = itemView.findViewById(R.id.imageview_pintura);
            firebaseStorage = FirebaseStorage.getInstance();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Paint paint = listaPinturas.get(getAdapterPosition());
                    listenerAdapterItem.pinturaSeleccionada(paint);
                }
            });

        }
        public void BindPaint(Paint paint){
            nombrePintura.setText(paint.getNombre());


            Glide.with(itemView.getContext())
                    .load(paint.getUrl_imagen())
                    .into(imageViewPaint);




        }
    }
    public void setResult(List<Paint> pinturas){
        this.listaPinturas = pinturas;
        notifyDataSetChanged();
    }
    public interface ListenerAdapterItem{
        public void pinturaSeleccionada(Paint paintSeleccionada);
    }
}
