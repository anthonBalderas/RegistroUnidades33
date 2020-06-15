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

public class HistorialCFragment extends Fragment {
    private SQLiteDatabase mDatabase;


    HistorialCAdapter historialCAdapter;
    RecyclerView recyclerView;
    View vista;

    public HistorialCFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_historial_c, container, false);
        UnidadesDBHelper dbHelper = new UnidadesDBHelper(getContext());
        mDatabase = dbHelper.getReadableDatabase();

        recyclerView = vista.findViewById(R.id.recycler_historial3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historialCAdapter = new HistorialCAdapter(getContext(), getAllItems());
        recyclerView.setAdapter(historialCAdapter);


        return vista;


    }

    private Cursor getAllItems() {

        return mDatabase.query(
                UnidadesContract.UnidadesEntry6.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UnidadesContract.UnidadesEntry6.COLUMN_TIMESTAMP + " ASC"
        );
    }


}
