package com.example.balderasx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ImageView imgAddA,imgAddB,imgAddC,imgList,imgPay,imgLogOut;


        imgAddA = findViewById(R.id.btn_agregar_A);
        imgAddB = findViewById(R.id.btn_agregar_B);
        imgAddC = findViewById(R.id.btn_agregar_C);
        imgList = findViewById(R.id.btn_listas);
        imgPay = findViewById(R.id.btn_pagos);
        imgLogOut = findViewById(R.id.btn_cerrar_sesion);


        imgAddA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent inToA = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(inToA);
            }
        });
        imgAddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent inToB = new Intent(DashboardActivity.this,Ruta44.class);
                startActivity(inToB);
            }
        });
        imgAddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inToC = new Intent(DashboardActivity.this, RutaBActivity.class);
                startActivity(inToC);

            }
        });
        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToList = new Intent(DashboardActivity.this, RutasActivity.class);
                startActivity(inToList);
            }
        });

        imgPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inToo = new Intent(DashboardActivity.this, PagosActivity.class);
                startActivity(inToo);
            }
        });

        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                        Intent inToLogin = new Intent(DashboardActivity.this, InicioSesionActivity.class);
                startActivity(inToLogin);
                finish();
            }
        });
    }

}

