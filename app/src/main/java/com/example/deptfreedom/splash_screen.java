package com.example.deptfreedom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView deptlogo = findViewById(R.id.deptfredomlogo);
        TextView deptname = findViewById(R.id.deptfredom);

        Animation name  = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        deptname.startAnimation(name);
        Animation logo  = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        deptlogo.startAnimation(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this,Log_In_Activity.class);
                startActivity(intent);
                finish();

            }
        },2000);

    }
}