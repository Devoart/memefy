package com.noneofever.memescommunity.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.RegisterActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    CircleImageView circleImageView;
    TextView position,name;
    ConstraintLayout constraintLayout;
    Button signIn,signUp;
    public static String Name,Position,profilePhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        circleImageView = view.findViewById(R.id.profile_circleImageView);
        position = view.findViewById(R.id.profile_position);
        name = view.findViewById(R.id.profile_name);
        constraintLayout =  view.findViewById(R.id.j_constraint);
        constraintLayout.setVisibility(View.GONE);

        if(ParseUser.getCurrentUser()==null){
            circleImageView.setVisibility(View.INVISIBLE);
            position.setVisibility(View.INVISIBLE);
            name.setVisibility(View.INVISIBLE);
            constraintLayout.setVisibility(View.VISIBLE);
//            Dialog dialog = new Dialog(getContext());
//            dialog.setContentView(R.layout.sign_in_dialog);
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.setCancelable(false);

             signIn = view.findViewById(R.id.sign_in_dialog_sign_in);
//            ImageView close = view.findViewById(R.id.sign_in_dialog_close);
             signUp = view.findViewById(R.id.sign_in_dialog_sign_up);

//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    dialog.dismiss();
//                }
//            });

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RegisterActivity.class));
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RegisterActivity.class));
                }
            });

//            dialog.show();

        }else {
            circleImageView.setVisibility(View.VISIBLE);
            position.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.GONE);

            String Name = ParseUser.getCurrentUser().getString("name");
            String Position = ParseUser.getCurrentUser().getString("position");
            String profilePhoto = ParseUser.getCurrentUser().getString("profile_photo");

            name.setText(Name);
            position.setText(Position);

            if (profilePhoto.isEmpty()) {
                circleImageView.setImageResource(R.drawable.ic_baseline_account_circle_24_black);
            } else {
                Glide.with(getContext()).load(profilePhoto).placeholder(R.drawable.circlegif).into(circleImageView);
            }
            constraintLayout.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(ParseUser.getCurrentUser()==null){
            circleImageView.setVisibility(View.INVISIBLE);
            position.setVisibility(View.INVISIBLE);
            name.setVisibility(View.INVISIBLE);
            constraintLayout.setVisibility(View.VISIBLE);
//            Dialog dialog = new Dialog(getContext());
//            dialog.setContentView(R.layout.sign_in_dialog);
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.setCancelable(false);

//            ImageView close = view.findViewById(R.id.sign_in_dialog_close);

//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    dialog.dismiss();
//                }
//            });

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RegisterActivity.class));
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RegisterActivity.class));
                }
            });

//            dialog.show();

        }else {
            circleImageView.setVisibility(View.VISIBLE);
            position.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.GONE);

             Name = ParseUser.getCurrentUser().getString("name");
             Position = ParseUser.getCurrentUser().getString("position");
             profilePhoto = ParseUser.getCurrentUser().getString("profile_photo");

            name.setText(Name);
            position.setText(Position);

            if (profilePhoto.isEmpty()) {
                circleImageView.setImageResource(R.drawable.ic_baseline_account_circle_24_black);
            } else {
                Glide.with(getContext()).load(profilePhoto).into(circleImageView);
            }
            constraintLayout.setVisibility(View.GONE);
        }

    }
}