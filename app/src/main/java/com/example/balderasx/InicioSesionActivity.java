package com.example.balderasx;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesionActivity extends AppCompatActivity {

    public EditText emailId, password;
    public Button btnSignIn,btnIraRegistro;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    String email = "";
    String pwd = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        mAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.email_Reg);
        password = findViewById(R.id.password_Reg);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnIraRegistro = findViewById(R.id.btn_ir_a_registrarse);

        btnIraRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intentLogin = new Intent(InicioSesionActivity.this, RegistroCuentaActivity.class);
                startActivity(intentLogin);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailId.getText().toString();
                pwd = password.getText().toString();

                if (!email.isEmpty() && !pwd.isEmpty()) {
                    loginUser();
                } else {
                        Toast.makeText(InicioSesionActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                        Intent intentProfile = new Intent(InicioSesionActivity.this, DashboardActivity.class);
                    startActivity(intentProfile);
                    finish();
                } else {
                        Toast.makeText(InicioSesionActivity.this, "No se pudo iniciar sesión, compruebe los datos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!= null){
                Intent intentInicioSesion = new Intent(InicioSesionActivity.this, DashboardActivity.class);
            startActivity(intentInicioSesion);
            finish();
        }
    }
}
