package nl.saxion.testnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import nl.saxion.testnav.models.Chat;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

public static final int MSG_Type_LEFT = 0;
public static final int MSG_TYPE_RIGHT = 1;

   private Context mContext;
   private List<Chat> mChat;
   private String imageUrl;

   FirebaseUser firebaseUser;


   public MessageAdapter (Context mContext, List<Chat>mChat,String imageUrl) {
    this.mChat = mChat;
    this.mContext= mContext;
    this.imageUrl = imageUrl;
   }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType ==MSG_TYPE_RIGHT) {
           View view = LayoutInflater.from(mContext).inflate(R.layout.message_right_item,parent,false);
           return new MessageAdapter.ViewHolder(view);
       } else {
           View view = LayoutInflater.from(mContext).inflate(R.layout.message_left_item,parent,false);
           return new MessageAdapter.ViewHolder(view);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

       Chat chat = mChat.get(position);

       holder.message.setText(chat.getMessage());
       if (imageUrl.equals("default")) {
           holder.message.setText(chat.getMessage());

           if(imageUrl.equals("default")) {
               holder.receiverImage.setImageResource(R.mipmap.ic_launcher);
           }else {
               Glide.with(mContext).load(imageUrl).into(holder.receiverImage);
           }
       }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       public TextView message;
       public ImageView receiverImage;

       public ViewHolder(View itemView) {
           super(itemView);

           message = itemView.findViewById(R.id.show_message);
           receiverImage = itemView.findViewById(R.id.receiver_image);
       }

   }

    @Override
    public int getItemViewType(int position) {
       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       if (mChat.get(position).getSender().equals(firebaseUser.getUid())) {
           return MSG_TYPE_RIGHT;
       }else {
           return MSG_Type_LEFT;
       }
    }
}
