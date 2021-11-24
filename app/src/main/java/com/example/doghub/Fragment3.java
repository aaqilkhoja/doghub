package com.example.doghub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        Button sendMessage = getActivity().findViewById(R.id.frag1_send_message);
        sendMessage.setOnClickListener(this);

        switch (v.getId()) {


            case R.id.frag1_send_message:
                Intent in = new Intent(getActivity(), ChatActivity.class);
                startActivity(in);
                break;

        }
    }

}
