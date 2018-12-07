package com.example.digital.momapp.model.DAO;

import com.example.digital.momapp.model.POJO.Paint;
import com.example.digital.momapp.model.POJO.PaintContainer;
import com.example.digital.momapp.utils.ResultListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPaint {
    private StorageReference storageReference;
    private Retrofit retrofit;

    public DAOPaint(){
        storageReference = FirebaseStorage.getInstance().getReference();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.myjson.com/").addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }
    public void getPaintsAsincronico(final ResultListener<List<Paint>> listenerController){
        ServicePaints servicePaints = retrofit.create(ServicePaints.class);
        Call<PaintContainer> call = servicePaints.getPaints();
        call.enqueue(new Callback<PaintContainer>() {
            @Override
            public void onResponse(Call<PaintContainer> call, Response<PaintContainer> response) {
                listenerController.finish(response.body().getPaints());
            }

            @Override
            public void onFailure(Call<PaintContainer> call, Throwable t) {

            }
        });
    }
}
