package com.noneofever.memescommunity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.noneofever.memescommunity.Adapter.TemplateAdapter;
import com.noneofever.memescommunity.PhotoEditorActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.TemplateActivity;
import com.noneofever.memescommunity.TrendingActivity;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class EditFragment extends Fragment {

    ConstraintLayout template;
    TextView seeAllTemplates,seeAllTrending;
    Button start;
    RecyclerView recyclerView,seeAllRecyclerView;

    public static TemplateAdapter templateAdapter;
    public static TemplateAdapter trendingAdapter;
    public static List<TemplateModel> templateModelList;
    public static List<TemplateModel> trendingModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);


        seeAllTemplates = view.findViewById(R.id.edit_templates_see_all);
        recyclerView = view.findViewById(R.id.edit_templates_recyclerview);
        seeAllTrending = view.findViewById(R.id.edit_trending_see_all);
        seeAllRecyclerView = view.findViewById(R.id.edit_trending_recyclerview);
        start = view.findViewById(R.id.photo_editor_start);

        templateModelList = new ArrayList<>();
        trendingModelList = new ArrayList<>();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PhotoEditorActivity.class));
            }
        });

        seeAllTemplates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TemplateActivity.class));
            }
        });

        seeAllTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TrendingActivity.class));
            }
        });


        Queries.loadTemplateNavigation(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        templateAdapter = new TemplateAdapter(templateModelList,1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(templateAdapter);
        templateAdapter.notifyDataSetChanged();


        Queries.loadTrendingNavigation(getContext());


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        trendingAdapter = new TemplateAdapter(trendingModelList,2);
        seeAllRecyclerView.setLayoutManager(linearLayoutManager1);
        seeAllRecyclerView.setAdapter(trendingAdapter);
        trendingAdapter.notifyDataSetChanged();



        return view;
    }
}