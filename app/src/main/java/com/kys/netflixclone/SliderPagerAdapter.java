package com.kys.netflixclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SliderPagerAdapter extends RecyclerView.Adapter<SliderPagerAdapter.ViewHolder> {

    ArrayList<Slide> slideitem;

    public SliderPagerAdapter(ArrayList<Slide> slideitem) {
        this.slideitem = slideitem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Slide slide = slideitem.get(position);

        holder.poster.setImageResource(slide.Image);
        holder.name.setText(slide.Title);

    }

    @Override
    public int getItemCount() {
        return slideitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.slide_img);
            name = itemView.findViewById(R.id.slide_title);

        }
    }
}
