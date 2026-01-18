package com.example.firebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;


public class InsertNotes extends AppCompatActivity {

    FirebaseFirestore db;
    DatabaseReference reference;
    FloatingActionButton save;
    EditText etTitle,etDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note_data);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        etTitle=findViewById(R.id.title);
        etDiscription=findViewById(R.id.description);
        save=findViewById(R.id.next);
        db = FirebaseFirestore.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("Notes");
        String key = reference.push().getKey();
        Notes notes = new Notes();
        reference.child(key).setValue(notes);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=etTitle.getText().toString();
                String description=etDiscription.getText().toString();
                if (!title.isEmpty() && !description.isEmpty()) {
                    HashMap<String, Object> note = new HashMap<>();
                    note.put("Title", title);
                    note.put("Description", description);

                    db.collection("NotesApp").document(userId).collection("Notes")
                            .add(note)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(InsertNotes.this, "Note Added", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(InsertNotes.this,MainActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("OnFailure ",e.getMessage(),e);
                                }
                            });
                }
            }
        });

    }
}