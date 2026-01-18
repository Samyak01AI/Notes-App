package com.example.firebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DashboardActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView tvName,tvEmail,tvView;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);
        tvView=findViewById(R.id.tvView);
        btnLogout=findViewById(R.id.btnLogout);

            tvEmail.setText("Email: "+firebaseUser.getEmail());
            tvName.setText("Name: "+firebaseUser.getDisplayName());
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                    mAuth.signOut();
                    startActivity(intent);
                    finish();
                }
            });
        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}