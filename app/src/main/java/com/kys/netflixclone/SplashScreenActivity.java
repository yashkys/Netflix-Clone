package com.kys.netflixclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(6000);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                Intent intent = new Intent(this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        thread.start();

    }
}