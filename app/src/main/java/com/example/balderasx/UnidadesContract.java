package com.example.balderasx;

import android.provider.BaseColumns;

public class UnidadesContract {

private UnidadesContract(){

}


    public static final class UnidadesEntry implements BaseColumns {
        public static final String TABLE_NAME = "unidadesList";
        public static final String COLUMN_UNIDAD = "unidad";
        public static final String COLUMN_HORA = "hora";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
