package com.kys.netflixclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity {

    TextView privacy;

    Button getstarted;

    private Toolbar toolbar;
    private TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        toolbar = findViewById(R.id.introToolbar);
        setSupportActionBar(toolbar);

        signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroductionActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        privacy = findViewById(R.id.privacy);
        privacy.setOnClickListener(view -> {
            gotoUrl("https://help.netflix.com/legal/privacy");
        });

        getstarted = findViewById(R.id.btnstart);
        getstarted.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });


    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.nav_menu, menu);
        getMenuInflater().inflate(R.menu.nav_menu, menu);

        for(int i=0; i<menu.size();i++){
            MenuItem menuItem = menu.getItem(i);
            SpannableString spannableString = new SpannableString(
                    menu.getItem(i).getTitle().toString()
            );
            spannableString.setSpan(new ForegroundColorSpan(Color.RED),
                    0,spannableString.length(),0);
            menuItem.setTitle(spannableString);
        }
        return true;
    }
}