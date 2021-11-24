package com.example.doghub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class Fragment4 extends Fragment implements View.OnClickListener {

    FloatingActionButton fb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;


    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();

        recyclerView = getActivity().findViewById(R.id.rv_f4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = database.getReference("All Events");


        imageView = getActivity().findViewById(R.id.iv_f4);
        fb = getActivity().findViewById(R.id.floatingActionBtn);
        reference = db.collection("user").document(currentUserId);

        fb.setOnClickListener(this);
        imageView.setOnClickListener(this);

        FirebaseRecyclerOptions<EventMember> options = new FirebaseRecyclerOptions.Builder<EventMember>().setQuery(databaseReference, EventMember.class).build();

        FirebaseRecyclerAdapter<EventMember, ViewHolder_Event> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventMember, ViewHolder_Event>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Event holder, int position, @NonNull EventMember model) {

                holder.setItem(getActivity(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getEvent(), model.getPrivacy(), model.getTime());

            }

            @NonNull
            @Override
            public ViewHolder_Event onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);


                return new ViewHolder_Event(view);

            }
        };
        firebaseRecyclerAdapter.startListening();

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_f4:

                break;

            case R.id.floatingActionBtn:
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public void onStart() {
        super.onStart();

        reference.get().addOnCompleteListener((task ->
        {
            if (task.getResult().exists()) {
                String url = task.getResult().getString("url");

                Picasso.get().load(url).into(imageView);

            } else {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
