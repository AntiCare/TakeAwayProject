package nl.saxion.testnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.saxion.testnav.models.OrderItem;

public class OrderOverViewConfig  {
    private Context mContext;
    private OrdersAdapter mOrdersAdapter;

    public void setConfig(RecyclerView recyclerView, Context context,List<OrderItem> orderItems,List<String> keys) {
        mContext = context;
        mOrdersAdapter = new OrdersAdapter(orderItems,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mOrdersAdapter);
    }

    class OrderItemView extends RecyclerView.ViewHolder{
        private TextView RestaurantName;
        private TextView itemNumber;
        private TextView itemQuantity;

        private String key;

        public OrderItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.order_overview_item,parent,false));

            RestaurantName = (TextView)itemView.findViewById(R.id.RestaurantName);
            itemNumber =(TextView)itemView.findViewById(R.id.itemNumber);
            itemQuantity =(TextView)itemView.findViewById(R.id.itemQuantity);

        }
        public void merge(OrderItem orderItem, String key){
            RestaurantName.setText(orderItem.getRestaurantName());
            itemNumber.setText(orderItem.getItemNum());
            itemQuantity.setText(orderItem.getItemQuantity());
            this.key = key;

        }
    }
    class OrdersAdapter extends RecyclerView.Adapter<OrderItemView>{
        private List<OrderItem> myOrderList;
        private List<String> myKeys;

        public OrdersAdapter(List<OrderItem> mOrderList, List<String> mKeys) {
            this.myOrderList = mOrderList;
            this.myKeys = mKeys;
        }

        @NonNull
        @Override
        public OrderItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OrderItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderItemView holder, int position) {
            holder.merge(myOrderList.get(position),myKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return myOrderList.size();
        }
    }
}
