package nl.saxion.testnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Restaurant;

public class OrderHistory extends AppCompatActivity {

    ListView orderListView;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Order");

    private ArrayList<String> orderID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        orderListView = findViewById(R.id.orderListVw);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderID = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String child = ds.getKey();
                    orderID.add(child);
                }

                orderListView.setAdapter(new ArrayAdapter<String>(OrderHistory.this, android.R.layout.simple_list_item_1, orderID));
                orderListView.setTextFilterEnabled(true);

                orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView orderid = (TextView)view;
                        Intent intent = new Intent(OrderHistory.this, OrderOverview.class);
                        intent.putExtra("orderID",orderid.getText());
                        startActivity(intent);
                    }
                });
             }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
