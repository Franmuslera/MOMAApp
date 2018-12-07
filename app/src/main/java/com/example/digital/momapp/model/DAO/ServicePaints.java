package com.example.digital.momapp.model.DAO;

import com.example.digital.momapp.model.POJO.PaintContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicePaints {

    @GET("/bins/x858r")
    Call<PaintContainer> getPaints();

}
