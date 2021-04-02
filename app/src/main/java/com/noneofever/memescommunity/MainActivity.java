package com.noneofever.memescommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.noneofever.memescommunity.Adapter.ImageAdapter;
import com.noneofever.memescommunity.Adapter.SliderAdapter;
import com.noneofever.memescommunity.Adapter.ViewPagerAdapter;
import com.noneofever.memescommunity.Fragment.EditFragment;
import com.noneofever.memescommunity.Fragment.HomeFragment;
import com.noneofever.memescommunity.Fragment.ProfileFragment;
import com.noneofever.memescommunity.Fragment.SearchFragment;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.ImageModel;
import com.noneofever.memescommunity.model.TemplateModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button startBtn,seeAllBtn;
    private CardView cardView;
    private GridView gridView;
    public static List<TemplateModel> templateModelList;
    public static ImageAdapter imageAdapter;

    public static SliderAdapter adapter;
    private TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_viewpager);
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.homeicon);
        tabLayout.getTabAt(1).setIcon(R.drawable.searchicon);
        tabLayout.getTabAt(2).setIcon(R.drawable.editiconmenuicon);
        tabLayout.getTabAt(3).setIcon(R.drawable.profileicon);



    }


    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(),"Home");
        viewPagerAdapter.addFragment(new SearchFragment(),"Edit");
        viewPagerAdapter.addFragment(new EditFragment(),"Search");
        viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");
        viewPager.setAdapter(viewPagerAdapter);
    }
}