package com.example.firebaseapplication;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Notes> list;
    OnNoteClickListener listener;
    FirebaseFirestore db;
    String noteId;

    public interface OnNoteClickListener {
        void onNoteClick(Notes note,String action);
    }
    public NotesAdapter(ArrayList<Notes> list) {
        this.list = list;
    }
    public NotesAdapter(ArrayList<Notes> list, OnNoteClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes note = list.get(position);
        holder.tvDescription.setText(note.getTitle());
        holder.tvTitle.setText(note.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNoteClick(note,"edit");
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNoteClick(note,"delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDescription;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
