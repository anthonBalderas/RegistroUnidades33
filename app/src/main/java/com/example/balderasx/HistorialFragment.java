package com.example.balderasx;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class HistorialFragment extends Fragment  {
    private SQLiteDatabase mDatabase;

    HistorialAdapter historialAdapter;
    RecyclerView recyclerView;
    View vista;
    public HistorialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       vista = inflater.inflate(R.layout.fragment_historial, container, false);
        UnidadesDBHelper dbHelper = new UnidadesDBHelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();

        recyclerView = vista.findViewById(R.id.recycler_historial);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historialAdapter = new HistorialAdapter(getContext(),getAllItems());
        recyclerView.setAdapter(historialAdapter);


        return vista;


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
