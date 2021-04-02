package com.noneofever.memescommunity.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.RegisterActivity;
import com.parse.ParseUser;

import static com.noneofever.memescommunity.Fragment.SignInFragment.VALID_EMAIL_ADDRESS_REGEX;

public class SignUpaFragment extends Fragment {

    private EditText name,username,email;
    private TextView signIntv;
    private ImageView back,next;


    public static String nameS,usernameS,emailS;

    public SignUpaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_upa, container, false);


        name = view.findViewById(R.id.sign_up_name);
        username = view.findViewById(R.id.sign_up_username);
        email = view.findViewById(R.id.sign_up_email);
        signIntv = view.findViewById(R.id.sign_up_sign_in_tv);
        back = view.findViewById(R.id.sign_up_back);
        next = view.findViewById(R.id.sign_up_next);


        signIntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).setFragment(new SignInFragment());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).setFragment(new SignUpbFragment());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }


    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        Parse.initialize(new Parse.Configuration.Builder(getContext()).applicationId("Gabwire").clientKey("MASTER").server("http://192.168.0.103:1337/parse").build());


//        firebaseAuth = FirebaseAuth.getInstance();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setError(null);
                name.setError(null);
                username.setError(null);
//                password.setError(null);
//                confirmPassword.setError(null);
                if(email.getText().toString().isEmpty()){
                    email.setError("Required");
                    return;
                }
                if(name.getText().toString().isEmpty()){
                    name.setError("Required");
                    return;
                }

                if(username.getText().toString().isEmpty()){
                    name.setError("Required");
                    return;
                }
//                if(password.getText().toString().isEmpty()) {
//                    password.setError("Required");
//                    return;
//                }
//                if(confirmPassword.getText().toString().isEmpty()){
//                    confirmPassword.setError("Required");
//                    return;
//                }

                if(!VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString()).find()){
                    email.setError("Please enter a valid Email");
                    return;
                }
//                if(!password.getText().toString().equals(confirmPassword.getText().toString())){
//                    confirmPassword.setError("Password mismatched");
//                    return;
//                }

//                createAccount();

                nameS = name.getText().toString();
                emailS = email.getText().toString();
                usernameS = username.getText().toString();

                ((RegisterActivity)getActivity()).setFragment(new SignUpbFragment());
            }
        });
    }


//    private void createAccount() {
//        progressBar.setVisibility(View.VISIBLE);
////        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////            @Override
////            public void onComplete(@NonNull Task<AuthResult> task) {
////                if(task.isSuccessful()){
////                    ((RegisterActivity)getActivity()).setFragment(new OTPFragment());
////                }else{
////                    String error = task.getException().getMessage();
////                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//
////        firebaseAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
////            @Override
////            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
////                if(task.isSuccessful()){
////                    if(task.getResult().getSignInMethods().isEmpty()){
////                        ((RegisterActivity)getActivity()).setFragment(new OTPFragment(email.getText().toString(),phone.getText().toString(),password.getText().toString()));
////                    }else{
////                        email.setError("Email already exists");
////                        progressBar.setVisibility(View.INVISIBLE);
////                    }
////                }else{
////                    String error = task.getException().getMessage();
////                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
////                }
////                progressBar.setVisibility(View.INVISIBLE);
////            }
////        });
//
//        ParseUser parseUser = new ParseUser();
//        parseUser.setUsername(username.getText().toString());
//        parseUser.setPassword(password.getText().toString());
//        parseUser.setEmail(email.getText().toString());
//        parseUser.put("profile_photo","");
//
//        parseUser.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
////                    ((RegisterActivity)getActivity()).setFragment(new OTPFragment(email.getText().toString(),phone.getText().toString(),password.getText().toString()));
////                    ParseQuery<ParseObject> parseObjectParseQuery = new ParseQuery<ParseObject>("UserProfile");
////                    parseObjectParseQuery.getFirstInBackground(new GetCallback<ParseObject>() {
////                        @Override
////                        public void done(ParseObject object, ParseException e) {
////                            object.put("user_id",ParseUser.getCurrentUser().getObjectId());
////                            object.put("username",name.getText().toString());
////                            object.put("profile_pic","default");
////                            object.put("status","online");
////                            object.put("search","");
////                            object.put("bio","");
////
////                            object.saveInBackground();
////
////                            Intent intent = new Intent(getContext(), MainActivity.class);
////                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
////                            startActivity(intent);
////                            getActivity().finish();
////                        }
////                    });
//
//                    ParseObject parseObject = new ParseObject("UserProfile");
//                    parseObject.put("user_id",ParseUser.getCurrentUser().getObjectId());
//                    parseObject.put("username",name.getText().toString());
//                    parseObject.put("profile_pic","default");
//                    parseObject.put("status","online");
//                    parseObject.put("search","");
//                    parseObject.put("bio","");
//
//                    parseObject.saveInBackground(new SaveCallback() {
//                        @Override
//                        public void done(ParseException e) {
//                            if(e == null){
//                                Intent intent = new Intent(getContext(), MainActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                getActivity().finish();
//                            }
//                        }
//                    });
//
//
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                    String error = e.getMessage();
//                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }



}