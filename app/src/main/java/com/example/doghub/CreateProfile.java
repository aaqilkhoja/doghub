package com.example.doghub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.widget.*;

public class CreateProfile extends AppCompatActivity {


    EditText etName, etDogName, etDogBreed, etDogAge, etBio;
    Button button;
    ImageView imageView, imageView2;
    ProgressBar progessBar;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;


    private static final int Pick_Image = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


        imageView = findViewById(R.id.imageView_dog_cp);
        imageView2 = findViewById(R.id.imageView_cp);
        etName = findViewById(R.id.et_name_cp);
        etDogName = findViewById(R.id.et_dog_name_cp);
        etDogBreed = findViewById(R.id.et_breed_cp);
        etDogAge = findViewById(R.id.et_dog_age_cp);
        button = findViewById(R.id.btn_cp);
        progessBar = findViewById(R.id.progressbar_cp);
    }
}