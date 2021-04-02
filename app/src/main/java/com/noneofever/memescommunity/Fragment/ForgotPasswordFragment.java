package com.noneofever.memescommunity.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.noneofever.memescommunity.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import static com.noneofever.memescommunity.Fragment.SignInFragment.VALID_EMAIL_ADDRESS_REGEX;

public class ForgotPasswordFragment extends Fragment {

    private EditText email;
    private Button resetBtn;
    private ProgressBar progressBar;
    private ImageView back;

    public ForgotPasswordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);


        email = view.findViewById(R.id.forgot_email);
        resetBtn = view.findViewById(R.id.reset_btn);
        progressBar = view.findViewById(R.id.fg_progressBar);
        back = view.findViewById(R.id.fg_back);

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

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setError(null);
                if(VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString()).find()) {
                    progressBar.setVisibility(View.VISIBLE);
                    resetBtn.setEnabled(false);

                    ParseUser.requestPasswordResetInBackground(email.getText().toString().trim(),
                            new RequestPasswordResetCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        // An email was successfully sent with reset instructions.
                                        Toast.makeText(getContext(), "Password reset email sent successfully", Toast.LENGTH_SHORT).show();
                                        getActivity().onBackPressed();
                                    } else {
                                        // Something went wrong. Look at the ParseException to see what's up.
                                            String error = e.getMessage();
                                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                            email.setError(error);
                                            progressBar.setVisibility(View.INVISIBLE);
                                    }
                                    resetBtn.setEnabled(true);
                                }
                            });
                }else{
                    email.setError("Please provide a valid email");
                }

            }
        });
    }
}