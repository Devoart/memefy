package com.noneofever.memescommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.RequestConfiguration;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.noneofever.memescommunity.Adapter.ImageDetailsViewPagerAdapter;
import com.noneofever.memescommunity.Fragment.EditFragment;
import com.noneofever.memescommunity.Utils.BitmapUtils;
import com.noneofever.memescommunity.Utils.Queries;
import com.noneofever.memescommunity.model.TemplateModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ImageDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ImageDetailsActivity" ;
    private ImageView backBtn;
    private ImageView EditLayout;
    private ImageView DownloadLayout;
    private ViewPager imageView;
    private TextView textView;

    public static String memeID,memeImage,memeTitle;
    public int category;

    private Dialog editDialog;

    public static String filename;
    public static File outFile;

//    private AdView adView;


    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;
    private String[] storage_permissions =
            {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };


    private AdView mAdView;
    List<TemplateModel> templateModelList;
    private RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
//        adView = new AdView(this);
//        adView.setAdSize(AdSize.SMART_BANNER);
//        adView.setAdUnitId("ca-app-pub-7251077034043726/6163066010");
//
//        // Create an ad request.
//        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
//
//        // Add the AdView to the view hierarchy.
//        layout.addView(adView);
//
//        // Start loading the ad.
//        adView.loadAd(adRequestBuilder.build());

        setContentView(R.layout.activity_image_details);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backBtn = findViewById(R.id.meme_image_details_back);
        EditLayout = findViewById(R.id.meme_image_details_editLayout);
        DownloadLayout = findViewById(R.id.meme_image_details_downloadLayout);
        imageView = findViewById(R.id.meme_image_details_image);
        textView = findViewById(R.id.meme_details_title);

        templateModelList = new ArrayList<>();

        MobileAds.initialize(this,
                "ca-app-pub-8875777226062571~1969451354");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView = findViewById(R.id.adView1);
        mAdView.loadAd(adRequest);

        rewardedAd = new RewardedAd(this,
                "ca-app-pub-8875777226062571/9175177471");

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);




//        firebaseFirestore = FirebaseFirestore.getInstance();


//        memeID = getIntent().getStringExtra("MEME_ID");
        memeImage = getIntent().getStringExtra("image");
        memeTitle = getIntent().getStringExtra("title");
        category = getIntent().getIntExtra("category",0);
        boolean sample = getIntent().getBooleanExtra("sample",false);
        int template = getIntent().getIntExtra("template",0);


        templateModelList.add(new TemplateModel(memeImage,memeTitle));

        Log.d("sample ",String.valueOf(sample));
        Log.d("category  ",String.valueOf(category));
        Log.d("template  ",String.valueOf(template));
        Log.d("position  ",String.valueOf(getIntent().getIntExtra("position",0)));

        textView.setText(memeTitle);

//        Glide.with(this).load(memeImage).into(imageView);
        ImageDetailsViewPagerAdapter viewPagerAdapter = null;

        if(category == 0 && sample == false && template == 0){
            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this,TemplateActivity.templateModelListActivity);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(getIntent().getIntExtra("position",0));
        }else if(category != 0 && sample == false&& template == 0){

            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this,CategoryActivity.templateModelList);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(getIntent().getIntExtra("position",0));

            viewPagerAdapter.notifyDataSetChanged();
        }else if(category == 0 && sample == true && template == 0){
            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this,templateModelList);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(0);

            viewPagerAdapter.notifyDataSetChanged();
        }else if(category == 0 && sample == false && template == 1){
            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this, EditFragment.templateModelList);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(getIntent().getIntExtra("position",0));
        }else if(category == 0 && sample == false && template == 2){
            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this, EditFragment.trendingModelList);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(getIntent().getIntExtra("position",0));
        }else if(category == 0 && sample == false && template == 3){
            viewPagerAdapter = new ImageDetailsViewPagerAdapter(this, TrendingActivity.templateModelListActivity);
            imageView.setAdapter(viewPagerAdapter);
            imageView.setCurrentItem(getIntent().getIntExtra("position",0));
        }

        viewPagerAdapter.notifyDataSetChanged();


        int position = getIntent().getIntExtra("position",0);

//        Log.d("position",String.valueOf(getIntent().getIntExtra("position",0)));
//        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();




        DownloadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showRewarded();

                if (rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            // Ad closed.
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            // User earned reward.
                        }

                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            // Ad failed to display.
                        }
                    };
                    rewardedAd.show(ImageDetailsActivity.this, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable) ImageDetailsViewPagerAdapter.imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                if ((int) Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ImageDetailsActivity.this);
                                builder.setMessage("To get storage access you have to allow us access to your sd card content.");
                                builder.setTitle("Storage");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(ImageDetailsActivity.this, storage_permissions, 0);
                                    }
                                });

                                builder.show();
                            } else {
                                ActivityCompat.requestPermissions(ImageDetailsActivity.this, storage_permissions, 0);
                            }
                        } else {
                            ActivityCompat.requestPermissions(ImageDetailsActivity.this,
                                    storage_permissions,
                                    MY_PERMISSIONS_REQUEST_STORAGE);
                        }

                    }
                }

                saveToCard(bitmap);
//
//                FileOutputStream outputStream = null;
//                File file = Environment.getExternalStorageDirectory();
//                File dir = new File(file.getAbsolutePath() + "/DCIM/Memes Community/");
//                dir.mkdirs();
//
//
//                filename = String.format("%d.png",System.currentTimeMillis());
//                outFile = new File(dir,filename);
//                try{
//                    outputStream = new FileOutputStream(outFile);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//                try{
//                    outputStream.flush();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                try{
//                    outputStream.close();
//                    Toast.makeText(ImageDetailsActivity.this, "Download completed", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }

//                saveToCard(bitmap);

                Log.d("s:",filename);
                Log.d("s", String.valueOf(outFile));
            }
        });

        EditLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showRewarded();

                if (rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            // Ad opened.
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            // Ad closed.
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            // User earned reward.
                        }

                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            // Ad failed to display.
                        }
                    };
                    rewardedAd.show(ImageDetailsActivity.this, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }

                BitmapDrawable bitmapDrawable = (BitmapDrawable) ImageDetailsViewPagerAdapter.imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                if ((int) Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImageDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ImageDetailsActivity.this);
                                builder.setMessage("To get storage access you have to allow us access to your sd card content.");
                                builder.setTitle("Storage");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(ImageDetailsActivity.this, storage_permissions, 0);
                                    }
                                });

                                builder.show();
                            } else {
                                ActivityCompat.requestPermissions(ImageDetailsActivity.this, storage_permissions, 0);
                            }
                        } else {
                            ActivityCompat.requestPermissions(ImageDetailsActivity.this,
                                    storage_permissions,
                                    MY_PERMISSIONS_REQUEST_STORAGE);
                        }

                    }
                }

                saveToCard(bitmap);

//                FileOutputStream outputStream = null;
//                File file = Environment.getExternalStorageDirectory();
//                File dir = new File(file.getAbsolutePath() + "/DCIM/Memes Community/");
//                dir.mkdirs();
//
//
//                filename = String.format("%d.png",System.currentTimeMillis());
//                outFile = new File(dir,filename);
//                try{
//                    outputStream = new FileOutputStream(outFile);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//                try{
//                    outputStream.flush();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                try{
//                    outputStream.close();
//                    Toast.makeText(ImageDetailsActivity.this, "Download completed", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                Log.d("s:",filename);
//                Log.d("s", String.valueOf(outFile));

//                saveToCard(bitmap);

                Intent intent = new Intent(ImageDetailsActivity.this,PhotoEditorActivity.class);
                intent.putExtra("i",outFile);
                startActivity(intent);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionChecker();
        memeTitle = getIntent().getStringExtra("title");

        textView.setText(memeTitle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String saveToCard(Bitmap bitmap) {

        String stored = null;

        File card = Environment.getExternalStorageDirectory();

        File folder = new File(card.getAbsolutePath() + "/DCIM/Memes Community/");//the dot makes this directory hidden to the user
        folder.mkdir();
        filename = String.format("%d.png",System.currentTimeMillis());
        outFile = new File(folder, filename);
        if (outFile.exists()) {
            outFile.delete();
        }
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream out = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            stored = "success";
            Toast.makeText(ImageDetailsActivity.this, "Download completed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stored;
    }

    private void permissionChecker(){
        if ((int) Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                        builder.setMessage("To get storage access you have to allow us access to your sd card content.");
                        builder.setTitle("Storage");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(ImageDetailsActivity.this, storage_permissions, 0);
                            }
                        });

                        builder.show();
                    } else {
                        ActivityCompat.requestPermissions(this, storage_permissions, 0);
                    }
                } else {
                    ActivityCompat.requestPermissions(ImageDetailsActivity.this,
                            storage_permissions,
                            MY_PERMISSIONS_REQUEST_STORAGE);
                }

            }
        }
    }


}