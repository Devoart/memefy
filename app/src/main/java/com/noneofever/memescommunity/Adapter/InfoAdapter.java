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
import com.noneofever.memescommunity.model.InfoModel;
import com.noneofever.memescommunity.model.TemplateModel;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoAdapter  extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{
    private List<InfoModel> templateModelList;

    public InfoAdapter(List<InfoModel> templateModelList) {
        this.templateModelList = templateModelList;
    }

    @NonNull
    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
        return new InfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
        String imageUrl = templateModelList.get(position).getImage();
        String name = templateModelList.get(position).getName();
        String positiont = templateModelList.get(position).getPosition();
        String description = templateModelList.get(position).getDescription();

        holder.setAll(imageUrl,name,positiont,description);
    }

    @Override
    public int getItemCount() {
        return templateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,position,description;
        CircleImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.info_circleimageview);
            name = itemView.findViewById(R.id.info_name);
            position = itemView.findViewById(R.id.info_position);
            description = itemView.findViewById(R.id.info_description);


//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(115, 115));

        }

        public void setAll(String imageUrl,String nametv,String positiontv,String descriptiontv){

            name.setText(nametv);
            position.setText(positiontv);
            description.setText(descriptiontv);

            Glide.with(itemView.getContext()).load(imageUrl).placeholder(R.drawable.circlegif).into(imageView);


        }
    }
}
