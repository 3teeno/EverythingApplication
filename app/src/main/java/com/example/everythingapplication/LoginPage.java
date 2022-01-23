package com.example.everythingapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginPage extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.login_name_txt);
        password = findViewById(R.id.login_password_txt);

    }

    public void Login(View view) {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(name.getText().toString().equals(document.getData().get("Name"))&&password.getText().toString().equals(document.getData().get("Password")))
                                {
                                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();;
                                    startActivity(new Intent(getApplicationContext(),HomePage.class));

                                }
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            Toast.makeText(getApplicationContext(), "Unknown User", Toast.LENGTH_SHORT).show();;
                        }
                        else {
                            Log.w(TAG, "Error getting documents.", task.getException());


                        }


                    }
                });
    }

    public void RegisterUser(View view) {
        Intent intent = new Intent(this,SignupForm.class);
        startActivity(intent);
    }

}

