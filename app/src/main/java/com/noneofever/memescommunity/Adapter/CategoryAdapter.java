package com.noneofever.memescommunity.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.noneofever.memescommunity.CategoryActivity;
import com.noneofever.memescommunity.ImageDetailsActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.CategoryModel;
import com.noneofever.memescommunity.model.TemplateModel;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.List;

public class CategoryAdapter  extends BaseAdapter {

    Context context;
    List<CategoryModel> templateModelList;


    public CategoryAdapter(Context context, List<CategoryModel> templateModelList) {
        this.context = context;
        this.templateModelList = templateModelList;
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

        view = mInflater.inflate(R.layout.category_item_layout, parent,false);

        RoundedImageView imageView = view.findViewById(R.id.category_item_imageView);
        ConstraintLayout cardView = view.findViewById(R.id.category_item_cardview);
        TextView textView = view.findViewById(R.id.category_item_title);

//        Glide.with(context).load(templateModelList.get(position).getCategoryImgageLink()).placeholder(R.drawable.banner).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(115, 115));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productDetailsIntent = new Intent(parent.getContext(), CategoryActivity.class);
                productDetailsIntent.putExtra("number", templateModelList.get(position).getCategoryNumber());
                productDetailsIntent.putExtra("title", templateModelList.get(position).getCategoryName());
                parent.getContext().startActivity(productDetailsIntent);
            }
        });

        textView.setText(templateModelList.get(position).getCategoryName());

        return view;
    }
}
