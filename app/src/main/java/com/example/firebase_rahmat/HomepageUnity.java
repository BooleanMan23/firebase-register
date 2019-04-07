package com.example.firebase_rahmat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomepageUnity extends AppCompatActivity {

    TextView emailTextView;
    TextView namaTextView;
    TextView nomorTextView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_unity);

        emailTextView = (TextView) findViewById(R.id.emailTextView);
        namaTextView = (TextView) findViewById(R.id.namaTextView);
        nomorTextView = (TextView) findViewById(R.id.nomorTextView);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        downloadDataUser(user.getUid());






    }


    public void downloadDataUser(String id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Class FirebaseDatabase merujuk ke database dari projek
        DatabaseReference myRef = database.getReference();
        myRef.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //daata snapshot ialah data yang akan kita download
                emailTextView.setText(dataSnapshot.child("email").getValue().toString());
                namaTextView.setText(dataSnapshot.child("nama").getValue().toString());
                nomorTextView.setText(dataSnapshot.child("nomorTelfon").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void logout(View view){

        mAuth.signOut();
        Intent intent = new Intent(HomepageUnity.this,MainActivity.class);
        startActivity(intent);

    }
}
