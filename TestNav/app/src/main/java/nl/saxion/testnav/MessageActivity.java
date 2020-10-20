package nl.saxion.testnav;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MessageActivity extends AppCompatActivity {
    ImageButton sendButton;
    EditText sendText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        initialVariable();
        event();



    }
    public void event() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendText.getText().toString();
                if (!msg.equals("")) {
                    sendMessage("SENDER","RECEIVER",msg);
                }else {
                    Toast.makeText(MessageActivity.this,"Can't send empty message!!!",Toast.LENGTH_SHORT).show();
                }
                sendText.setText("");
            }
        });
    }




    public void sendMessage (String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);
    }


    public void initialVariable() {
        sendButton= findViewById(R.id.btn_send);
        sendText= findViewById(R.id.text_send);
    }
}
