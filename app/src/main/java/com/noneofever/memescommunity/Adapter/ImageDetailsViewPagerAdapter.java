package com.noneofever.memescommunity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.noneofever.memescommunity.ImageDetailsActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailsViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<TemplateModel> imageUrls = new ArrayList<>();
    public static ImageView imageView;


    public ImageDetailsViewPagerAdapter(Context context, List<TemplateModel> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = layoutInflater.inflate(R.layout.image_item, null);

        imageView = layoutScreen.findViewById(R.id.image_item_image);


//        firebaseFirestore = FirebaseFirestore.getInstance();


//        String dawdwa = getIntent().getStringExtra("MEME_ID");
//        String memeImage = ImageDetailsActivity.memeImage;
//        if(memeImage != null){
//            memeImage ="";
//            memeImage = ImageDetailsActivity.memeImage;
//        }


//
//        if (memeImage != null) {
////            int l = 0;
////            for(int i=0; i < imageUrls.size(); i++) {
////                if (imageUrls.contains(memeImage)) {
////                    position = i;
////                    Log.d("position",String.valueOf(i));
////                }
////            }
//            Glide.with(layoutScreen.getContext()).load(imageUrls.get(position).getImageUrl()).into(imageView);
//        } else if (memeImage == null) {

        Glide.with(layoutScreen.getContext()).load(imageUrls.get(position).getImageUrl()).into(imageView);
        ImageDetailsActivity.memeTitle = imageUrls.get(position).getTitle();
//
//        }

        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
