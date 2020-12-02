package com.example.room_rxjava_livedata.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_rxjava_livedata.R;
import com.example.room_rxjava_livedata.models.notes;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.AdapterNoteHolder> {

    private List<notes> notesList = new ArrayList<>();

    @NonNull
    @Override
    public AdapterNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterNoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNoteHolder holder, int position) {
            holder.idnum.setText(Integer.toString(notesList.get(position).getUserid()));
            holder.idtext.setText(notesList.get(position).getNote_txt());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setList(List<notes> notesList) {
        this.notesList = notesList;
    }

    public class AdapterNoteHolder extends RecyclerView.ViewHolder {

        private MaterialTextView idnum,idtext;
        public AdapterNoteHolder(View itemView) {
            super(itemView);
            idnum = itemView.findViewById(R.id.c1);
            idtext = itemView.findViewById(R.id.c2);
        }
    }
}