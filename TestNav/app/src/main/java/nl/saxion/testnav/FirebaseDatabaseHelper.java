package nl.saxion.testnav;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.testnav.models.OrderItem;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceOrders;
    private List<OrderItem> orders = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<OrderItem> orderItems, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceOrders = mDatabase.getReference("Order");
    }

    public void readOrders(final DataStatus dataStatus) {
        mReferenceOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                List<String> keys= new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    OrderItem orderItem = keyNode.getValue(OrderItem.class);
                    orders.add(orderItem);
                }
                dataStatus.DataIsLoaded(orders,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
