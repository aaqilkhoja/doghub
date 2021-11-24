package com.example.doghub;


import android.app.Application;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class ProfileViewholder extends RecyclerView.ViewHolder {

    TextView textViewName, textViewProfession, viewUserProfile, sendMessagebtn;

    public ProfileViewholder(@NonNull View itemView) {
        super(itemView);
    }


    public void setProfileInChat(Application fragmentActivity, String name, String uid, String url) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();


        ImageView imageView = itemView.findViewById(R.id.iv_ch_item);
        TextView nametv = itemView.findViewById(R.id.namech_item_tv);
        TextView proftv = itemView.findViewById(R.id.ch_item_prof_tv);
        sendMessagebtn = itemView.findViewById(R.id.send_messagech_item_btn);

        if (userId.equals(uid)) {

            Picasso.get().load(url).into(imageView);
            nametv.setText(name);
            //  proftv.setText(prof);
            sendMessagebtn.setVisibility(View.INVISIBLE);
        } else {
            Picasso.get().load(url).into(imageView);
            nametv.setText(name);
            //  proftv.setText(prof);
        }


    }
}
