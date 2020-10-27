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

import nl.saxion.testnav.models.Courier;

public class CourierOrderOverview extends AppCompatActivity {
    String courierID;
    ListView orderListView;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Order");

     ArrayList<String> orderID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier_order_overview);

        Intent intent = getIntent();
        courierID = intent.getStringExtra("courierID");



        orderListView = findViewById(R.id.orderListVw);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderID= new ArrayList<>();
                for ( final DataSnapshot ds : dataSnapshot.getChildren()) {
                    String courierid = dataSnapshot.child(ds.getKey()).child("OrderUser").child("CourierID").getValue().toString();
                    if (courierid.equals(courierID)) {
                               String child = myRef.child(ds.getKey()).getKey();
                               orderID.add(child);
                           }
                }

                orderListView.setAdapter(new ArrayAdapter<String>(CourierOrderOverview.this, android.R.layout.simple_list_item_1,orderID));
                orderListView.setTextFilterEnabled(true);

                orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView orderid = (TextView)view;
                        Intent intent = new Intent(CourierOrderOverview.this, CourierOrderOverviewDetial.class);
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
