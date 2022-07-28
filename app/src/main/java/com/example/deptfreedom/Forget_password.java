package com.example.deptfreedom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.deptfreedom.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {

    ActivityForgetPasswordBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        EditText email = findViewById(R.id.foremail);

        ProgressBar progressBar = findViewById(R.id.forprogressbar);
        binding.forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.foremail.getText().toString().isEmpty())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Forget_password.this, "Check your Email", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Forget_password.this,Log_In_Activity.class);
                                startActivity(intent);
                                finish();


                            }
                            else
                            {
                                Toast.makeText(Forget_password.this, "Error", Toast.LENGTH_SHORT).show();

                            }

                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }
                else
                {
                    binding.foremail.setError("Enter the Email id");
                    binding.foremail.requestFocus();
                }
            }
        });

        binding.clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forget_password.this,Log_In_Activity.class));
                finish();
            }
        });
    }
}