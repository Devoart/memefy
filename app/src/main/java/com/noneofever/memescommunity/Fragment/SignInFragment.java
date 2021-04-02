package com.noneofever.memescommunity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.noneofever.memescommunity.MainActivity;
import com.noneofever.memescommunity.R;
import com.noneofever.memescommunity.RegisterActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.regex.Pattern;

public class SignInFragment extends Fragment {

    private EditText email,password;
    private Button signIn,createAccount;
    private TextView forgotPassword;
    private ProgressBar progressBar;
    private ImageView close;


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public SignInFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        forgotPassword = view.findViewById(R.id.sign_in_forgot_password);
        signIn = view.findViewById(R.id.sign_in_sign_in_btn);
        createAccount = view.findViewById(R.id.sign_in_sign_up_btn);
        progressBar = view.findViewById(R.id.sign_in_progressBar);
        close = view.findViewById(R.id.sign_in_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Parse.initialize(new Parse.Configuration.Builder(getContext()).applicationId("Gabwire").clientKey("MASTER").server("http://localhost:1337/parse").build());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setError(null);
                password.setError(null);

                if(email.getText().toString().isEmpty()){
                    email.setError("Required");
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Required");
                    return;
                }

//                if(VALID_EMAIL_ADDRESS_REGEX){
//                    progressBar.setVisibility(View.VISIBLE);
//                }else

//                    if(email.getText().toString().matches("\\d{11}")){
                    progressBar.setVisibility(View.VISIBLE);

//                    FirebaseFirestore.getInstance().collection("users").whereEqualTo("phone",emailOrPhone.getText().toString()).get()
//                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    if(task.isSuccessful()){
//                                        List<DocumentSnapshot> document = task.getResult().getDocuments();
//                                        if(document.isEmpty()){
//                                            emailOrPhone.setError("phone not found");
//                                            progressBar.setVisibility(View.INVISIBLE);
//                                            return;
//                                        }else{
//                                            String email = document.get(0).get("email").toString();
//                                            login(email);
//                                        }
//                                    }else{
//                                        String error = task.getException().getMessage();
//                                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.INVISIBLE);
//                                    }
//                                }
//                            });

                    ParseUser.logInInBackground(email.getText().toString().trim(), password.getText().toString().trim(), new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                // Hooray! The user is logged in.
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                // Signup failed. Look at the ParseException to see what happened.
                                String error = e.getMessage();
                                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//                }
//                else {
////                    email.setError("Please enter a valid Email");
//                    return;
//                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterActivity)getActivity()).setFragment(new SignUpaFragment());
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterActivity)getActivity()).setFragment(new ForgotPasswordFragment());
            }
        });
    }

}