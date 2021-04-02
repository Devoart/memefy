package com.noneofever.memescommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.noneofever.memescommunity.Adapter.ImageAdapter;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.CategoryModel;
import com.noneofever.memescommunity.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    public static List<TemplateModel> templateModelList;
    public static ImageAdapter imageAdapter;
    GridView gridView;
    TextView textView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        gridView = findViewById(R.id.categoryGridViewActivity);
        textView = findViewById(R.id.categoryNameActivity);
        back = findViewById(R.id.categoryActivityBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int number = getIntent().getIntExtra("number",0);
        String title  = getIntent().getStringExtra("title");

        textView.setText(title);

        templateModelList = new ArrayList<>();

        Queries.loadTemplateCategory(this,number);

        imageAdapter = new ImageAdapter(this,templateModelList,number,0);
        gridView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}