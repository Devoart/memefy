package com.noneofever.memescommunity.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.noneofever.memescommunity.R;

import java.util.ArrayList;
import java.util.List;

public class FontAdapter  extends RecyclerView.Adapter<FontAdapter.FontViewHolder> {

    Context context;
    FontAdapterClickListener listener;
    List<String> fontList;

    int rowSelected = -1;

    public FontAdapter(Context context, FontAdapterClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.fontList = loadFontList();
    }


    private List<String> loadFontList() {
        List<String> result = new ArrayList<>();

        result.add("hindsiliguri_bold.ttf");
        result.add("hindsiliguri_light.ttf");
        result.add("hindsiliguri_medium.ttf");
        result.add("hindsiliguri_regular.ttf");
        result.add("hindsiliguri_semibold.ttf");
        result.add("mamun_swapno.ttf");
        result.add("meep_light.ttf");
        result.add("mereoleona.ttf");
        result.add("mereoleonascript.ttf");
        result.add("mereoleonascriptalt.ttf");
        result.add("valentinebutterfly.ttf");

        return  result;
    }

    @NonNull
    @Override
    public FontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.font_item,parent,false);
        return new FontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FontViewHolder holder, int position) {
        if(rowSelected == position){
            holder.image_check.setVisibility(View.VISIBLE);
        }else{
            holder.image_check.setVisibility(View.INVISIBLE);
        }

        Typeface typeface =  Typeface.createFromAsset(context.getAssets(),new StringBuilder("fonts/")
                .append(fontList.get(position)).toString());

        holder.txt_font_demo.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return fontList.size();
    }


    public class FontViewHolder extends RecyclerView.ViewHolder{
        TextView txt_font_demo;
        ImageView image_check;

        public FontViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_font_demo = itemView.findViewById(R.id.txt_font_demo);
            image_check = itemView.findViewById(R.id.image_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFontSelected(fontList.get(getAdapterPosition()));
                    rowSelected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface FontAdapterClickListener{
        public void onFontSelected(String fontName);
    }
}
