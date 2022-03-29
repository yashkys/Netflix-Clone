package com.kys.netflixclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    TextView tvnavtosignup;
    TextView tvhelp;

    EditText mail,password;
    TextView btnsignin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tvnavtosignup = findViewById(R.id.tvnavtosignup);
        tvhelp = findViewById(R.id.tvhelp);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        btnsignin = findViewById(R.id.btnsignin);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        tvnavtosignup.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        tvhelp.setOnClickListener(view -> {
            gotoUrl("https://help.netflix.com/en/");
        });

        btnsignin.setOnClickListener(view -> {
            PerforLogin();
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void PerforLogin() {
        String email = mail.getText().toString();
        String pass = password.getText().toString();
        if(!email.matches(emailPattern)){
            mail.setError("Enter correct email");
        }
        else if(pass.length()<8){
            password.setError("Password must contain atleast 8 characters");
        }else{
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                    sendToNextActivity();
                } else {
                    Toast.makeText(this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return super.onSupportNavigateUp();
//    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void sendToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}