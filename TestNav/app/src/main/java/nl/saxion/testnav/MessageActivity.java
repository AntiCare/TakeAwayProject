package nl.saxion.testnav;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nl.saxion.testnav.models.Chat;
import nl.saxion.testnav.models.Customer;


public class MessageActivity extends AppCompatActivity {

    TextView userName;
    String userNames;
    String userid;
    FirebaseUser firebaseUser;

    ImageButton sendButton;
    EditText sendText;
    DatabaseReference databaseReference,df1,df2;


    MessageAdapter messageAdapter;
    List<Chat> mChat;
    RecyclerView recyclerView;


    String OrderID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initialVariable();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        String check = intent.getStringExtra("check");
        if (check.equals("1")) {
            userNames = intent.getStringExtra("ReceiverName1");
            OrderID = intent.getStringExtra("OrderID1");
            df1=FirebaseDatabase.getInstance().getReference("Order").child(OrderID).child("OrderUser").child("CourierID");
            df1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                  userid = snapshot.getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }else if (check.equals("2")) {
            userid = intent.getStringExtra("ReceiverID");
            userNames = intent.getStringExtra("ReceiverName");
            OrderID = intent.getStringExtra("OrderID");
        }

        userName.setText(userNames);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendText.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(firebaseUser.getUid(),userid,msg);
                }else {
                    Toast.makeText(MessageActivity.this,"Can't send empty message!!!",Toast.LENGTH_SHORT).show();
                }
                sendText.setText("");
            }
        });



        databaseReference =FirebaseDatabase.getInstance().getReference("Order");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (firebaseUser.getUid().equals("EmEpFRHLoWOmOrJZbeX40fzhDuL2")) {
                    readMassage("fvosadhfgabsfa",userid);
                }else if(firebaseUser.getUid().equals("LHHuI26WtOZQuG8TeP4EjHIOXKk1")) {

                    readMassage("nsjkfDKJFBAG",userid);
                }else if(firebaseUser.getUid().equals("RZHrlYziUady8eCd3chXWRRUSdb2")) {

                    readMassage("asjnfdsagkrhsd",userid);
                }else if(firebaseUser.getUid().equals("YZflkGMTqBeoEbhGOtWPzg28SVM2")) {

                    readMassage("asjkdfnwakfab",userid);
                }else {
                    readMassage(firebaseUser.getUid(),userid);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void sendMessage (String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        if (sender.equals("EmEpFRHLoWOmOrJZbeX40fzhDuL2")) {
            sender = "fvosadhfgabsfa";
        }
        if(sender.equals("LHHuI26WtOZQuG8TeP4EjHIOXKk1")) {
            sender = "nsjkfDKJFBAG";
        }
        if(sender.equals("RZHrlYziUady8eCd3chXWRRUSdb2")) {
            sender = "asjnfdsagkrhsd";
        }
        if(sender.equals("YZflkGMTqBeoEbhGOtWPzg28SVM2")) {
            sender = "asjkdfnwakfab";
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Order").child(OrderID).child("Chats").push().setValue(hashMap);
    }


    public void initialVariable() {
        userName = findViewById(R.id.user_name);
        sendButton= findViewById(R.id.btn_send);
        sendText= findViewById(R.id.text_send);
    }

    public void readMassage(final String myid, final String userid) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Order").child(OrderID).child("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)) {
                        mChat.add(chat);
                    }

                }
                messageAdapter = new MessageAdapter(MessageActivity.this,mChat);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
