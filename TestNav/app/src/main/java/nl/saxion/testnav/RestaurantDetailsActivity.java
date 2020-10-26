package nl.saxion.testnav;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.URL;

public class RestaurantDetailsActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference a =database.getReference("Order").push();
    DatabaseReference myRef = a.child("OrderItem");
    TextView resName, Raddress;
    ImageButton b1,b2,b3,b4,b5,b6;
    ImageView pic;
    FloatingActionButton buyButton;

    int id;

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
        String address = intent.getStringExtra("ADDRESS");
        resName.setText(restaurantName);
        Raddress.setText(address);
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
        Raddress = findViewById(R.id.rDetailsRatingTxtVw);
        b1 = findViewById(R.id.item1Button);
        b2 = findViewById(R.id.item2Button);
        b3 = findViewById(R.id.item3Button);
        b4 = findViewById(R.id.item4Button);
        b5 = findViewById(R.id.item5Button);
        b6 = findViewById(R.id.item6Button);
        pic = (ImageView)this.findViewById(R.id.rDetailsImgVw);
        buyButton = findViewById(R.id.floatingActionButton);
    }

    public void event() {
//        if(resName.getText().equals("BBQ king")) {
//            b1.setImageResource(R.drawable.bbq1);
//            b2.setImageResource(R.drawable.bbq2);
//            b3.setImageResource(R.drawable.bbq3);
//            b4.setImageResource(R.drawable.bbq4);
//            b5.setImageResource(R.drawable.bbq5);
//            b6.setImageResource(R.drawable.bbq6);
//        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetailsActivity.this, OrderOverview.class);
                String orderId = myRef.getParent().getKey();
                intent.putExtra("orderID",orderId);
                System.out.println(orderId);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            int number1 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"1").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"1").child("itemNum").setValue("food1");
                myRef.child(resName.getText().toString()+"1").child("itemQuantity").setValue(String.valueOf(number1));
                number1++;
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            int number2 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"2").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"2").child("itemNum").setValue("food2");
                myRef.child(resName.getText().toString()+"2").child("itemQuantity").setValue(String.valueOf(number2));
                number2++;
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            int number3 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"3").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"3").child("itemNum").setValue("food3");
                myRef.child(resName.getText().toString()+"3").child("itemQuantity").setValue(String.valueOf(number3));
                number3++;
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            int number4 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"4").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"4").child("itemNum").setValue("food4");
                myRef.child(resName.getText().toString()+"4").child("itemQuantity").setValue(String.valueOf(number4));
                number4++;
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            int number5 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"5").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"5").child("itemNum").setValue("food5");
                myRef.child(resName.getText().toString()+"5").child("itemQuantity").setValue(String.valueOf(number5));
                number5++;
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            int number6 = 1;
            @Override
            public void onClick(View v) {
                myRef.child(resName.getText().toString()+"6").child("RestaurantName").setValue(resName.getText().toString());
                myRef.child(resName.getText().toString()+"6").child("itemNum").setValue("food6");
                myRef.child(resName.getText().toString()+"6").child("itemQuantity").setValue(String.valueOf(number6));
                number6++;
            }
        });


    }



}