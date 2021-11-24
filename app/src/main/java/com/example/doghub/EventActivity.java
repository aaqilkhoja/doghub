package com.example.doghub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EventActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllEvents, UserEvents;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    EventMember member;
    String name, url, privacy, uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();


        editText = findViewById(R.id.et_event);
        button = findViewById(R.id.btn_submit);
        documentReference = db.collection("user").document(currentUserId);

        AllEvents = database.getReference("All Events");
        UserEvents = database.getReference("Event Questions").child(currentUserId);


        member = new EventMember();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = editText.getText().toString();

                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentDate.format(cdate.getTime());

                Calendar ctime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                final String saveTime = currentTime.format(ctime.getTime());

                String time = savedate + ":" + saveTime;


                if (event != null) {

                    member.setEvent(event);
                    member.setName(name);
                    member.setPrivacy(privacy);
                    member.setUrl(url);
                    member.setUserid(uid);
                    member.setTime(time);

                    String id = UserEvents.push().getKey();
                    UserEvents.child(id).setValue(member);

                    String child = AllEvents.push().getKey();
                    member.setKey(id);
                    AllEvents.child(child).setValue(member);
                    Toast.makeText(EventActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(EventActivity.this, "Please post an event", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.getResult().exists()) {


                    name = task.getResult().getString("name");
                    url = task.getResult().getString("url");
                    privacy = task.getResult().getString("privacy");
                    uid = task.getResult().getString("uid");


                } else {
                    Toast.makeText(EventActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
