package com.noneofever.memescommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.noneofever.memescommunity.Adapter.ImageAdapter;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class TemplateActivity extends AppCompatActivity {

    private GridView gridView;
    public static ImageAdapter imageAdapterTemplate;
    public static List<TemplateModel> templateModelListActivity;
    private ImageView templateDetailsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        gridView = findViewById(R.id.grid_view_template);
        templateDetailsBack = findViewById(R.id.template_details_back);

        templateModelListActivity = new ArrayList<>();


//        Queries.loadTemplateNavigationActivity(this);
//
//
//        imageAdapterTemplate = new ImageAdapter(this,templateModelListActivity);
//        gridView.setAdapter(imageAdapterTemplate);
//        imageAdapterTemplate.notifyDataSetChanged();
//
//        if(templateModelListActivity.isEmpty()){
//            Queries.loadTemplateNavigationActivity(this);
//
//            imageAdapterTemplate = new ImageAdapter(this,templateModelListActivity);
//            gridView.setAdapter(imageAdapterTemplate);
//            imageAdapterTemplate.notifyDataSetChanged();
//        }else {
//            imageAdapterTemplate = new ImageAdapter(this, templateModelListActivity);
//            gridView.setAdapter(imageAdapterTemplate);
//            imageAdapterTemplate.notifyDataSetChanged();
//        }

        if(templateModelListActivity.size() == 0){
//
            templateModelListActivity.clear();
            Queries.loadTemplateNavigationActivity(this);
        }else{
//            DBqueries.loadMemeNavigation(getContext());
        }

        templateDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageAdapterTemplate = new ImageAdapter(this, templateModelListActivity,0,0);
        gridView.setAdapter(imageAdapterTemplate);
        imageAdapterTemplate.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}