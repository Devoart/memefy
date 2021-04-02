package com.noneofever.memescommunity.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.noneofever.memescommunity.ImageDetailsActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.TemplateModel;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.List;

public class TemplateAdapter  extends RecyclerView.Adapter<TemplateAdapter.ViewHolder>{
    private List<TemplateModel> templateModelList;
    private int template = 0;

    public TemplateAdapter(List<TemplateModel> templateModelList, int template) {
        this.templateModelList = templateModelList;
        this.template = template;
    }

    @NonNull
    @Override
    public TemplateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview_item_imageview,parent,false);
        return new TemplateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateAdapter.ViewHolder holder, int position) {
        String imageUrl = templateModelList.get(position).getImageUrl();
        String title = templateModelList.get(position).getTitle();

        holder.setAll(imageUrl,title,position);
    }

    @Override
    public int getItemCount() {
        return templateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RoundedImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.template_item_imageView);
            cardView = itemView.findViewById(R.id.template_item_cardview);
            textView = itemView.findViewById(R.id.template_item_title);


//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(115, 115));

        }

        public void setAll(String imageUrl,String title,int position){

            textView.setText(title);

            Glide.with(itemView.getContext()).load(imageUrl).placeholder(R.drawable.banner).into(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ImageDetailsActivity.class);
                    productDetailsIntent.putExtra("image", imageUrl);
                    productDetailsIntent.putExtra("title", title);
                    productDetailsIntent.putExtra("position", position);
                    productDetailsIntent.putExtra("template", template);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
