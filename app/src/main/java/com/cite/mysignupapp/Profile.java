package com.cite.mysignupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbReference;

    String userID;
    TextView txtDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        dbReference= FirebaseDatabase.getInstance().getReference("User");
        userID= user.getUid();

        txtDisplay=findViewById(R.id.Text1);
        dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user= snapshot.getValue(User.class);
                
                if  (user!= null){
                    String username;
                    username=user. username;

                    txtDisplay.setText("USERNAME"+username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "An error occurred", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void Logout(View view) {

        mAuth.signOut();
        Intent intent= new Intent(Profile. this, MySignUp.class);
        startActivity(intent);


    }
}