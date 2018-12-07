package com.example.digital.momapp.view;

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

    public AdapterPaints(){
        this.listaPinturas= new ArrayList<>();
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
        private String imageUrl;
        private ImageView imageViewPaint;


        public ItemViewHolder(View itemView) {
            super(itemView);
            nombrePintura = itemView.findViewById(R.id.textview_nombre_pintura);
            imageViewPaint = itemView.findViewById(R.id.imageview_pintura);

        }
        public void BindPaint(Paint paint){
            nombrePintura.setText(paint.getNombre());
            imageUrl = (paint.getImagen());
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference reference = storage.getReference();
            reference = reference.child(imageUrl);
            Glide.with(itemView.getContext()).load(reference).into(imageViewPaint);
        }
    }
    public void setResult(List<Paint> pinturas){
        this.listaPinturas = pinturas;
        notifyDataSetChanged();
    }
}
