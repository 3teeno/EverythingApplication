package com.example.everythingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText p_title, p_description, p_price;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 100;
    ImageView p_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        p_title = findViewById(R.id.new_product_title);
        p_image = findViewById(R.id.new_product_img);
        p_price = findViewById(R.id.new_product_price);
        p_description = findViewById(R.id.new_product_description);
    }

    public void AddProduct(View view) {
        Map<String, Object> Products = new HashMap<>();
        Products.put("Title", p_title.getText().toString());
        Products.put("Price", p_price.getText().toString());
        Products.put("Description", p_description.getText().toString());
        Products.put("Image", p_image.getId());

        db.collection("Products")
                .add(Products)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Added Product", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                        Toast.makeText(getApplicationContext(), "Failed To Add", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void AddImage(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            p_image.setImageURI(imageUri);
        }
    }
}