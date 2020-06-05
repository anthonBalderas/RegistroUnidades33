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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroCuentaActivity extends AppCompatActivity {

    public EditText emailId, password, mNombre;
    public Button btnSignUp,btnIrSignIn;


    FirebaseAuth mAuth;
    String email = "";
    String pwd = "";
    String nombre = "";
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mNombre = findViewById(R.id.nombre_Reg);
        emailId = findViewById(R.id.email_Reg);
        password = findViewById(R.id.password_Reg);
        btnSignUp = findViewById(R.id.btn_SignUp);
        btnIrSignIn = findViewById(R.id.btn_ir_SignIn);


        btnIrSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intentLogin = new Intent(RegistroCuentaActivity.this, InicioSesionActivity.class);
                startActivity(intentLogin);


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailId.getText().toString();
                pwd = password.getText().toString();
                nombre = mNombre.getText().toString();
                if (!email.isEmpty() && !pwd.isEmpty()) {
                    if (pwd.length() >= 6) {

                    } else {
                            Toast.makeText(RegistroCuentaActivity.this, "La contraseña debe contener al menos 6 caracteres",
                                Toast.LENGTH_SHORT).show();
                    }
                    registerUser();

                } else {
                        Toast.makeText(RegistroCuentaActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("email", email);
                    map.put("password", pwd);
                    map.put("nombre",nombre);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                Intent intentProfile = new Intent( RegistroCuentaActivity.this, MainActivity.class);
                                startActivity(intentProfile);
                                finish();

                            } else {
                                Toast.makeText(RegistroCuentaActivity.this, "No se pudieron crear los datos correctamente",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistroCuentaActivity.this, "No se pudo registrar este usuario",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}