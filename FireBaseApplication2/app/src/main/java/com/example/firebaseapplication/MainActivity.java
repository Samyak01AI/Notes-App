package com.example.firebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnNoteClickListener{

    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    ArrayList<Notes> noteList;
    FirebaseFirestore db;
    FloatingActionButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        next=findViewById(R.id.next);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        noteList = new ArrayList<>();
        notesAdapter=new NotesAdapter(noteList, new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Notes note,String action) {
                if (action.equals("edit")){
                    Intent intent = new Intent(MainActivity.this, NoteOpActivity.class);
                    intent.putExtra("id", note.getId());
                    intent.putExtra("title", note.getTitle());
                    intent.putExtra("description", note.getDescription());
                    startActivity(intent);
                }
                else if (action.equals("delete")){
                    db.collection("NotesApp").document(userId).collection("Notes").document(note.getId()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
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
        recyclerView.setAdapter(notesAdapter);
        loadNotes(userId);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNotes.class));
            }
        });
    }
    private void loadNotes(String userId) {
        db.collection("NotesApp").document(userId).collection("Notes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    noteList.clear();

                    int size = queryDocumentSnapshots.size();
                    for (int i = 0; i < size; i++) {
                        DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(i);
                        Notes note = new Notes(doc.getId(), doc.getString("Title"), doc.getString("Description"));
                        noteList.add(note);

                    }
                    notesAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Log.d("OnFailure", e.getMessage(), e);
                });
    }

    @Override
    public void onNoteClick(Notes note, String action) {

    }
}