package com.example.everythingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupForm extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name,email,phone,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        name = findViewById(R.id.login_name_txt);
        email = findViewById(R.id.signup_email_txt);
        phone = findViewById(R.id.login_password_txt);
        password = findViewById(R.id.signup_password_txt);
    }

    public void SignUp(View view) {
        Map<String, Object> user = new HashMap<>();
        user.put("Name", name.getText().toString());
        user.put("Email", email.getText().toString());
        user.put("Password", password.getText().toString());
        user.put("Phone", phone.getText().toString());

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }

    public void GotoLogin(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }
}