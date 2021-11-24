//I have established a key here to identify areas of improvement
//they are marked with "IMP"


package com.example.doghub;

//importing all the required classes/packages

import android.content.ContentResolver;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.PopupMenu;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import android.widget.*;

import java.util.*;

public class CreateProfile extends AppCompatActivity {


    //creating the variables
    EditText etName, etDogName, etDogBreed, etDogAge, etBio;
    Button button, char1, char2, char3, char4, char5;

    ImageView imageView;
    ProgressBar progessBar;
    Uri imageUri, imageUri2;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    private static final int Pick_Image = 1;
    All_User_Members member;
    String currentUserID, choice1, choice2, choice3, choice4, choice5;

    //String [] characteristics = {"happy","2","3","4","5","6","7","8","9","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


        //equating the variables declared above with their respective IDs
        member = new All_User_Members();
        imageView = findViewById(R.id.imageView_dog_cp);
        // imageView2 = findViewById(R.id.imageView_cp);
        etName = findViewById(R.id.et_name_cp);
        etBio = findViewById(R.id.et_bio_cp);
        etDogName = findViewById(R.id.et_dog_name_cp);
        etDogBreed = findViewById(R.id.et_breed_cp);
        etDogAge = findViewById(R.id.et_dog_age_cp);
        button = findViewById(R.id.btn_cp);

        char1 = findViewById(R.id.char1_btn);
        char2 = findViewById(R.id.char2_btn);
        char3 = findViewById(R.id.char3_btn);
        char4 = findViewById(R.id.char4_btn);
        char5 = findViewById(R.id.char5_btn);

        progessBar = findViewById(R.id.progressbar_cp);

        //getting the instance of the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        currentUserID = user.getUid();

        //declaring the references
        documentReference = db.collection("user").document(currentUserID);
        storageReference = FirebaseStorage.getInstance().getReference("Profile Images");

        // storageReference2= FirebaseStorage.getInstance().getReference("Profile Images 2");


        databaseReference = database.getReference("All Users");

        //declaring what the button will do once clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            //on clicking, the upload data method will be called
            public void onClick(View v) {
                uploadData();
            }
        });

        //once user clicks on the image, it will allow them to select an image from their phone
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //setting type of variable to any type of image, not limiting to just one for example jpeg
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, Pick_Image);

            }
        });


        char1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CreateProfile.this, char1);

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        choice1 = item.getTitle().toString();
                        return true;
                    }
                });
                popupMenu.show();

            }
        });

  /*              (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   PopupMenu popupMenu = new PopupMenu(CreateProfile.this, spino);
               // String choice = new String();

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    }
                });
                popupMenu.show();

            }

        });*/


        char2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CreateProfile.this, char2);

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        choice2 = item.getTitle().toString();
                        return true;


                    }
                });
                popupMenu.show();

            }
        });


        char3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CreateProfile.this, char3);

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        choice3 = item.getTitle().toString();
                        return true;
                    }
                });
                popupMenu.show();

            }
        });

        char4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(CreateProfile.this, char4);

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

                        choice4 = item.getTitle().toString();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        char5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(CreateProfile.this, char5);

                popupMenu.getMenuInflater().inflate(R.menu.dog_char_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(CreateProfile.this, "You clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        choice5 = item.getTitle().toString();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    ;
      /*  imageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, Pick_Image);
            }
        });*/




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == Pick_Image || resultCode == RESULT_OK || data != null || data.getData() != null) {

                imageUri = data.getData();


                //loading an image into Picasso
                Picasso.get().load(imageUri).into(imageView);

                //   imageUri2 = data.getData();
                //   Picasso.get().load(imageUri2).into(imageView2);

            }

        } catch (Exception e) {
            Toast.makeText(this, "Error" + e, Toast.LENGTH_SHORT).show();
        }

    }


    private String getFileExt(Uri uri) {

        //we are using a two-way map that maps MIME-types to file extensions and vice versa
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }

    private void uploadData() {

        //converting all entered data into Strings for now, including age
        //will have to change later so that age only takes in number
        //this comes in the improvements step of the code (IMP)


        String name = etName.getText().toString();
        String dogs_name = etDogName.getText().toString();
        String bio = etBio.getText().toString();
        String breed = etDogBreed.getText().toString();
        String dogs_age = etDogAge.getText().toString();
        String char1str = choice1;
        String char2str = choice2;
        String char3str = choice3;
        String char4str = choice4;
        String char5str = choice5;

        //  String char2str = char2.getText().toString();
        // String char3str = char3.getText().toString();
        //  String char4str = char4.getText().toString();
        //  String char5str = char5.getText().toString();


        //making sure that all fields aren't blank
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(bio) || !TextUtils.isEmpty(dogs_name) || !TextUtils.isEmpty(breed) || !TextUtils.isEmpty(dogs_age) || imageUri != null || !TextUtils.isEmpty(char1str)
                || !TextUtils.isEmpty(char2str) || !TextUtils.isEmpty(char3str) || !TextUtils.isEmpty(char4str) || !TextUtils.isEmpty(char5str)) {
            // || imageUri2 != null


            //setting the progress bar visibility on while the data is being loaded into firebase
            //might have to work on this(IMP)

            progessBar.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri));
            //   final StorageReference reference2 = storageReference2.child(System.currentTimeMillis()+"."+getFileExt(imageUri2));
            uploadTask = reference.putFile(imageUri);
            // uploadTask = reference.putFile(imageUri2);

            Task<Uri> urlTask = uploadTask.continueWithTask((new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            })).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        //    Uri downloadUri2 = task.getResult();

                        Map<String, String> profile = new HashMap<>();
                        profile.put("name", name);
                        profile.put("dogs_name", dogs_name);
                        profile.put("breed", breed);
                        profile.put("bio", bio);
                        profile.put("dogs_age", dogs_age);
                        profile.put("url", downloadUri.toString());
                        profile.put("char1", char1str);
                        profile.put("char2", char2str);
                        profile.put("char3", char3str);
                        profile.put("char4", char4str);
                        profile.put("char5", char5str);

                        profile.put("privacy", "Public");

                        //   profile.put("url2", downloadUri2.toString());

//saving data in the realtime database
                        member.setName(name);
                        member.setBio(bio);
                        member.setBreed(breed);
                        member.setDogs_age(dogs_age);
                        member.setDogs_name(dogs_name);
                        member.setChar1(char1str);
                        member.setChar2(char2str);
                        member.setChar3(char3str);
                        member.setChar4(char4str);
                        member.setChar5(char5str);

                        member.setUid(currentUserID);
                        member.setUrl(downloadUri.toString());
                        //   member.setUrl(downloadUri2.toString());

                        databaseReference.child(currentUserID).setValue(member);

                        documentReference.set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                //adding progress bar and displaying a toast message for a successful profile creation

                                //progressBar.setVisiblity(View.INVISIBLE);
                                Toast.makeText(CreateProfile.this, "Profile Created", Toast.LENGTH_SHORT).show();

                                //using Handler to send user after some time to fragment 1 after 2 seconds after completion
                                //waiting for 2 seconds to avoid errors
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(CreateProfile.this, Fragment1.class);
                                        startActivity(intent);
                                    }
                                }, 2000);


                            }
                        });


                    }

                }
            });
        } else {
            //error toast message if all the fields are not filled
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }

    }

}