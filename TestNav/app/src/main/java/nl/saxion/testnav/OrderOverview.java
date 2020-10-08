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

import nl.saxion.testnav.models.OrderItem;
import nl.saxion.testnav.models.Restaurant;

public class OrderOverview extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Order");
    private ImageButton chatBtn;
    private RecyclerView mRecyclerView;
    private Button cancelOrder;
    private TextView orderStatus;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_overview);
        //init
        initAttributes();
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
                DatabaseReference nodeKeyRef = FirebaseDatabase.getInstance().getReference().child("Order");
                nodeKeyRef.removeValue();
            }
        });

        //Open chat gui with courier
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                //intent.putExtra( , ) add courierID or something
                startActivity(intent);
            }
        });

    }

    //INIT ALL ATTRIBUTES BELOW
    private void initAttributes() {
        chatBtn = findViewById(R.id.chatBtn);
        mRecyclerView = (RecyclerView) findViewById(R.id.itemListVw);
        cancelOrder = findViewById(R.id.cancelOrder);
        orderStatus = findViewById(R.id.textView5);
    }



}