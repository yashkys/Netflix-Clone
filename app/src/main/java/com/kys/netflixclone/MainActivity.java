package com.kys.netflixclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    ArrayList<Slide> lstSlides;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
