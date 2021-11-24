package com.example.doghub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.squareup.picasso.Picasso;


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
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentUid = user.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUid = user.getUid();

        documentReference = db.collection("user").document(currentUid);

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


        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.getResult().exists()) {


                    String nameResult = task.getResult().getString("name");
                    String dogNameResult = task.getResult().getString("dogs_name");
                    String breedResult = task.getResult().getString("breed");
                    String bioResult = task.getResult().getString("bio");
                    String dogAgeResult = task.getResult().getString("dogs_age");


                    etBio.setText(bioResult);
                    etName.setText(nameResult);
                    etDogAge.setText(dogAgeResult);
                    etDogName.setText(dogNameResult);
                    etBreed.setText(breedResult);
                } else {
                    Toast.makeText(UpdateProfile.this, "No Profile", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    private void updateProfile() {
        String bio = etBio.getText().toString();
        String name = etName.getText().toString();
        String age = etDogAge.getText().toString();
        String dogname = etDogName.getText().toString();
        String breed = etBreed.getText().toString();


        final DocumentReference sDoc = db.collection("user").document(currentUid);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                // DocumentSnapshot snapshot = transaction.get(sfDocRef);


                transaction.update(sDoc, "bio", bio);
                transaction.update(sDoc, "name", name);
                transaction.update(sDoc, "dogs_age", age);
                transaction.update(sDoc, "dogs_name", dogname);
                transaction.update(sDoc, "breed", breed);

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UpdateProfile.this, "updated", Toast.LENGTH_SHORT).show();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}


