package com.example.balderasx;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class HistorialActivity extends AppCompatActivity {

    Button btnDeleteA,btnDeleteB,btnDeleteC, btnA, btnB, btnC;
    private SQLiteDatabase mDatabase;
    HistorialAdapter mAdapter;

    HistorialFragment historialFragment;
    HistorialBFragment historialBFragment;
    HistorialCFragment historialCFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        btnDeleteA = findViewById(R.id.btnDeleteA);
        btnDeleteB = findViewById(R.id.btnDeleteB);
        btnDeleteC = findViewById(R.id.btnDeleteC);

        btnA = findViewById(R.id.btn_hist_a);
        btnB = findViewById(R.id.btn_hist_b);
        btnC = findViewById(R.id.btn_hist_c);

        historialFragment = new HistorialFragment();
        historialBFragment = new HistorialBFragment();
        historialCFragment = new HistorialCFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment, historialFragment).commit();


        UnidadesDBHelper dbHelper = new UnidadesDBHelper(getApplicationContext());
        mDatabase = dbHelper.getWritableDatabase();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, historialFragment);
                transaction.commit();
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, historialBFragment);
                transaction.commit();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragment, historialCFragment);
                transaction.commit();


            }
        });

        btnDeleteA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistorialActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Eliminar el historial");
                builder.setMessage("Quieres eliminar el historial de forma permanente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mDatabase.delete(UnidadesContract.UnidadesEntry4.TABLE_NAME, null, null);
                                mAdapter.swapCursor(getAllItems());
                                Toast.makeText(HistorialActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnDeleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistorialActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Eliminar el historial");
                builder.setMessage("Quieres eliminar el historial de forma permanente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mDatabase.delete(UnidadesContract.UnidadesEntry5.TABLE_NAME, null, null);
                                mAdapter.swapCursor(getAllItems());
                                Toast.makeText(HistorialActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        btnDeleteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistorialActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Eliminar el historial");
                builder.setMessage("Quieres eliminar el historial de forma permanente?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mDatabase.delete(UnidadesContract.UnidadesEntry6.TABLE_NAME, null, null);
                                mAdapter.swapCursor(getAllItems());
                                Toast.makeText(HistorialActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("No", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private Cursor getAllItems() {

        return mDatabase.query(
                UnidadesContract.UnidadesEntry4.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UnidadesContract.UnidadesEntry4.COLUMN_TIMESTAMP + " ASC"
        );
    }
}
