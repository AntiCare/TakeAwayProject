package nl.saxion.testnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.rengwuxian.materialedittext.MaterialEditText;

import nl.saxion.testnav.models.ACCOUNT_STATUS;
import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Customer;

public class CustomerProfile extends Fragment {

    private String e;
    private MaterialEditText address, phoneNo, email, password;
    private RadioButton online, offline;
    private Button edit;
    private Customer c;
    private TextView name;
    private ImageView profilepic;

    public CustomerProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_profile, container, false);

        initAttributes(view);


        return view;
    }

    private void initAttributes(View view) {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            e = bundle.getString("email");
        }
        edit = view.findViewById(R.id.editInfoBtn);
        online = view.findViewById(R.id.customerOnlineRadioBtn);
        offline = view.findViewById(R.id.customerOfflineRadioBtn);
        address = view.findViewById(R.id.customerAddressEditTxt);
        phoneNo = view.findViewById(R.id.phoneNoProfileEditTxt);
        email = view.findViewById(R.id.emailProfileEditTxt);
        password = view.findViewById(R.id.passwordProfileEditTxt);
        edit = view.findViewById(R.id.editInfoBtn);
        name = view.findViewById(R.id.customerNameProfileTxtVw);
        profilepic = view.findViewById(R.id.customerProfileImgVw);
        //TODO: TO BE CHANGED TO FETCH INFORMATION FROM THE FIREBASE
        c = (Customer) Admin.getAccount(e);

        if (c != null) {
            if (!c.getStreetAddress().isEmpty())
                address.setText(c.getStreetAddress() + ", " + c.getZipcode() + ", " + c.getCity());
            if (!c.getPhoneNo().isEmpty()) phoneNo.setText(c.getPhoneNo());
            email.setText(c.getEmail());
            password.setText(c.getPassword());
            name.setText(c.getFirstName() + " " + c.getLastName());

            if (c.getStatus() == ACCOUNT_STATUS.ONLINE) online.setChecked(true);
            else offline.setChecked(true);
            if (c.getImageURL().equals("default"))
                profilepic.setImageResource(R.mipmap.ic_launcher);
            else Glide.with(getActivity()).load(c.getImageURL()).into(profilepic);
        }

    }
}