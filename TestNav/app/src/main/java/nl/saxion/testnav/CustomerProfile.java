package nl.saxion.testnav;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Customer;
import nl.saxion.testnav.models.Restaurant;


public class CustomerProfile extends AppCompatActivity {
    List<Customer> customers;

    TextView customerName;
    MaterialEditText address,phoneNum,emailAddress,passWord ;
    Button button;
    RadioButton offline, online;
    ImageView imageView;

    int num;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Customers");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_customer_profile);

        initAttributes();
        Intent intent = getIntent();
        final String email = intent.getStringExtra("EMAIL");
        final String password = intent.getStringExtra("PASSWORD");


        //read customer from realtimeDatabase.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customers = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                   Customer customer = keyNode.getValue(Customer.class);
                    customers.add(customer);
                }
                for (int i = 0; i <customers.size() ; i++) {
                    if(email.equals(customers.get(i).getEmail()) && password.equals(customers.get(i).getPassword())) {
                        num=i;
                        customerName.setText(customers.get(i).getFirstName()+" "+customers.get(i).getLastName());
                        address.setText("Address: "+ customers.get(i).getAddress());
                        phoneNum.setText("Phone Number: "+ customers.get(i).getPhoneNum());
                        emailAddress.setText("Email: "+ customers.get(i).getEmail());
                        passWord.setText("Password: "+ customers.get(i).getPassword());
                        passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CustomerProfile.this, MainActivity.class);
                        intent.putExtra("User",customers.get(num));
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void initAttributes() {
        customerName = findViewById(R.id.customerNameProfileTxtVw);
        imageView = findViewById(R.id.customerProfileImgVw);
        address = findViewById(R.id.customerAddressEditTxt);
        phoneNum = findViewById(R.id.phoneNoProfileEditTxt);
        emailAddress = findViewById(R.id.emailProfileEditTxt);
        passWord =findViewById(R.id.passwordProfileEditTxt);
        button = findViewById(R.id.editInfoBtn);
        offline = findViewById(R.id.customerOfflineRadioBtn);
        online = findViewById(R.id.customerOnlineRadioBtn);
    }




}