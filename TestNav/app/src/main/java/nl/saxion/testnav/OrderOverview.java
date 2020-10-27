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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.saxion.testnav.models.Courier;
import nl.saxion.testnav.models.OrderItem;
import nl.saxion.testnav.models.Restaurant;

public class OrderOverview extends AppCompatActivity {
    static String orderID;


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRefer = database.child("Couriers");
    Courier courier;
    List<Courier> drivers;

    private ImageButton chatBtn;
    private RecyclerView mRecyclerView;
    private Button cancelOrder;
    private TextView orderStatus, courierNam;
    boolean status = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_overview);
        //init
        initAttributes();
        //get orderID
        Intent intent = getIntent();
         orderID = intent.getStringExtra("orderID");

        //read the order from firebase.
        new FirebaseDatabaseHelper().readOrders(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<OrderItem> orderItems, List<String> keys) {
                new OrderOverViewConfig().setConfig(mRecyclerView,OrderOverview.this,orderItems,keys);
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
        //set order status.
        if(!status) {
            orderStatus.setText("open");
        }else {
            orderStatus.setText("closed");
        }
        //click the button, the order will be deleted.
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference nodeKeyRef = FirebaseDatabase.getInstance().getReference().child("Order").child(OrderOverview.orderID).child("OrderItem");
                nodeKeyRef.removeValue();
            }
        });



        //read courier from firebase.
        myRefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                drivers = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Courier courier = keyNode.getValue(Courier.class);
                    drivers.add(courier);
                }
                //Randomly arrange a courier
                courier = drivers.get(randomCourier());
                final DatabaseReference decidedUser = database.child("Order").child(orderID);
                decidedUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshots) {
                        if (!snapshots.child("OrderUser").exists()) {
                            decidedUser.child("OrderUser").child("CourierID").setValue(courier.getId());
                            if(MainActivity.customer!=null){
                                decidedUser.child("OrderUser").child("CustomerID").setValue(MainActivity.customer.getId());
                            } else {
                                decidedUser.child("OrderUser").child("CustomerID").setValue("withoutLogIn");
                            }
                        }
                        DatabaseReference getCourier = database.child("Order").child(orderID).child("OrderUser").child("CourierID");
                        getCourier.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String courierid= snapshot.getValue().toString();
                                for (Courier e:drivers
                                ) {
                                    if(e.getId().equals(courierid)) {
                                        courierNam.setText(e.getFirstName()+e.getLastName());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Open chat gui with courier
                chatBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                        intent.putExtra("ReceiverID",courier.getId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //INIT ALL ATTRIBUTES BELOW
    private void initAttributes() {
        courierNam = findViewById(R.id.courierNameRatingTxtVw);
        chatBtn = findViewById(R.id.chatBtn);
        mRecyclerView = (RecyclerView) findViewById(R.id.itemListVw);
        cancelOrder = findViewById(R.id.cancelOrder);
        orderStatus = findViewById(R.id.textView5);
    }

    public int randomCourier () {
        int randomCourier = (int)(Math.random()*4);
        return randomCourier;
    }



}