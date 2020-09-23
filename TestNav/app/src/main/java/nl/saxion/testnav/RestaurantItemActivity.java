package nl.saxion.testnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class RestaurantItemActivity extends Activity {
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_item_list);

        textView = findViewById(R.id.restaurantNameTxtVw);
        Intent intent=getIntent();
        String restaurantName=intent.getStringExtra("RN");
        textView.setText(restaurantName);
    }



}
