package com.noneofever.memescommunity.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.noneofever.memescommunity.ImageDetailsActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.TemplateModel;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter  extends BaseAdapter {

    Context context;
    List<TemplateModel> templateModelList;
    int category = 0;
    int tempate = 0;


    public ImageAdapter(Context context, List<TemplateModel> templateModelList, int category, int tempate) {
        this.context = context;
        this.templateModelList = templateModelList;
        this.category = category;
        this.tempate = tempate;
    }

    @Override
    public int getCount() {
        return templateModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return templateModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            view = mInflater.inflate(R.layout.template_item_layout, parent,false);

            RoundedImageView imageView = view.findViewById(R.id.template_item_imageView);
            CardView cardView = view.findViewById(R.id.template_item_cardview);
            TextView textView = view.findViewById(R.id.template_item_title);

            Glide.with(context).load(templateModelList.get(position).getImageUrl()).placeholder(R.drawable.banner).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(115, 115));

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(category == 0 && tempate == 0) {
                        Intent productDetailsIntent = new Intent(parent.getContext(), ImageDetailsActivity.class);
                        productDetailsIntent.putExtra("image", templateModelList.get(position).getImageUrl());
                        productDetailsIntent.putExtra("title", templateModelList.get(position).getTitle());
                        productDetailsIntent.putExtra("position", position);
                        parent.getContext().startActivity(productDetailsIntent);
                    }else if(category != 0 && tempate == 0){
                        Intent productDetailsIntent = new Intent(parent.getContext(), ImageDetailsActivity.class);
                        productDetailsIntent.putExtra("image", templateModelList.get(position).getImageUrl());
                        productDetailsIntent.putExtra("title", templateModelList.get(position).getTitle());
                        productDetailsIntent.putExtra("position", position);
                        productDetailsIntent.putExtra("category", category);
                        parent.getContext().startActivity(productDetailsIntent);
                    }else if(category == 0 && tempate == 3){

                        Intent productDetailsIntent = new Intent(parent.getContext(), ImageDetailsActivity.class);
                        productDetailsIntent.putExtra("image", templateModelList.get(position).getImageUrl());
                        productDetailsIntent.putExtra("title",  templateModelList.get(position).getTitle());
                        productDetailsIntent.putExtra("position", position);
                        productDetailsIntent.putExtra("template", tempate);
                        parent.getContext().startActivity(productDetailsIntent);
                    }
                }
            });

            textView.setText(templateModelList.get(position).getTitle());

        return view;
    }
}
