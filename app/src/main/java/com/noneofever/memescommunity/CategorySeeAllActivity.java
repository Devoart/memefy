package com.noneofever.memescommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.noneofever.memescommunity.Adapter.CategoryAdapter;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategorySeeAllActivity extends AppCompatActivity {

    public static CategoryAdapter categoryAdapter;
    public static List<CategoryModel> categoryModelList;

    GridView gridView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_see_all);

        categoryModelList = new ArrayList<>();

        gridView = findViewById(R.id.categoryGridViewSeeAllActivity);
        back = findViewById(R.id.categorySeeAllActivityBack);

        Queries.loadCategorySeeAllActivity(this);

        categoryAdapter = new CategoryAdapter(this,categoryModelList);
        gridView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}