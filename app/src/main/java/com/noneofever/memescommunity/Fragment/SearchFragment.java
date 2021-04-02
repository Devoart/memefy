package com.noneofever.memescommunity.Fragment;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.noneofever.memescommunity.Adapter.ImageAdapter;
import com.noneofever.memescommunity.Adapter.SearchAdapter;
import com.noneofever.memescommunity.CategoryActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.model.TemplateModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    private EditText editText;
    private TextView textView;
    private RecyclerView recyclerView;

    List<TemplateModel> templateModelList;
    List<String> ids;
    Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = view.findViewById(R.id.search_text);
        textView = view.findViewById(R.id.search_tv);
        recyclerView = view.findViewById(R.id.search_recycler);

        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        templateModelList = new ArrayList<>();
        ids = new ArrayList<>();

        adapter = new Adapter(getContext(),templateModelList);

        recyclerView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    filter(v.getText().toString());
//                    return true;
//                }
//                return false;
//            }
//        });

        return view;
    }

    private void filter(String toString) {
        templateModelList.clear();
        ids.clear();

        String[] tags = toString.toLowerCase().split(" ");

        for (String ta:tags){
            ta.trim();
            ParseQuery<ParseObject> queryt =  new ParseQuery<ParseObject>("Template");
            queryt.whereContains("tags",ta).findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e == null){
                        for (ParseObject score : objects) {
                            TemplateModel templateModel = new TemplateModel(score.getString("url"),score.getString("title"));

                            if(!ids.contains(templateModel.getImageUrl())){
                                templateModelList.add(templateModel);
                                ids.add(templateModel.getImageUrl());
                            }
                        }
                        if(ta.equals(tags[tags.length-1])){
                            if(templateModelList.size() == 0){
                                textView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                templateModelList.clear();
                            }else{
                                textView.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                adapter.getFilter().filter(toString.toLowerCase());
                            }
                        }
                    }else{
                        templateModelList.clear();
                        String error = e.getMessage();
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    class Adapter extends SearchAdapter implements Filterable{

        public Adapter(Context context, List<TemplateModel> templateModelList) {
            super(templateModelList);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    return null;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    notifyDataSetChanged();
                }
            };
        }
    }
}