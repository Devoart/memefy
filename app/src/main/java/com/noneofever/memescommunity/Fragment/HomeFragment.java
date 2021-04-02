package com.noneofever.memescommunity.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.noneofever.memescommunity.Adapter.CategoryAdapter;
import com.noneofever.memescommunity.Adapter.ImageAdapter;
import com.noneofever.memescommunity.Adapter.SliderAdapter;
import com.noneofever.memescommunity.CategoryActivity;
import com.noneofever.memescommunity.CategorySeeAllActivity;
import com.noneofever.memescommunity.InfoActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.TemplateActivity;
import com.noneofever.memescommunity.TrendingActivity;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.CategoryModel;
import com.noneofever.memescommunity.model.ImageModel;
import com.noneofever.memescommunity.model.TemplateModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    GridView gridView,gridviewCategory;
    public static List<TemplateModel> templateModelList;
    public static List<ImageModel> imageModelList;
    public static List<CategoryModel> categoryModelList;
    public static ImageAdapter imageAdapter;
    public static SliderAdapter adapter;
    public static CategoryAdapter categoryAdapter;

    private ImageView home,edit,search,profile,infoicon;
    private Button SeeAllBtnCategory,SeeAllbtn;


    private SwipeRefreshLayout swipeRefreshLayout;


    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        templateModelList = new ArrayList<>();
        imageModelList = new ArrayList<>();
//        imageModelList = new ArrayList<>();
        categoryModelList = new ArrayList<>();

//        gridView = view.findViewById(R.id.grid_view);
//        SeeAllbtn = view.findViewById(R.id.template_see_all_btn);

        gridviewCategory = view.findViewById(R.id.category_grid_view);
        SeeAllBtnCategory = view.findViewById(R.id.category_seeall_imageView);
        infoicon = view.findViewById(R.id.infoicon);

        MobileAds.initialize(getContext(),
                "ca-app-pub-8875777226062571~1969451354");

//        SeeAllbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), TemplateActivity.class));
//            }
//        });

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                templateModelList.clear();
//                imageModelList.clear();
//                categoryModelList.clear();
//                TemplateActivity.templateModelListActivity.clear();
//                TrendingActivity.templateModelListActivity.clear();
//            }
//        });

        SeeAllBtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CategorySeeAllActivity.class));
            }
        });

        infoicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InfoActivity.class));
            }
        });



//        Queries.loadTemplateNavigation(getContext());
//
//        imageAdapter = new ImageAdapter(getContext(),templateModelList);
//        gridView.setAdapter(imageAdapter);
//        imageAdapter.notifyDataSetChanged();

        Queries.loadCategoryNavigation(getContext());

        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        gridviewCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        SliderView sliderView = view.findViewById(R.id.imageSlider);

        Queries.loadBanner(getContext());

        adapter = new SliderAdapter(getContext(),imageModelList);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        adapter.notifyDataSetChanged();

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        return view;
    }
}