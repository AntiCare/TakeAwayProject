package nl.saxion.testnav;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.saxion.testnav.models.Admin;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView resName;
    TextView rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        resName = findViewById(R.id.rDetailsNameTxtView);
        rating = findViewById(R.id.rDetailsRatingTxtVw);

        Intent intent=getIntent();
        String restaurantName=intent.getStringExtra("RN");
        resName.setText(restaurantName);

        for (int i = 0; i < Admin.getRestaurants().size(); i++) {
            if (restaurantName.equals(Admin.getRestaurants().get(i).getName())) {
                rating.setText(Admin.getRestaurants().get(i).getRatings().toString());
            }
        }

    }
}