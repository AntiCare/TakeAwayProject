package nl.saxion.testnav.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rengwuxian.materialedittext.MaterialEditText;

import nl.saxion.testnav.CourierProfile;
import nl.saxion.testnav.CustomerProfile;
import nl.saxion.testnav.R;
import nl.saxion.testnav.SignUpActivity;
import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Courier;
import nl.saxion.testnav.models.Customer;

public class ProfileFragment extends Fragment {
    private Button loginBtn;
    private MaterialEditText emailEditTxt, passwordEditTxt;
    private TextView signUpQ;

    public ProfileFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //TODO: REMOVE AFTER ESTABLISHED FIREBASE CONNECTION FOR USERS
        initCourier();
        initCustomers();

        initAttributes(view);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        return view;
    }

    //#region local data for sample
    //TODO: remove after @sasha and @tuan finish authentication through firestore
    private void initCustomers() {
        Admin.accounts.add(new Customer("jane", "123456", "jane", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("hanh", "123456", "hanh", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("andre", "123456", "andre", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("sasha", "123456", "sasha", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("yang", "123456", "yang", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("julie", "123456", "julie", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("kevin", "123456", "kevin", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("jeroen", "123456", "jeroen", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("erik", "123456", "erik", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
        Admin.accounts.add(new Customer("idfk", "123456", "idfk", "nguyen",
                "+31 6 43 71 23 44", "61 GJL Ankersmitlaan", "7411AS", "Deventer"));
    }

    private void initCourier() {
        Admin.accounts.add(new Courier("alvin", "987654", "alvin", "smthg", "+31 6 12 36 54 78"));
        Admin.accounts.add(new Courier("potato", "987654", "potato", "smthg", "+31 6 12 36 54 78"));
        Admin.accounts.add(new Courier("potahtoe", "987654", "potahtoe", "smthg", "+31 6 12 36 54 78"));
        Admin.accounts.add(new Courier("water", "987654", "water", "smthg", "+31 6 12 36 54 78"));
        Admin.accounts.add(new Courier("watah", "987654", "watah", "smthg", "+31 6 12 36 54 78"));
    }


    private void signIn() {
        String email = emailEditTxt.getText().toString();
        String password = passwordEditTxt.getText().toString();
        FragmentManager manager;
        Bundle bundle = new Bundle();
        if (Admin.checkCredential(email, password)) {
            if (Admin.checkAccountType(email)) {
                CustomerProfile customerFragment = new CustomerProfile();
                bundle.putString("email", email);
                customerFragment.setArguments(bundle);
                manager = getParentFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment, customerFragment, customerFragment.getTag())
                        .commit();
            } else {
                CourierProfile courierFragment = new CourierProfile();
                bundle.putString("email", email);
                courierFragment.setArguments(bundle);
                manager = getParentFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment, courierFragment, courierFragment.getTag())
                        .commit();
            }
        } else {
            passwordEditTxt.setError("Invalid credential");
        }
    }
    //#endregion

    private void initAttributes(View view) {
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