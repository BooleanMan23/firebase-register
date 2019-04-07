package com.example.firebase_rahmat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText namaEditText;
    EditText nomorTelfonEditText;
    Button registerButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText  = (EditText) findViewById(R.id.registerUsernameEditText);
        passwordEditText = (EditText) findViewById(R.id.registerPasswordEditText);
        namaEditText = (EditText) findViewById(R.id.namaEditText);
        nomorTelfonEditText = (EditText) findViewById(R.id.nomorTelfonEditText);
        registerButton = (Button) findViewById(R.id.registerButton);

//        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(MainActivity.this,HomepageUnity.class);
            startActivity(intent);
        }



    }

    public void register(View view){
        final String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        final String nama = namaEditText.getText().toString();
        final String nomorTelfon = nomorTelfonEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            uploadToDatabase(username, nama, nomorTelfon, mAuth.getUid());
                            Toast.makeText(MainActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this,HomepageUnity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });


    }

    public void login(View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);


    }

    public void uploadToDatabase(String email, String nama, String nomorTelfon, String id ){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Class FirebaseDatabase merujuk ke database dari projek
        DatabaseReference myRef = database.getReference();
        //parameter menunjukan fokus
        MUsers newUser = new MUsers(email, nama, nomorTelfon);
        myRef.child("Users").child(id).setValue(newUser);


    }
}
