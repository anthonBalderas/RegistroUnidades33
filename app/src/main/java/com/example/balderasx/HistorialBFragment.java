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


public class HistorialBFragment extends Fragment {
    private SQLiteDatabase mDatabase;

    HistorialBAdapter historialBAdapter;
    RecyclerView recyclerView;
    View vista;

    public HistorialBFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_historial_b, container, false);
        UnidadesDBHelper dbHelper = new UnidadesDBHelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();

        recyclerView = vista.findViewById(R.id.recycler_historial2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historialBAdapter = new HistorialBAdapter(getContext(), getAllItems());
        recyclerView.setAdapter(historialBAdapter);


        return vista;


    }

    private Cursor getAllItems() {

        return mDatabase.query(
                UnidadesContract.UnidadesEntry5.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UnidadesContract.UnidadesEntry5.COLUMN_TIMESTAMP + " ASC"
        );
    }


}
