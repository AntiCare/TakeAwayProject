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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import nl.saxion.testnav.models.Admin;

public class RestaurantDetailsActivity extends AppCompatActivity {
    TextView resName;
    TextView rating;
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    ImageButton b5;
    ImageButton b6;
    ImageView pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        resName = findViewById(R.id.rDetailsNameTxtView);
        rating = findViewById(R.id.rDetailsRatingTxtVw);
        b1 = findViewById(R.id.item1Button);
        b2 = findViewById(R.id.item2Button);
        b3 = findViewById(R.id.item3Button);
        b4 = findViewById(R.id.item4Button);
        b5 = findViewById(R.id.item5Button);
        b6 = findViewById(R.id.item6Button);
        pic = (ImageView)this.findViewById(R.id.rDetailsImgVw) ;


        Intent intent=getIntent();
        String restaurantName=intent.getStringExtra("RN");
        final String imageURL = intent.getStringExtra("URL");
        resName.setText(restaurantName);
        System.out.println(imageURL);

        new DownloadImageTask().execute(imageURL) ;

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

}