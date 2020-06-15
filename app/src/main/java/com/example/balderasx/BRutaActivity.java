package com.example.balderasx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BRutaActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private EditText mEditTextUnidad;
    private Button btnAdd2;


    private CountDownTimer mCountDownTimer;
    private long mStartTimeInMillis;
    private long mtimeLeftInMilliSeconds; //
    private boolean mtimerRunning;
    private long mEndTime;

    EditText intervaloInput;
    TextView tvHora2, countDownText;
    Button btnStartPause, btnReset, btnSetIntervalo;
    SearchableSpinner mSpinnerRuta;
    RecyclerView recyclerView2;
    Unidades2Adapter mAdapter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_ruta44);

        UnidadesDBHelper dbHelper = new UnidadesDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        tvHora2 = findViewById(R.id.tvHora2);
        mEditTextUnidad = findViewById(R.id.edittext_unidad);
        btnAdd2 = findViewById(R.id.button_add2);


        mSpinnerRuta = findViewById(R.id.rutas_spinner2);
        btnSetIntervalo = findViewById(R.id.btn_set_minutes);
        btnStartPause = findViewById(R.id.button_start_pause);
        btnReset = findViewById(R.id.button_reset);
        countDownText = findViewById(R.id.countdown_text);
        intervaloInput = findViewById(R.id.set_minutes);


        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Unidades2Adapter(this, getAllItems());
        new ItemTouchHelper(ItemTouchHelperCallback2).attachToRecyclerView(recyclerView2);
        recyclerView2.setAdapter(mAdapter);

        setBotones();
        crearListaRuta();

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarHora();
                addItem();
            }
        });

    }

    private void addItem() {
        String name = mEditTextUnidad.getText().toString();
        String hora = tvHora2.getText().toString();
        String ruta = mSpinnerRuta.getSelectedItem().toString();


        ContentValues cv = new ContentValues();
        cv.put(UnidadesContract.UnidadesEntry2.COLUMN_HORA, name);
        cv.put(UnidadesContract.UnidadesEntry2.COLUMN_UNIDAD, hora);
        cv.put(UnidadesContract.UnidadesEntry2.COLUMN_RUTA, ruta);
        mDatabase.insert(UnidadesContract.UnidadesEntry2.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        ContentValues cvHist2 = new ContentValues();
        cvHist2.put(UnidadesContract.UnidadesEntry2.COLUMN_HORA, name);
        cvHist2.put(UnidadesContract.UnidadesEntry2.COLUMN_UNIDAD, hora);
        cvHist2.put(UnidadesContract.UnidadesEntry2.COLUMN_RUTA, ruta);
        mDatabase.insert(UnidadesContract.UnidadesEntry5.TABLE_NAME, null, cvHist2);


        mEditTextUnidad.getText().clear();

        Toast.makeText(this, "Unidad Agregada", Toast.LENGTH_SHORT).show();
    }

    private void registrarHora() {

        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        Date horaRegistro = Calendar.getInstance().getTime();

        String horaa = formato.format(horaRegistro);
        tvHora2.setText(horaa);
    }


    public void crearListaRuta() {
        ArrayList<String> rutaList = new ArrayList<>();

        rutaList.add("3");
        rutaList.add("3 Reyes Portillo");
        rutaList.add("3 Reyes Andres Q.roo");
        rutaList.add("3 Reyes Nichupte");
        rutaList.add("4");
        rutaList.add("5");
        rutaList.add("6");
        rutaList.add("7X4");
        rutaList.add("8");
        rutaList.add("11");
        rutaList.add("14");
        rutaList.add("15");
        rutaList.add("15-A");
        rutaList.add("15-B");
        rutaList.add("17");
        rutaList.add("18-A");
        rutaList.add("19");
        rutaList.add("21");
        rutaList.add("33");
        rutaList.add("44");
        rutaList.add("48");
        rutaList.add("68");
        rutaList.add("69");
        rutaList.add("71");
        rutaList.add("78");
        rutaList.add("80");
        rutaList.add("87");
        rutaList.add("90");
        rutaList.add("94");
        rutaList.add("95-96");
        rutaList.add("95-96 Andres Quintana Roo");
        rutaList.add("97 - 99 Itzales");
        rutaList.add("97 - 99 Poligono");
        rutaList.add("100");
        rutaList.add("102 Palmas");
        rutaList.add("103");
        rutaList.add("227");
        rutaList.add("236");
        rutaList.add("259");
        rutaList.add("295");
        rutaList.add("510");
        rutaList.add("Avante Portillo");
        rutaList.add("Avante Nichupte");
        rutaList.add("Avante Andres Q,roo");
        rutaList.add("Bonfil");
        rutaList.add("Guayacan");
        rutaList.add("Kusamil");
        rutaList.add("La Joya");
        rutaList.add("Leona Vicario");
        rutaList.add("México");
        rutaList.add("Milagro Nichupte");
        rutaList.add("Milagro Portillo");
        rutaList.add("Milagro Andres Q.roo");
        rutaList.add("Niños Heroes");
        rutaList.add("Paraiso Maya Portillo");
        rutaList.add("Paraiso Maya Quintana. Roo");
        rutaList.add("Paraiso Maya Nichupte");
        rutaList.add("Playa Blanca");
        rutaList.add("Portillo");
        rutaList.add("Puerto Juarez");
        rutaList.add("Sta Cecilia Portillo");
        rutaList.add("Sta Cecilia Andres Q.roo");
        rutaList.add("Sta Cecilia Nichupte");
        rutaList.add("Talleres");
        rutaList.add("Tierra Maya");
        rutaList.add("Torito");
        rutaList.add("Urbi Nichupte");
        rutaList.add("Urbi Portillo");
        rutaList.add("Urbi Andres Q.roo");
        rutaList.add("Valle Verde Portillo");
        rutaList.add("Valle Verde Andres Q.roo");
        rutaList.add("Valle Verde Nichupte");
        rutaList.add("Villas del Mar");

        mSpinnerRuta.setAdapter(new ArrayAdapter<>(BRutaActivity.this, android.R.layout.simple_spinner_dropdown_item, rutaList));
        mSpinnerRuta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Toast.makeText(BRutaActivity.this, "Selecciona una Ruta", Toast.LENGTH_SHORT).show();
                } else {
                    String sNumeroRuta = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Cursor getAllItems() {

        return mDatabase.query(
                UnidadesContract.UnidadesEntry2.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UnidadesContract.UnidadesEntry2.COLUMN_TIMESTAMP + " ASC"
        );
    }

    ItemTouchHelper.SimpleCallback ItemTouchHelperCallback2 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            removeItem((long) viewHolder.itemView.getTag());
        }
    };

    private void removeItem(long id) {

        mDatabase.delete(UnidadesContract.UnidadesEntry2.TABLE_NAME,
                UnidadesContract.UnidadesEntry2._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
        Toast.makeText(this, "Unidad eliminada", Toast.LENGTH_SHORT).show();
    }

    public void setBotones() {
        btnSetIntervalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = intervaloInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(BRutaActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(BRutaActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                intervaloInput.setText("");
            }
        });
        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mtimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();
            }
        });
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mtimeLeftInMilliSeconds;
        mCountDownTimer = new CountDownTimer(mtimeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mtimeLeftInMilliSeconds = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mtimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        mtimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mtimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mtimeLeftInMilliSeconds = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mtimeLeftInMilliSeconds / 1000) / 3600;
        int minutes = (int) ((mtimeLeftInMilliSeconds / 1000) % 3600) / 60;
        int seconds = (int) (mtimeLeftInMilliSeconds / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        countDownText.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (mtimerRunning) {
            intervaloInput.setVisibility(View.INVISIBLE);
            btnSetIntervalo.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.INVISIBLE);
            btnStartPause.setText("PAUSA");
        } else {
            intervaloInput.setVisibility(View.VISIBLE);
            btnSetIntervalo.setVisibility(View.VISIBLE);
            btnStartPause.setText("INICIAR");
            if (mtimeLeftInMilliSeconds < 1000) {
                btnStartPause.setVisibility(View.INVISIBLE);
            } else {
                btnStartPause.setVisibility(View.VISIBLE);
            }
            if (mtimeLeftInMilliSeconds < mStartTimeInMillis) {
                btnReset.setVisibility(View.VISIBLE);
            } else {
                btnReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        // closeKeyboard();
        intervaloInput.setText("");
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs2 = getSharedPreferences("prefs2", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = prefs2.edit();
        editor2.putLong("startTimeInMillis", mStartTimeInMillis);
        editor2.putLong("millisLeft", mtimeLeftInMilliSeconds);
        editor2.putBoolean("timerRunning", mtimerRunning);
        editor2.putLong("endTime", mEndTime);
        editor2.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs2 = getSharedPreferences("prefs2", MODE_PRIVATE);
        mStartTimeInMillis = prefs2.getLong("startTimeInMillis", 600000);
        mtimeLeftInMilliSeconds = prefs2.getLong("millisLeft", mStartTimeInMillis);
        mtimerRunning = prefs2.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mtimerRunning) {
            mEndTime = prefs2.getLong("endTime", 0);
            mtimeLeftInMilliSeconds = mEndTime - System.currentTimeMillis();
            if (mtimeLeftInMilliSeconds < 0) {
                mtimeLeftInMilliSeconds = 0;
                mtimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }
}
