package com.example.doghub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class Fragment1 extends Fragment implements View.OnClickListener {

    ImageView imageView;
    TextView nameEt, dogs_nameEt, dogs_ageEt, breedEt, bioEt;
    TextView char1_tv, char2_tv, char3_tv, char4_tv, char5_tv;
    ImageButton ib_edit;
    Button btnsendmessage;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getActivity().findViewById(R.id.profilePic);
        nameEt = getActivity().findViewById(R.id.ownerName);
        dogs_nameEt = getActivity().findViewById(R.id.dogName);
        dogs_ageEt = getActivity().findViewById(R.id.dogAge);
        breedEt = getActivity().findViewById(R.id.dogBreed);
        bioEt = getActivity().findViewById(R.id.ownerBio);
        char1_tv = getActivity().findViewById(R.id.char1_f1);
        char2_tv = getActivity().findViewById(R.id.char2_f1);
        char3_tv = getActivity().findViewById(R.id.char3_f1);
        char4_tv = getActivity().findViewById(R.id.char4_f1);
        char5_tv = getActivity().findViewById(R.id.char5_f1);

        ib_edit = getActivity().findViewById(R.id.editPencilButton);
        btnsendmessage = getActivity().findViewById(R.id.frag1_send_message);

        ib_edit.setOnClickListener(this);

        btnsendmessage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.editPencilButton:
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
                break;

            case R.id.frag1_send_message:
                Intent in = new Intent(getActivity(), ChatActivity.class);
                startActivity(in);
                break;

        }
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //had to add if statement because we were getting a null current user id.
        //if statement checks if user is logged in, if they aren't, intent sends them back to main login page
        //should be going to login to begin with
        if (user != null) {
            String currentid = user.getUid();


            DocumentReference reference;
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();

            reference = firestore.collection("user").document(currentid);

            reference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.getResult().exists()) {

                                String nameResult = task.getResult().getString("name");
                                String dogNameResult = task.getResult().getString("dogs_name");
                                String breedResult = task.getResult().getString("breed");
                                String bioResult = task.getResult().getString("bio");
                                String dogAgeResult = task.getResult().getString("dogs_age");
                                String url = task.getResult().getString("url");
                                String char1Result = task.getResult().getString("char1");
                                String char2Result = task.getResult().getString("char2");
                                String char3Result = task.getResult().getString("char3");
                                String char4Result = task.getResult().getString("char4");
                                String char5Result = task.getResult().getString("char5");


                                Picasso.get().load(url).into(imageView);
                                nameEt.setText(nameResult);
                                bioEt.setText(bioResult);
                                dogs_nameEt.setText(dogNameResult);
                                dogs_ageEt.setText(dogAgeResult);
                                breedEt.setText(breedResult);
                                char1_tv.setText(char1Result);
                                char2_tv.setText(char2Result);
                                char3_tv.setText(char3Result);
                                char4_tv.setText(char4Result);
                                char5_tv.setText(char5Result);

                            } else {
                                Intent intent = new Intent(getActivity(), CreateProfile.class);
                                startActivity(intent);
                            }
                        }
                    });

        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
        }
    }
}







