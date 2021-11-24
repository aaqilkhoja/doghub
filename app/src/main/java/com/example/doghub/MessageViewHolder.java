package com.example.doghub;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView sendertv, receivertv;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);


    }

    public void Setmessage(Application application, String message, String type, String senderUid, String receiverUid) {

        sendertv = itemView.findViewById(R.id.sender_tv);
        receivertv = itemView.findViewById(R.id.receiver_tv);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUid = user.getUid();

        if (currentUid.equals(senderUid)) {
            receivertv.setVisibility(View.GONE);
            sendertv.setText(message);

        } else if (currentUid.equals(receiverUid)) {
            sendertv.setVisibility(View.GONE);
            receivertv.setText(message);

        }

    }


}
