package com.example.deptfreedom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deptfreedom.databinding.ActivityAddnewDeptdataBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class addnew_deptdata extends AppCompatActivity {

    ImageButton arrow_debtDetails;
    DatabaseReference mDatabase;
    AutoCompleteTextView actv_categoryDropdown,reninderDropDown;
    DrawerLayout drawerLayout;
    Dialog dialog;
    Spinner selectpayofforder;
    ImageView menuimage;
    TextView useremailid;
    ProgressDialog Loading;
    String userchildid;
    Button close;
    setdataAdapter adapter;
    Button okay ;
    ArrayList<getdatamodel> list;
    RecyclerView deptnamerecyclerView;
    ImageButton arrow,arrow2,arrow3,arrow4;
    ImageView strategyplusbutton;
    LinearLayout hiddenView,hiddenView2,hiddenView4,hiddenView3,hiddenView_debtDetails;
    ActivityAddnewDeptdataBinding binding;
    // Syntax of declaration of variable
    final Calendar myCalendar = Calendar.getInstance();
    String[] categories = {"Credit Card", "Auto Loan", "Student Loan", "Medicine Loan", "Mortgage", "Personal Loan", "TAXES", "Utilites anad Bills", "Overdraft", "Others"};
    String[] reminder = {"5 days life", "10 days life", "15 days life", "20 days life", "25 days life", "30 days life"};
    String[] dept_details = new String[]{"APR High to Low (avalanche)","APR Low to High (avalanche)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddnewDeptdataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Loading = new ProgressDialog(addnew_deptdata.this);
        Loading.setTitle("Please Wait");
        Loading.setMessage("Data will be Add in app");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        actv_categoryDropdown = findViewById(R.id.category);
        reninderDropDown = findViewById(R.id.remdate);
        selectpayofforder = findViewById(R.id.selectpayofforder);
        arrow_debtDetails = findViewById(R.id.arrow_debtDetails);
        drawerLayout = findViewById(R.id.drawer);
        menuimage = findViewById(R.id.Menu);
        hiddenView_debtDetails = findViewById(R.id.hiddenView_debtDetails);
        arrow = findViewById(R.id.arrow_button);
        arrow2 = findViewById(R.id.arrow_button2);
        close = findViewById(R.id.close);
        arrow3 = findViewById(R.id.arrow_button3);
        arrow4 = findViewById(R.id.arrow_button4);
        Button logout = findViewById(R.id.logout);
        LinearLayout hiddenView = findViewById(R.id.hidden_view),hiddenView2 = findViewById(R.id.hidden_view2),
                hiddenView3 = findViewById(R.id.hidden_view3),hiddenView4 = findViewById(R.id.hidden_view4);

        list = new ArrayList<>();
        list.add(new getdatamodel("bill","500","50","3","student","02/06/2022","04/02/2020"));
        userchildid = binding.name.getText().toString() + binding.sbalance.getText().toString() +
                binding.mpayment.getText().toString() + binding.apr.getText().toString();



        EditText name = findViewById(R.id.name);
        EditText sbalance = findViewById(R.id.sbalance);
        EditText mpayment = findViewById(R.id.mpayment);
        EditText apr = findViewById(R.id.apr);
        EditText category = findViewById(R.id.category);
        TextView paydate = findViewById(R.id.paydate);
        EditText remdate = findViewById(R.id.remdate);
        useremailid = findViewById(R.id.useremail_id);
        hiddenView_debtDetails.setVisibility(View.VISIBLE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addnew_deptdata.this,SignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });
//        mDatabase.child("Users data").child(FirebaseAuth.getInstance().getUid()).child(userchildid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot snapshot1: snapshot.getChildren())
//                {
//                    getdatamodel user = snapshot1.getValue(getdatamodel.class);
//                    list.add(user);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        mDatabase.child("Users").child(FirebaseAuth.getInstance().getUid()).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.getValue().toString();
                useremailid.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // dialog box set

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialogbox);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        okay = dialog.findViewById(R.id.okaybuttondialogbox);

        //set recyclerview

        deptnamerecyclerView = findViewById(R.id.deptnamerecyclerview);
        deptnamerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new setdataAdapter(list,this);
        deptnamerecyclerView.setAdapter(adapter);


        //drop down sets
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, categories);
        actv_categoryDropdown.setAdapter(categoryAdapter);

        ArrayAdapter<String> deptdetails = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, dept_details);
        selectpayofforder.setAdapter(deptdetails);

        ArrayAdapter<String> reminderAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, reminder);
        reninderDropDown.setAdapter(reminderAdapter);


        // drop down functions

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

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

                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (hiddenView2.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    // by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //*TransitionManager.beginDelayedTransition(cardView,
                    // new AutoTransition());*//*
                    hiddenView2.setVisibility(View.GONE);
                    arrow2.setImageResource(R.drawable.right_arrow);

                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    //*TransitionManager.beginDelayedTransition(cardView,
                    // new AutoTransition());*//*
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


        menuimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.RIGHT);
//                auth.signOut();
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);

            }
        });


        // Linear layout hidden
        arrow_debtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView_debtDetails.getVisibility() == View.VISIBLE) {


                    hiddenView_debtDetails.setVisibility(View.GONE);
                    arrow_debtDetails.setImageResource(R.drawable.right_arrow);

                } else {

                    hiddenView_debtDetails.setVisibility(View.VISIBLE);
                    arrow_debtDetails.setImageResource(R.drawable.down_arrow);

                }
            }
        });

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        paydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addnew_deptdata.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //data save in firebase button
        binding.savebtn.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                if (!binding.name.getText().toString().isEmpty() && !binding.sbalance.getText().toString().isEmpty() &&
                        !binding.mpayment.getText().toString().isEmpty() && !binding.apr.getText().toString().isEmpty()) {


                    if (binding.checkBox.isChecked()) {

                        // data post to server Firebase from user input

                        writeNewUser(FirebaseAuth.getInstance().getUid(),
                                name.getText().toString(), sbalance.getText().toString(),
                                mpayment.getText().toString(), apr.getText().toString(),
                                category.getText().toString(), paydate.getText().toString(), remdate.getText().toString());
                        dialog.show();


                    } else
                    // check for condition error
                    {
                        displayAlertbox();

                    }


                }
                // if user can't input data to this form then validation
                else if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Enter the Name");
                    binding.name.requestFocus();
                } else if (binding.sbalance.getText().toString().isEmpty()) {
                    binding.sbalance.setError("Required starting balance");
                    binding.sbalance.requestFocus();

                } else if (binding.mpayment.getText().toString().isEmpty()) {
                    binding.mpayment.setError("Enter the Minimum Payment");
                    binding.mpayment.requestFocus();

                } else if (binding.apr.getText().toString().isEmpty()) {
                    binding.apr.setError("Enter the APR");
                    binding.apr.requestFocus();

                } else if (binding.checkBox.getText().toString().isEmpty()) {
                    binding.checkBox.setError("Please Agree checkbox");
                    binding.checkBox.requestFocus();

                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/mm/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.paydate.setText(dateFormat.format(myCalendar.getTime()));

    }

    public void writeNewUser(String userId, String name, String sbalance, String mpayment, String apr, String category, String paydate, String remdate) {
        getdatamodel user = new getdatamodel(name, sbalance, mpayment, apr, category, paydate, remdate);


        mDatabase.child("Users data").child(userId).child(userchildid).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(addnew_deptdata.this, "data insert successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void displayAlertbox() {

        AlertDialog.Builder dialogalesrt = new AlertDialog.Builder(this);
        dialogalesrt.setTitle("Please Click The Check Box");
        dialogalesrt.setNegativeButton("ok", null);
        dialogalesrt.show();

    }
}