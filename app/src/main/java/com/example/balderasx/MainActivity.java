package com.example.balderasx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private UnidadesAdapter mAdapter;
    private EditText mEditTextName;


    private CountDownTimer mCountDownTimer;
    private long mStartTimeInMillis;
    private long mtimeLeftInMilliSeconds; //
    private boolean mtimerRunning;
    private long mEndTime;

    EditText intervaloInput;
    TextView tvHora, countDownText;
    Button btnStartPause, btnReset, btnSetIntervalo;

    RecyclerView recyclerView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnidadesDBHelper dbHelper = new UnidadesDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        tvHora = findViewById(R.id.tvHora);
        mEditTextName = findViewById(R.id.edittext_name);

        btnSetIntervalo = findViewById(R.id.btn_set_minutes);
        btnStartPause = findViewById(R.id.button_start_pause);
        btnReset = findViewById(R.id.button_reset);
        countDownText = findViewById(R.id.countdown_text);
        intervaloInput = findViewById(R.id.set_minutes);


        Button buttonAdd = findViewById(R.id.button_add);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UnidadesAdapter(this, getAllItems());
        new ItemTouchHelper(ItemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarHora();
                addItem();
            }
        });

        setBotones();
    }

    private void addItem() {

        String name = mEditTextName.getText().toString();
        String hora = tvHora.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(UnidadesContract.UnidadesEntry.COLUMN_HORA, name);
        cv.put(UnidadesContract.UnidadesEntry.COLUMN_UNIDAD, hora);
        mDatabase.insert(UnidadesContract.UnidadesEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
        mEditTextName.getText().clear();

    }

    private void removeItem(long id) {

        mDatabase.delete(UnidadesContract.UnidadesEntry.TABLE_NAME,
                UnidadesContract.UnidadesEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {

        return mDatabase.query(
                UnidadesContract.UnidadesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UnidadesContract.UnidadesEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    ItemTouchHelper.SimpleCallback ItemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            removeItem((long) viewHolder.itemView.getTag());
        }
    };

    private void registrarHora() {

        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        Date horaRegistro = Calendar.getInstance().getTime();

        String horaa = formato.format(horaRegistro);
        tvHora.setText(horaa);
    }

    public void setBotones() {
        btnSetIntervalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = intervaloInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(MainActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
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
            btnStartPause.setText("Pause");
        } else {
            intervaloInput.setVisibility(View.VISIBLE);
            btnSetIntervalo.setVisibility(View.VISIBLE);
            btnStartPause.setText("Start");
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
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mtimeLeftInMilliSeconds);
        editor.putBoolean("timerRunning", mtimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mtimeLeftInMilliSeconds = prefs.getLong("millisLeft", mStartTimeInMillis);
        mtimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mtimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
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
