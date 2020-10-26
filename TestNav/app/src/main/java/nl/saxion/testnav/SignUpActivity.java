package nl.saxion.testnav;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Objects;

import nl.saxion.testnav.fragments.ProfileFragment;

public class SignUpActivity extends AppCompatActivity {

    MaterialEditText firstNam, lastNam, phoneNum, email, password, address;
    Button signUpBtn;
    FirebaseAuth auth;
    DatabaseReference reference;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initAttributes();
        auth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_firstNam = Objects.requireNonNull(firstNam.getText()).toString();
                String input_lastNam = Objects.requireNonNull(lastNam.getText()).toString();
                String input_phoneNum = Objects.requireNonNull(phoneNum.getText()).toString();
                String input_email = Objects.requireNonNull(email.getText()).toString();
                String input_password = Objects.requireNonNull(password.getText()).toString();
                String input_address = Objects.requireNonNull(address.getText()).toString();

                if (TextUtils.isEmpty(input_firstNam)||TextUtils.isEmpty(input_lastNam)||TextUtils.isEmpty(input_phoneNum)||
                        TextUtils.isEmpty(input_email)||TextUtils.isEmpty(input_password) ) {
                    Toast.makeText(SignUpActivity.this,"Please fill in all infor",Toast.LENGTH_SHORT).show();
                } else if(input_password.length()<6) {
                    Toast.makeText(SignUpActivity.this,"password at least 6 num",Toast.LENGTH_SHORT).show();
                }else if (!checkBox.isChecked()) {
                    Toast.makeText(SignUpActivity.this,"please agree to the Terms& Condition",Toast.LENGTH_SHORT).show();
                } else{
                        register(input_firstNam,input_lastNam,input_phoneNum,input_email,input_password,input_address);
                    Toast.makeText(SignUpActivity.this,"sign up success! You can login now.",Toast.LENGTH_SHORT).show();
                    SignUpActivity.this.finish();
                    }
                }
        });


    }

    private void register(final String firstName, final String lastName, final String phoneNum, final String email, final String password, final String address) {
        //TODO: SEPARATE METHOD FOR SIGN UP
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Customers").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("firstName",firstName);
                            hashMap.put("lastName",lastName);
                            hashMap.put("phoneNum",phoneNum);
                            hashMap.put("email",email);
                            hashMap.put("password",password);
                            hashMap.put("imageURL","default");
                            hashMap.put("address",address);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SignUpActivity.this,"error!!, please check your format.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initAttributes() {
        address = findViewById(R.id.addressEditTxt);
        checkBox = findViewById(R.id.tncCheckBox);
        firstNam = findViewById(R.id.fnameEditTxt);
        lastNam = findViewById(R.id.lnameEditTxt);
        phoneNum = findViewById(R.id.phoneNoProfileEditTxt);
        email = findViewById(R.id.emailProfileEditTxt);
        password = findViewById(R.id.passwordSignUpEditTxt);
        signUpBtn = findViewById(R.id.signUpBtn);


    }
}