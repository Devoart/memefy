package com.noneofever.memescommunity.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.noneofever.memescommunity.CategoryActivity;
import com.noneofever.memescommunity.ImageDetailsActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.TemplateModel;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<TemplateModel> templateModelList;

    public SearchAdapter(List<TemplateModel> templateModelList) {
        this.templateModelList = templateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = templateModelList.get(position).getImageUrl();
        String title = templateModelList.get(position).getTitle();

        holder.setAll(imageUrl,title);
    }

    @Override
    public int getItemCount() {
        return templateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RoundedImageView imageView;
        ConstraintLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.search_item_imageView);
            cardView = itemView.findViewById(R.id.search_constraint);
            textView = itemView.findViewById(R.id.search_item_title);


//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(115, 115));

        }

        public void setAll(String imageUrl,String title){

            textView.setText(title);

            Glide.with(itemView.getContext()).load(imageUrl).placeholder(R.drawable.banner).into(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ImageDetailsActivity.class);
                    productDetailsIntent.putExtra("image", imageUrl);
                    productDetailsIntent.putExtra("title", title);
                    productDetailsIntent.putExtra("sample", true);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
