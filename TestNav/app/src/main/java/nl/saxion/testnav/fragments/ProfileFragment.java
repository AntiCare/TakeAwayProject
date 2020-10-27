package nl.saxion.testnav.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import nl.saxion.testnav.CourierProfile;
import nl.saxion.testnav.CustomerProfile;
import nl.saxion.testnav.R;
import nl.saxion.testnav.SignUpActivity;
import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Courier;
import nl.saxion.testnav.models.Customer;

public class ProfileFragment extends Fragment {
     Button loginBtn;
     MaterialEditText emailEditTxt, passwordEditTxt;
     TextView signUpQ;

        FirebaseAuth auth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initAttributes(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String input_email = emailEditTxt.getText().toString();
                final String input_password = passwordEditTxt.getText().toString();

                if (TextUtils.isEmpty(input_email)) {
                    Toast.makeText(getActivity(),"Please enter email",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(input_password)) {
                    Toast.makeText(getActivity(),"Please enter password",Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(input_email,input_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() && input_email.contains("driver")) {
                                        Intent intent = new Intent(getActivity(),CourierProfile.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("EMAIL",input_email);
                                        intent.putExtra("PASSWORD",input_password);
                                        startActivity(intent);
                                    } else if(task.isSuccessful()) {
                                        Intent intent = new Intent(getActivity(),CustomerProfile.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("EMAIL",input_email);
                                        intent.putExtra("PASSWORD",input_password);
                                        startActivity(intent);

                                    }else {
                                        Toast.makeText(getActivity(),"Wrong email or password, please try again!",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }
        });










    }

    private void initAttributes(View view) {
        auth = FirebaseAuth.getInstance();

        loginBtn = view.findViewById(R.id.logInBtn);
        emailEditTxt = view.findViewById(R.id.emailEditTxt);
        passwordEditTxt = view.findViewById(R.id.passwordEditTxt);

        signUpQ = view.findViewById(R.id.signUpQueueTV);
        String click_here = getString(R.string.click_here_link);
        String signUp = getString(R.string.sign_up_queue);
        SpannableString ss = new SpannableString(signUp + " " + click_here);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan, 28, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpQ.setText(ss);
        signUpQ.setMovementMethod(LinkMovementMethod.getInstance());
    }
}