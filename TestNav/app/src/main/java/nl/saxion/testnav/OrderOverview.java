package nl.saxion.testnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OrderOverview extends AppCompatActivity {

    private ImageButton chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_overview);
        initAttributes();

        //TODO: CREATE ORDER

        //TODO: ASSIGN DRIVER

        //TODO: FETCH THE DRIVER'S INFORMATION AND CREATE A DRIVER OBJECT

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

        //TODO: INIT THE REST OF THE BUTTONS ETC. HERE
    }
}