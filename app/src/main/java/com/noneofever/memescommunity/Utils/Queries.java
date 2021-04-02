package com.noneofever.memescommunity.Utils;//package com.example.memeproject.Utils;

import android.content.Context;
import android.widget.Toast;

import com.noneofever.memescommunity.Adapter.InfoAdapter;
import com.noneofever.memescommunity.CategoryActivity;
import com.noneofever.memescommunity.CategorySeeAllActivity;
import com.noneofever.memescommunity.Fragment.EditFragment;
import com.noneofever.memescommunity.Fragment.HomeFragment;
import com.noneofever.memescommunity.InfoActivity;
import com.noneofever.memescommunity.MainActivity;
import com.noneofever.memescommunity.TemplateActivity;
import com.noneofever.memescommunity.TrendingActivity;
import com.noneofever.memescommunity.model.CategoryModel;
import com.noneofever.memescommunity.model.ImageModel;
import com.noneofever.memescommunity.model.InfoModel;
import com.noneofever.memescommunity.model.TemplateModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Queries {

    public static void loadCategoryNavigation(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Category");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("categoryicon");
                        String name = score.getString("categoryName");
                        int number = (int) score.getLong("categoryNumber");

                        if(HomeFragment.categoryModelList.size() != 9) {
                            HomeFragment.categoryModelList.add(new CategoryModel(name,imageUrl,number));
                        }
                        HomeFragment.categoryAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static void loadCategorySeeAllActivity(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Category");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("categoryicon");
                        String name = score.getString("categoryName");
                        int number = (int) score.getLong("categoryNumber");

                         CategorySeeAllActivity.categoryModelList.add(new CategoryModel(name,imageUrl,number));

                        CategorySeeAllActivity.categoryAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public static void loadTemplateNavigation(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Template");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("url");
                        String title = score.getString("title");

                        if(EditFragment.templateModelList.size() != 12) {
                            EditFragment.templateModelList.add(new TemplateModel(imageUrl,title));
                        }
                        EditFragment.templateAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadTrendingNavigation(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Trending");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("url");
                        String title = score.getString("title");

                        if(EditFragment.trendingModelList.size() != 12) {
                            EditFragment.trendingModelList.add(new TemplateModel(imageUrl,title));
                        }
                        EditFragment.trendingAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadTemplateCategory(final Context context,int numberl){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Template");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("url");
                        String title = score.getString("title");
                        int number = (int) score.getLong("category");

                        if(numberl == number) {
                            CategoryActivity.templateModelList.add(new TemplateModel(imageUrl,title));
                        }
                        CategoryActivity.imageAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadTemplateNavigationActivity(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Template");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
                        String imageUrl = score.getString("url");
                        String title = score.getString("title");

                        TemplateActivity.templateModelListActivity.add(new TemplateModel(imageUrl,title));

                        TemplateActivity.imageAdapterTemplate.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static void loadTrendingNavigationActivity(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Trending");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("url");
                        String title = score.getString("title");

                        TrendingActivity.templateModelListActivity.add(new TemplateModel(imageUrl,title));

                        TrendingActivity.imageAdapterTemplate.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadBanner(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Banner");
        query.setLimit(6);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String imageUrl = score.getString("url");


                        HomeFragment.imageModelList.add(new ImageModel(imageUrl));


                        HomeFragment.adapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static void loadInfoNavigation(final Context context){
        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Info");
        query.setLimit(10000000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    for (ParseObject score : objects) {
//                        String username = score.getString("username");
                        String name = score.getString("name");
                        String position = score.getString("position");
                        String description = score.getString("description");
                        String image = score.getString("image");


                        InfoActivity.infoModelList.add(new InfoModel(name,position,description,image));
                        InfoActivity.infoAdapter.notifyDataSetChanged();
                    }
                }else{
                    String error = e.getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//
//    public static void loadHorizontalList(final Context context){
//        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("HorizontalTemplate");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e == null) {
//                    for (ParseObject score : objects) {
////                        String username = score.getString("username");
//                        String imageUrl = score.getString("url");
//
//                        horizontalList.add(new ImageModel(imageUrl));
//                        MainActivity.adapter.notifyDataSetChanged();
//                    }
//                }else{
//                    String error = e.getMessage();
//                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public static void loadSliderList(final Context context){
//        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Banner");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e == null) {
//                    for (ParseObject score : objects) {
////                        String username = score.getString("username");
//                        String imageUrl = score.getString("url");
//
//                        sliderList.add(new ImageModel(imageUrl));
//                        MainActivity.adapter.notifyDataSetChanged();
//                    }
//                }else{
//                    String error = e.getMessage();
//                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
}