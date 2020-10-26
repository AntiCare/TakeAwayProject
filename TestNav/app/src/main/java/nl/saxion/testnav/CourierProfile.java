package nl.saxion.testnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.testnav.models.Courier;
import nl.saxion.testnav.models.Customer;

public class CourierProfile extends AppCompatActivity {
    TextView courierName,deliveries,rating;
    ImageView imageView;
    Button order,logout;

    List<Courier> couriers;

    int num;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Couriers");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_courier_profile);

        initAttributes();

        final Intent intent = getIntent();
        final String email = intent.getStringExtra("EMAIL");
        final String password = intent.getStringExtra("PASSWORD");

        //read customer from realtimeDatabase.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                couriers = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Courier courier = keyNode.getValue(Courier.class);
                    couriers.add(courier);
                }
                for (int i = 0; i <couriers.size() ; i++) {
                    if(email.equals(couriers.get(i).getEmail()) && password.equals(couriers.get(i).getPassword())) {
                        num=i;
                        courierName.setText(couriers.get(i).getFirstName()+" "+couriers.get(i).getLastName());
                       deliveries.setText(couriers.get(i).getDeliveries());
                       rating.setText(couriers.get(i).getRating());
                    }
                }
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CourierProfile.this, MainActivity.class);
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(CourierProfile.this,"log out",Toast.LENGTH_SHORT).show();
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
        deliveries=findViewById(R.id.courierDeliveryNoTxtVw);
        rating=findViewById(R.id.courierRatingTxtVw);
        imageView=findViewById(R.id.courierProfileImgVw);
        order=findViewById(R.id.courierAllOrdersBtn);
        logout=findViewById(R.id.courierLogOutBtn);
        courierName=findViewById(R.id.courierNameTxtVw);

    }



}