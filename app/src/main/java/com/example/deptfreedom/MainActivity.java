package com.example.deptfreedom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ImageView addDebt, menuimage;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Button close;
    Button logout;
    ImageButton arrow;
    ImageButton arrow2;
    DatabaseReference mDatabase;
    ImageButton arrow3;
    Dialog changepasswordDialog;
    ImageButton arrow4;
    ImageView back;
    LinearLayout hiddenView;
    DatabaseReference databaseReferenc;
    LinearLayout hiddenView2;
    LinearLayout hiddenView3;
    LinearLayout hiddenView4;
    TextView useremailid;
    //CardView cardView;

    AutoCompleteTextView currencySysmbolDropdown;


    String[] currency_symbols = {
            "$",
            "₹",
            "£",

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        currencySysmbolDropdown = findViewById(R.id.actv_currencySymbol);
        menuimage = findViewById(R.id.Menu);
        addDebt = findViewById(R.id.addDebt);
        drawerLayout = findViewById(R.id.drawer);
        close = findViewById(R.id.close);
        logout = findViewById(R.id.logout);
        arrow = findViewById(R.id.arrow_button);
        arrow2 = findViewById(R.id.arrow_button2);
        arrow3 = findViewById(R.id.arrow_button3);
        arrow4 = findViewById(R.id.arrow_button4);
        hiddenView = findViewById(R.id.hidden_view);
        hiddenView2 = findViewById(R.id.hidden_view2);
        hiddenView3 = findViewById(R.id.hidden_view3);
        hiddenView4 = findViewById(R.id.hidden_view4);
        useremailid = findViewById(R.id.useremail_id);
        Button passconformbutton = findViewById(R.id.passconformbutton);
        TextView currentpass = findViewById(R.id.currentpass);
        TextView newpass = findViewById(R.id.newpass);
        TextView conform = findViewById(R.id.conformpass);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, R.layout.list_item, currency_symbols);
        currencySysmbolDropdown.setAdapter(adapter);

        databaseReferenc = FirebaseDatabase.getInstance().getReference("Users");
        databaseReferenc.child(FirebaseAuth.getInstance().getUid()).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.getValue().toString();
                useremailid.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        changepasswordDialog =  new Dialog(this);
        changepasswordDialog.setContentView(R.layout.passwordchangedialogbox);
        changepasswordDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        changepasswordDialog.setCancelable(false);
        Button changebuttonok = changepasswordDialog.findViewById(R.id.changepassokaybubttn);


        changebuttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changepasswordDialog.dismiss();
            }
        });

        passconformbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (currentpass.getText().toString().equals(mDatabase.child("Users").child(FirebaseAuth.getInstance().getUid()).child("password")))
//                {
                if (newpass.getText().toString().length() <= 6 && conform.getText().toString().length() <= 6) {
                    newpass.setError("Weak Password");
                    newpass.requestFocus();
                } else {


                    if (!newpass.getText().toString().isEmpty() && !conform.getText().toString().isEmpty()) {
                        if (newpass.getText().toString().equals(conform.getText().toString())) {
                            mDatabase.child("Users").child(FirebaseAuth.getInstance().getUid()).child("password").setValue(newpass.getText().toString());
                            currentpass.setText("");
                            newpass.setText("");
                            conform.setText("");
                            currentpass.requestFocus();
                            changepasswordDialog.show();

                        } else {
                            newpass.setError("Not Match password");
                            newpass.requestFocus();
                            conform.setError("Not Match Password");
                        }

                    } else if (newpass.getText().toString().isEmpty()) {
                        newpass.setError("Invalid Password");
                        newpass.requestFocus();

                    } else if (conform.getText().toString().isEmpty()) {
                        conform.setError("Invalid password");
                        conform.requestFocus();
                    }
                }

            }
//                else
//                {
//                    Toast.makeText(addnew_deptdata.this, "Current Password is not Match", Toast.LENGTH_SHORT).show();
//                }
//            }
        });


        menuimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.RIGHT);

//                auth.signOut();
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);

            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });


        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView.getVisibility() == View.VISIBLE) {
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.right_arrow);

                } else {
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.down_arrow);

                }
            }
        });

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView2.getVisibility() == View.VISIBLE) {

                    hiddenView2.setVisibility(View.GONE);
                    arrow2.setImageResource(R.drawable.right_arrow);

                }

                else {

                    hiddenView2.setVisibility(View.VISIBLE);
                    arrow2.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView3.getVisibility() == View.VISIBLE) {


                    hiddenView3.setVisibility(View.GONE);
                    arrow3.setImageResource(R.drawable.right_arrow);

                } else {


                    hiddenView3.setVisibility(View.VISIBLE);
                    arrow3.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView4.getVisibility() == View.VISIBLE) {


                    hiddenView4.setVisibility(View.GONE);
                    arrow4.setImageResource(R.drawable.right_arrow);

                } else {


                    hiddenView4.setVisibility(View.VISIBLE);
                    arrow4.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        addDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addnew_deptdata.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent intent = new Intent(MainActivity.this, Log_In_Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}