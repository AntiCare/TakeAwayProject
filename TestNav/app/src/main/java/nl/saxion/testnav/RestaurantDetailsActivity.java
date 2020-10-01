package nl.saxion.testnav;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import nl.saxion.testnav.models.Admin;

public class RestaurantDetailsActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Order");
    TextView resName,rating;
    ImageButton b1,b2,b3,b4,b5,b6;
    ImageView pic;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        //initialize
        Initialize();
        //get the data
        Intent intent=getIntent();
        String restaurantName=intent.getStringExtra("RN");
        final String imageURL = intent.getStringExtra("URL");
        resName.setText(restaurantName);
        new DownloadImageTask().execute(imageURL) ;
        //ImageButton click event (store the order into firebase)
        event();

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Drawable>
    {
        protected Drawable doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        protected void onPostExecute(Drawable result) {
            pic.setImageDrawable(result);
        }
    }

    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }
        return drawable ;
    }

    public void Initialize() {
        resName = findViewById(R.id.rDetailsNameTxtView);
        rating = findViewById(R.id.rDetailsRatingTxtVw);
        b1 = findViewById(R.id.item1Button);
        b2 = findViewById(R.id.item2Button);
        b3 = findViewById(R.id.item3Button);
        b4 = findViewById(R.id.item4Button);
        b5 = findViewById(R.id.item5Button);
        b6 = findViewById(R.id.item6Button);
        pic = (ImageView)this.findViewById(R.id.rDetailsImgVw) ;
    }

    public void event() {

        b1.setOnClickListener(new View.OnClickListener() {
            int number1 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item1").setValue(number1);
                number1++;
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            int number2 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item2").setValue(number2);
                number2++;
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            int number3 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item3").setValue(number3);
                number3++;
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            int number4 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item4").setValue(number4);
                number4++;
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            int number5 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item5").setValue(number5);
                number5++;
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            int number6 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()).child("item6").setValue(number6);
                number6++;
            }
        });


    }

}