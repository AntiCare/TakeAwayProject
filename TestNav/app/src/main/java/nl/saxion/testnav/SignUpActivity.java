package nl.saxion.testnav;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initAttributes();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
                    //TODO: CHECK TO SEE IF ALL FIELDS ARE FILLED, CHECK BOX IS CHECKED. IF NOT MAKE A DIALOG OR A TOAST
                }

                Toast.makeText(getApplicationContext(), "Remove this after finishing", Toast.LENGTH_SHORT).show();
                signUp();
                finish(); //return to previous screen.
            }
        });
    }

    private void signUp() {
        //TODO: SEPARATE METHOD FOR SIGN UP
    }

    private void initAttributes() {
        signUpBtn = findViewById(R.id.signUpBtn);
    }
}