package com.example.digital.momapp.controller;

import com.example.digital.momapp.model.DAO.DAOPaint;
import com.example.digital.momapp.model.POJO.Paint;
import com.example.digital.momapp.utils.ResultListener;

import java.util.List;

public class PaintController {

    public void getPaints(final ResultListener<List<Paint>> listenerView){
        DAOPaint daoPaint = new DAOPaint();
        daoPaint.getPaintsAsincronico(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> result) {
                listenerView.finish(result);
            }
        });
    }
}
