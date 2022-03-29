package com.kys.netflixclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    Button btnback;
    Button help;

    EditText mail, password;
    Button btnsignup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

//    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        help = findViewById(R.id.btnhelp);
        help.setOnClickListener(view -> {
            gotoUrl("https://help.netflix.com/en/");
        });

        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        btnsignup = findViewById(R.id.btnsignin);
        btnback = findViewById(R.id.btnback);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        progressBar = findViewById(R.id.progressBar);

        btnsignup.setOnClickListener(view -> {
//            progressBar.setVisibility(View.VISIBLE);
            PerforAuth();
        });

        btnback.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

    }


    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void PerforAuth() {
        String email = mail.getText().toString();
        String pass = password.getText().toString();
        if (!email.matches(emailPattern)) {
            mail.setError("Enter correct email");
        } else if (pass.length() < 8) {
            password.setError("Password must contain atleast 8 characters");
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                    sendToNextActivity();
                } else {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}