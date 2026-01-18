package com.example.firebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class NoteOpActivity extends AppCompatActivity {
    FirebaseFirestore db;
    String userId;
    EditText etTitle,etDescription;

    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        etTitle=findViewById(R.id.etTitle);
        etDescription=findViewById(R.id.etDescription);
        btnUpdate=findViewById(R.id.update);
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String noteId = getIntent().getStringExtra("id");
        etTitle.setText(getIntent().getStringExtra("description"));
        etDescription.setText(getIntent().getStringExtra("title"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();

                db.collection("NotesApp").document(userId).collection("Notes").document(noteId)
                        .update("Title", title, "Description", description)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(NoteOpActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("OnFailure ",e.getMessage(),e);
                            }
                        });
                Intent intent = new Intent(NoteOpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}