package com.example.doghub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class UpdateProfile extends AppCompatActivity {


    //declaring variables

    EditText etName, etBio, etDogName, etBreed, etDogAge;
    Button button;
    //getting instance of database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //for also saving the data in the firebase database, since we are saving in two different places
    DatabaseReference reference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        //giving the reference for the data entered by the user

        etBio = findViewById(R.id.et_bio_up);
        etName = findViewById(R.id.et_name_up);
        etDogAge = findViewById(R.id.et_dog_age_up);
        etDogName = findViewById(R.id.et_dog_name_up);
        etBreed = findViewById(R.id.et_breed_up);
        button = findViewById(R.id.btn_up);


        //setting the button method

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();

            }
        });
    }

    //this onStart method will show the user the previous data that they had entered prior to updating
    //this way users can see what they want to change.
    //whenever opened, we will see the data that was already loaded
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUid = user.getUid();

        documentReference.collection("user").document(currentUid);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        });

    }


    private void updateProfile() {
    }

}
