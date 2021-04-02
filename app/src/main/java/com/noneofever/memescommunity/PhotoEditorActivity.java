package com.noneofever.memescommunity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.noneofever.memescommunity.Fragment.AddTextFragment;
import com.noneofever.memescommunity.Fragment.BrushFragment;
import com.noneofever.memescommunity.Fragment.EmojiFragment;
import com.noneofever.memescommunity.Interface.AddTextFragmentListener;
import com.noneofever.memescommunity.Interface.BrushFragmentListener;
import com.noneofever.memescommunity.Interface.EmojiFragmentListener;
import com.noneofever.memescommunity.Utils.BitmapUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.noneofever.memescommunity.photoeditor.OnSaveBitmap;
import com.noneofever.memescommunity.photoeditor.PhotoEditor;
import com.noneofever.memescommunity.photoeditor.PhotoEditorView;


public class PhotoEditorActivity extends AppCompatActivity implements   BrushFragmentListener, EmojiFragmentListener, AddTextFragmentListener {

    public static final String pictureName = "a.jpg";
    public static final int PERMISSION_PICK_NAME = 1000;
    public static final int PERMISSION_INSERT_IMAGE = 1001;
    public static final int CAMERA_REQUEST = 1002;
    public static final String FILE_PROVIDER_AUTHORITY = "com.example.memeproject.fileprovider";

    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;

    ConstraintLayout coordinatorLayout;
    AddTextFragment addTextFragment;
    EmojiFragment emojiFragment;

    Bitmap originalBitmap,filteredBitmap,finalBitmap;

    ImageView photoEditorBack,photoEditorCamera,photoEditorOpen,photoEditorSave,imgUndo,imgRedo,imgShare;

    CardView btn_filters_list,btn_edit,btn_brush,btn_emoji,btn_text,btn_add_image,btn_add_frame,btn_crop;

    Uri image_selected_uri;

    int brightnessFinal = 0;
    float saturationFinal = 1.0f;
    float constraintFinal = 1.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Photo Editor");

//        Typeface font = ResourcesCompat.getFont(this,R.font.joypixels_android);
//        Typeface font = Typeface.createFromAsset(getAssets(),"emojione-android.ttf");

        photoEditorView = findViewById(R.id.image_preview);
        photoEditor = new PhotoEditor.Builder(this,photoEditorView)
                .setPinchTextScalable(true)
                .build();

//        StickerView stickerView = findViewById(R.id.photo_editor_sticker);
//
//
//        TextSticker textSticker = new TextSticker(this);
//        textSticker.setText("Sticker");
//        textSticker.setTextColor(Color.BLUE);
//        textSticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
//        textSticker.resizeText();
//
//        stickerView.addSticker(textSticker);

        coordinatorLayout = findViewById(R.id.coordinator);
        btn_brush = findViewById(R.id.btn_brush);
        btn_emoji = findViewById(R.id.btn_emoji);
        btn_text = findViewById(R.id.btn_add_text);
        btn_add_image = findViewById(R.id.btn_add_image);
        btn_crop = findViewById(R.id.btn_crop);
        photoEditorCamera = findViewById(R.id.photo_editor_camera);
        photoEditorOpen = findViewById(R.id.photo_editor_open);
        photoEditorSave = findViewById(R.id.photo_editor_save);
        photoEditorBack = findViewById(R.id.photo_editor_back);
        imgUndo = findViewById(R.id.imgUndo);
        imgRedo = findViewById(R.id.imgRedo);
        imgShare = findViewById(R.id.imgShare);


        btn_brush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enable brush mode
                photoEditor.setBrushDrawingMode(true);

                BrushFragment brushFragment = BrushFragment.getInstance();
                brushFragment.setBrushFragmentListener(PhotoEditorActivity.this);
                brushFragment.show(getSupportFragmentManager(),brushFragment.getTag());
            }
        });

        btn_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiFragment = EmojiFragment.getInstance();
                emojiFragment.setListener(PhotoEditorActivity.this);
                emojiFragment.show(getSupportFragmentManager(),emojiFragment.getTag());
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTextFragment = AddTextFragment.getInstance();
                addTextFragment.setListener(PhotoEditorActivity.this);
                addTextFragment.show(getSupportFragmentManager(),addTextFragment.getTag());
            }
        });

        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImageToPicture();
            }
        });

        btn_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startCrop(image_selected_uri);
                startCrop();
            }
        });

        photoEditorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        photoEditorOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageFromGallery();
            }
        });

        photoEditorCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        photoEditorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToGallery();
            }
        });

        imgUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoEditor.undo();
            }
        });

        imgRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoEditor.redo();
            }
        });

//        imgShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shareImage();
//            }
//        });

        loadImage();

    }

//    private void shareImage() {
//        if (photoEditorView.getSource().getDrawable() == null) {
//            showSnackbar("Please save image to share");
//            return;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_STREAM, buildFileProviderUri(image_selected_uri));
//        startActivity(Intent.createChooser(intent, "Share Image"));
//    }

    private Uri buildFileProviderUri(@NonNull Uri uri) {
        return FileProvider.getUriForFile(this,
                FILE_PROVIDER_AUTHORITY,
                new File(uri.getPath()));
    }

    private void startCrop() {
        String destinationFileName = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        UCrop uCrop = null;
        if(ImageDetailsActivity.outFile != null) {
            File iss = new File(String.valueOf(ImageDetailsActivity.outFile));
            uCrop = UCrop.of(Uri.fromFile(iss), Uri.fromFile(iss));
            uCrop.start(PhotoEditorActivity.this);
        }else{
//            uCrop = UCrop.of(originalBitmap, originalBitmap);
//            uCrop = uCrop.of(image_selected_uri,image_selected_uri);
        }
    }

    protected void showSnackbar(@NonNull String message) {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void addImageToPicture() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,PERMISSION_INSERT_IMAGE);
                        }else{
                            Toast.makeText(PhotoEditorActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void loadImage(){

        Bitmap bitmap = null;

        if(ImageDetailsActivity.outFile != null){
            File iss = new File(String.valueOf(ImageDetailsActivity.outFile));

            photoEditor.clearAllViews();

            try {
                originalBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(iss));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            originalBitmap = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.a, 300, 300);
            image_selected_uri = Uri.parse(getResources().getDrawable(R.drawable.ic_launcher_background).toString());
        }

        filteredBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888,true);
        finalBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888,true);
//        photoEditorView.getSource().setImageBitmap(originalBitmap);
        Glide.with(this).load(originalBitmap).into(photoEditorView.getSource());


    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        filtersListFragment = new FiltersListFragment();
//        filtersListFragment.setListener(this);
//
//        editImageFragment = new EditImageFragment();
//        editImageFragment.setListener(this);
//
//        viewPagerAdapter.addFragment(filtersListFragment,"FILTERS");
//        viewPagerAdapter.addFragment(editImageFragment,"EDIT");
//
//        viewPager.setAdapter(viewPagerAdapter);
//    }


    private void showSaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you want to exit without saving image ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveImageToGallery();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }


    private void resetControl() {
        brightnessFinal =0;
        constraintFinal = 1.0f;
        saturationFinal = 1.0f;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.action_open){
//            openImageFromGallery();
//            return true;
//        }else if(id == R.id.action_save){
//            saveImageToGallery();
//            return true;
//        }else if(id == R.id.action_camera){
//            openCamera();
//            return true;
//        }
//        return false;
//    }

    private void openCamera() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            ContentValues values = new ContentValues();
                            values.put(MediaStore.Images.Media.TITLE,"New Picture");
                            values.put(MediaStore.Images.Media.DESCRIPTION,"Description");
                            image_selected_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_selected_uri);
                            startActivityForResult(cameraIntent,CAMERA_REQUEST);

                        }else{
                            Toast.makeText(PhotoEditorActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void openImageFromGallery() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,PERMISSION_PICK_NAME);
                        }else{
                            Toast.makeText(PhotoEditorActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public void openImageFile(){
        File iss = new File(String.valueOf(ImageDetailsActivity.outFile));

        photoEditor.clearAllViews();

        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(iss));
        } catch (IOException e) {
            e.printStackTrace();
        }

        originalBitmap.recycle();
        filteredBitmap.recycle();
        finalBitmap.recycle();

        originalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        finalBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        filteredBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
//        photoEditorView.getSource().setImageBitmap(originalBitmap);
        btn_edit.setVisibility(View.VISIBLE);
        bitmap.recycle();

//                filtersListFragment.displayThumbnail(originalBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {
            if(requestCode == PERMISSION_PICK_NAME) {
                Bitmap bitmap = BitmapUtils.getBitmapFromGallery(this, data.getData(), 800, 800);

                image_selected_uri = data.getData();

                originalBitmap.recycle();
                filteredBitmap.recycle();
                finalBitmap.recycle();

                originalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                finalBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                filteredBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                photoEditorView.getSource().setImageBitmap(originalBitmap);
                btn_edit.setVisibility(View.VISIBLE);
                bitmap.recycle();

//                filtersListFragment.displayThumbnail(originalBitmap);
            }else if(requestCode == CAMERA_REQUEST) {
                Bitmap Fbitmap = BitmapUtils.getBitmapFromGallery(this, image_selected_uri, 800, 800);

                originalBitmap.recycle();
                filteredBitmap.recycle();
                finalBitmap.recycle();

                originalBitmap = Fbitmap.copy(Bitmap.Config.ARGB_8888, true);
                finalBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                filteredBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
                photoEditorView.getSource().setImageBitmap(originalBitmap);
                btn_edit.setVisibility(View.VISIBLE);
                Fbitmap.recycle();

//                filtersListFragment.displayThumbnail(originalBitmap);
            }else if(requestCode == PERMISSION_INSERT_IMAGE){
                Bitmap Sbitmap = BitmapUtils.getBitmapFromGallery(this, data.getData(), 250, 250);
                photoEditor.addImage(Sbitmap);
            }else if(requestCode == UCrop.REQUEST_CROP){
                handleCropRequest(data);
            }
        }else if(resultCode == UCrop.RESULT_ERROR){
            handleCropError(data);
        }
    }

    private void handleCropError(Intent data) {
        final Throwable cropError = UCrop.getError(data);
        if(cropError != null){
            Toast.makeText(this, ""+cropError.getMessage(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Unexcepted Error!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleCropRequest(Intent data) {
        final Uri resultUri = UCrop.getOutput(data);
        if(resultUri != null){
            photoEditorView.getSource().setImageURI(resultUri);

            Bitmap bitmap = ((BitmapDrawable)photoEditorView.getSource().getDrawable()).getBitmap();
            originalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
            filteredBitmap = originalBitmap;
            finalBitmap = originalBitmap;
        }else{
            Toast.makeText(this, "Cannot retrive crop image", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToGallery(){
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted()){
                            photoEditor.saveAsBitmap(new OnSaveBitmap() {
                                @Override
                                public void onBitmapReady(Bitmap saveBitmap) {
                                    try {

                                        photoEditorView.getSource().setImageBitmap(saveBitmap);

                                        final Uri path = BitmapUtils.insertImage(getContentResolver(), saveBitmap,
                                                System.currentTimeMillis() + ".jpeg", null);

                                        Log.d("pat :  ",getRealPathFromUri(getApplicationContext(),path));

                                        showSnackbar("Photo Saved in  "+ path);



//                                        if (!TextUtils.isEmpty(path)) {
//                                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Image Saved to Gallery",
//                                                    Snackbar.LENGTH_LONG).setAction("OPEN", new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    openImage(path);
//                                                }
//                                            });
//
//                                            Toast.makeText(PhotoEditorActivity.this, "Image Saved to Gallery", Toast.LENGTH_SHORT).show();
//
//                                            snackbar.show();
//                                        }else{
//                                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Unable to saved image",
//                                                    Snackbar.LENGTH_LONG);
//
//                                            snackbar.show();
//                                            Toast.makeText(PhotoEditorActivity.this, "Unable to saved image", Toast.LENGTH_SHORT).show();
//                                        }
                                        Log.d("s",path.toString());
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {

                                }
                            });
                        }else{
                            Toast.makeText(PhotoEditorActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void openImage(String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path),"image/*");
        startActivity(intent);
    }

    @Override
    public void onBrushSizeChangedListener(float size) {
        photoEditor.setBrushSize(size);
    }

    @Override
    public void onBrushOpacityChangedListener(int opacity) {
        photoEditor.setOpacity(opacity);
    }

    @Override
    public void onBrushColorChangedListener(int color) {
        photoEditor.setBrushColor(color);
    }

    @Override
    public void onBrushStateChangedListener(boolean isEraser) {
        if(isEraser){
            photoEditor.brushEraser();
        }else {
            photoEditor.setBrushDrawingMode(true);
        }
    }

    @Override
    public void onEmojiSelected(String emoji) {
        photoEditor.addEmoji(emoji);
        emojiFragment.dismiss();
    }
//
//    @Override
//    public void onAddTextButtonClick(String text, int color) {
//        photoEditor.addText(text,color);
//    }

    @Override
    public void onAddTextButtonClick(Typeface typeface, String text, int color) {
        if(!text.isEmpty()){
            photoEditor.addText(typeface,text,color);
        }else if(text.isEmpty()){
            Toast.makeText(this, "Please write a text", Toast.LENGTH_SHORT).show();
        }
        addTextFragment.dismiss();
    }

//
//    @Override
//    public void onAddFrame(int frame) {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),frame);
//        photoEditor.addImage(bitmap);
//        frameFragment.dismiss();
//    }

    @Override
    public void onBackPressed() {
        if (!photoEditor.isCacheEmpty()) {
            showSaveDialog();
        } else {
            super.onBackPressed();
        }
    }
}