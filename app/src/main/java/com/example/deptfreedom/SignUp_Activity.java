package com.example.deptfreedom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deptfreedom.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ProgressDialog Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Loading = new ProgressDialog(SignUp_Activity.this);
        Loading.setTitle("Creating Account");
        Loading.setMessage("Please Wait While Creating Account");
        Button signupbtn = findViewById(R.id.signupbtn);
        TextView loginlink = findViewById(R.id.LoginLink);


        signupbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final EditText username = findViewById(R.id.etname);
                final EditText useremail = findViewById(R.id.etemail);
                final EditText password = findViewById(R.id.etpass);


                if (username == null || username.length() == 0 || username.equals("")) {
                    username.setError("Enter Your Name");
                    username.requestFocus();
                    return;
                } else if (useremail == null || useremail.length() == 0 || useremail.equals("")) {
                    useremail.setError("Enter Email ID");
                    useremail.requestFocus();
                    return;
                } else if (password == null || password.length() == 0 || password.equals("")) {
                    password.setError("Create password");
                    password.requestFocus();
                    return;
                } else {


                    Loading.show();
                    mAuth.createUserWithEmailAndPassword(useremail.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Loading.dismiss();
                            if (task.isSuccessful()) {
                                Users user = new Users(username.getText().toString(), useremail.getText().toString(),
                                        password.getText().toString());

                                String id = task.getResult().getUser().getUid();

                                database.getReference().child("Users").child(id).setValue(user);
                                Toast.makeText(SignUp_Activity.this, "User Created successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp_Activity.this, Log_In_Activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUp_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this,Log_In_Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}