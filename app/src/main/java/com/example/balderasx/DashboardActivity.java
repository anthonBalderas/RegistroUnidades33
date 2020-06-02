package com.example.balderasx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        ImageView imgAdd,imgList,imgPay,imgAddList,imgHist;


        imgAdd = findViewById(R.id.btn_agregar);
        imgList = findViewById(R.id.btn_listas);
        imgHist = findViewById(R.id.btn_historial);
        imgAddList = findViewById(R.id.btn_agregar_listas);
        imgPay = findViewById(R.id.btn_pagos);


        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent inToReg = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(inToReg);
            }
        });
        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent inToRutas = new Intent(DashboardActivity.this,RutasActivity.class);
            startActivity(inToRutas);
            }
        });
        imgHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

