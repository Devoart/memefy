package com.noneofever.memescommunity.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.noneofever.memescommunity.R;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    Context context;
    List<Integer> colorList;
    ColorAdapterListener colorAdapterListener;

    int rowSelected = -1;


    public ColorAdapter(Context context, ColorAdapterListener colorAdapterListener) {
        this.context = context;
        this.colorList = genColorList();
        this.colorAdapterListener = colorAdapterListener;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.color_item,parent,false);
        return new ColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        holder.color_selection.setBackgroundColor(colorList.get(position));


        if(rowSelected == position){
            holder.colorCheck.setVisibility(View.VISIBLE);
        }else{
            holder.colorCheck.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{

        public CardView color_selection;
        public ImageView colorCheck;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            color_selection = itemView.findViewById(R.id.color_section);
            colorCheck = itemView.findViewById(R.id.color_check);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorAdapterListener.onColorSelected(colorList.get(getAdapterPosition()));
                    rowSelected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    private List<Integer> genColorList() {
        List<Integer> colorList = new ArrayList<>();

        colorList.add(Color.parseColor("#ffb6c1"));
        colorList.add(Color.parseColor("#e8eaee"));
        colorList.add(Color.parseColor("#53555a"));
        colorList.add(Color.parseColor("#625e5e"));
        colorList.add(Color.parseColor("#504b5d"));
        colorList.add(Color.parseColor("#9e909a"));
        colorList.add(Color.parseColor("#867581"));
        colorList.add(Color.parseColor("#000000"));
        colorList.add(Color.parseColor("#ffee88"));
        colorList.add(Color.parseColor("#b57170"));
        colorList.add(Color.parseColor("#988be5"));
        colorList.add(Color.parseColor("#a99bff"));
        colorList.add(Color.parseColor("#e7e1d1"));
        colorList.add(Color.parseColor("#9ae1ca"));
        colorList.add(Color.parseColor("#d01212"));
        colorList.add(Color.parseColor("#b77b82"));
        colorList.add(Color.parseColor("#fe019a"));
        colorList.add(Color.parseColor("#cc123b"));
        colorList.add(Color.parseColor("#380022"));
        colorList.add(Color.parseColor("#8fb1cc"));
        colorList.add(Color.parseColor("#a9b4ea"));
        colorList.add(Color.parseColor("#a9dfd0"));
        colorList.add(Color.parseColor("#ecab9b"));
        colorList.add(Color.parseColor("#252e3b"));
        colorList.add(Color.parseColor("#3f4e63"));
        colorList.add(Color.parseColor("#e7baea"));
        colorList.add(Color.parseColor("#3743f7"));
        colorList.add(Color.parseColor("#cafbb6"));
        colorList.add(Color.parseColor("#535e7e"));
        colorList.add(Color.parseColor("#00bfff"));
        colorList.add(Color.parseColor("#ffbbbb"));
        colorList.add(Color.parseColor("#ffffff"));
        colorList.add(Color.parseColor("#cafbb6"));
        colorList.add(Color.parseColor("#e7b6fb"));
        colorList.add(Color.parseColor("#fbe7b6"));
        colorList.add(Color.parseColor("#f5f5f5"));
        colorList.add(Color.parseColor("#3743f7"));
        colorList.add(Color.parseColor("#535e7e"));
        colorList.add(Color.parseColor("#33383e"));
        colorList.add(Color.parseColor("#4c616a"));
        colorList.add(Color.parseColor("#867581"));
        colorList.add(Color.parseColor("#808284"));


        return colorList;
    }


    public interface  ColorAdapterListener{
        void onColorSelected(int color);
    }
}
