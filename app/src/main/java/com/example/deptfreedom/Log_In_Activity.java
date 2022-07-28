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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_In_Activity extends AppCompatActivity {

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btnlogin = findViewById(R.id.btnlogin);
        final EditText useremail = findViewById(R.id.email);
        final EditText password = findViewById(R.id.mypass);
        TextView signuplink = findViewById(R.id.signUpLink);
        TextView forgetlink = findViewById(R.id.forgetlink);
        progressDialog = new ProgressDialog(Log_In_Activity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login your account");
        mAuth = FirebaseAuth.getInstance();

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_In_Activity.this,SignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        forgetlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Log_In_Activity.this,Forget_password.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (useremail==null|| useremail.length()==0||useremail.equals(""))
                {
                    useremail.setError("Enter Email ID");
                    useremail.requestFocus();
                    return;
                }
                else if (password==null||password.length()==0||password.equals(""))
                {
                    password.setError("Create password");
                    password.requestFocus();
                    return;
                }
                else {

                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(useremail.getText().toString(), password.
                            getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Log_In_Activity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Log_In_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

        if (mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(Log_In_Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


}