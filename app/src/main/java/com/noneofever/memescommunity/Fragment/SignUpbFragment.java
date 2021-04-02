package com.noneofever.memescommunity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.noneofever.memescommunity.MainActivity;
import com.noneofever.memescommunity.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpbFragment extends Fragment {


    private EditText password,confirmPassword;
    private ImageView signUp,back;
    private ProgressBar progressBar;

    public SignUpbFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_upb, container, false);


        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);
        signUp = view.findViewById(R.id.sign_up_b_next);
        back = view.findViewById(R.id.sign_up_b_back);
        progressBar = view.findViewById(R.id.sign_up_b_progressbar);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setError(null);
                confirmPassword.setError(null);
                if(password.getText().toString().isEmpty()) {
                    password.setError("Required");
                    return;
                }
                if(confirmPassword.getText().toString().isEmpty()){
                    confirmPassword.setError("Required");
                    return;
                }
                if(!password.getText().toString().equals(confirmPassword.getText().toString())){
                    confirmPassword.setError("Password mismatched");
                    return;
                }

                createAccount();
            }
        });
    }



    private void createAccount() {
        progressBar.setVisibility(View.VISIBLE);

        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(SignUpaFragment.usernameS);
        parseUser.put("name",SignUpaFragment.nameS);
        parseUser.setPassword(password.getText().toString());
        parseUser.setEmail(SignUpaFragment.emailS);
        parseUser.put("profile_photo","");
        parseUser.put("position","Member");

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                 Intent intent = new Intent(getContext(), MainActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
                 getActivity().finish();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    String error = e.getMessage();
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}