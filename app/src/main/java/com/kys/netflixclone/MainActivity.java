package com.kys.netflixclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    ArrayList<Slide> lstSlides;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        viewPager2 = findViewById(R.id.slider_pager);

        lstSlides = new ArrayList<>();
        int[] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide1,R.drawable.slide2};
        String[] title = {"Hellaro","Radhe Shyam","Hellaro","Radhe Shyam"};

        for(int i =0 ; i<images.length; i++){
            Slide slide = new Slide(images[i], title[i]);
            lstSlides.add(slide);
        }

        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(lstSlides);

        viewPager2.setAdapter(sliderPagerAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.nav_menu, menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);

        for(int i=0; i<menu.size();i++){
            MenuItem menuItem = menu.getItem(i);
            SpannableString spannableString = new SpannableString(
                    menu.getItem(i).getTitle().toString()
            );
            spannableString.setSpan(new ForegroundColorSpan(Color.RED),
                    0,spannableString.length(),0);
            menuItem.setTitle(spannableString);
        }

//        MenuItem help = findViewById((R.id.navigation_help1));
//        MenuItem signout = findViewById((R.id.navigation_signout));
//
//        help.setOnMenuItemClickListener(menuItem -> {
//            gotoUrl("https://help.netflix.com/legal/privacy");
//            return true;
//        });
//
//        signout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                FirebaseAuth.getInstance().signOut();
//                sendToNextActivity();
//                return true;
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.navigation_help1){
            gotoUrl("https://help.netflix.com/legal/privacy");
            return true;
        }
        if(id == R.id.navigation_signout){
            FirebaseAuth.getInstance().signOut();
            sendToNextActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendToNextActivity() {
        Intent intent = new Intent(this, IntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        return;
    }

}
