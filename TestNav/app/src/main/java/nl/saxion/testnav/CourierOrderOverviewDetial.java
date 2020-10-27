package nl.saxion.testnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Customer;
import nl.saxion.testnav.models.OrderItem;
import nl.saxion.testnav.models.Restaurant;

public class CourierOrderOverviewDetial extends AppCompatActivity {
    static String orderID;


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference customer = database.child("Customers");

    Customer customers;
    List<Customer> customersList;

    private ImageButton chatBtn;
    private RecyclerView mRecyclerView;

    private TextView customerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_order_overview_detial);
        //init
        initAttributes();
        //get orderID
        Intent intent = getIntent();
        orderID = intent.getStringExtra("orderID");
        DatabaseReference myRefer = database.child("Order").child(orderID).child("OrderUser").child("CustomerID");


        //read the order from firebase.
        new FirebaseDatabaseHelperCourier().readOrders(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<OrderItem> orderItems, List<String> keys) {
                new OrderOverViewConfig().setConfig(mRecyclerView,CourierOrderOverviewDetial.this,orderItems,keys);
            }
            @Override
            public void DataIsInserted() {

            }
            @Override
            public void DataIsUpdated() {

            }
            @Override
            public void DataIsDeleted() {

            }
        });




        myRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String customerid = snapshot.getValue().toString();
                if (customerid.equals("withoutLogIn")) {
                    customerName.setText(customerid);
                    chatBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                            intent.putExtra("ReceiverID",customerid);
                            startActivity(intent);
                        }
                    });
                }else {
                    customer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            customersList = new ArrayList<>();
                            for(DataSnapshot keyNode : snapshot.getChildren()) {
                                Customer customer3 = keyNode.getValue(Customer.class);
                                customersList.add(customer3);
                            }
                            for (int i = 0; i <customersList.size() ; i++) {
                                if (customersList.get(i).getId().equals(customerid)) {
                                    customerName.setText(customersList.get(i).getFirstName()+customersList.get(i).getLastName());
                                    chatBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                                            intent.putExtra("ReceiverID",customerid);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
    //INIT ALL ATTRIBUTES BELOW
    private void initAttributes() {
        customerName = findViewById(R.id.CustomerNamee);
        chatBtn = findViewById(R.id.chatBtnn);
        mRecyclerView = (RecyclerView) findViewById(R.id.itemListVww);
    }



}
