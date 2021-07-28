package com.example.bhawook54545434.noteit.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bhawook54545434.noteit.MainActivity;
import com.example.bhawook54545434.noteit.R;
import com.example.bhawook54545434.noteit.UpdateNotes;
import com.example.bhawook54545434.noteit.model.Notes;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitems;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesitems = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filterName) {
        this.notes = filterName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes note = notes.get(position);

        if (note.notesPriority.equals("1")) {
            holder.priority.setBackgroundResource(R.drawable.green);
        } else if (note.notesPriority.equals("2")) {
            holder.priority.setBackgroundResource(R.drawable.yellow);
        } else if (note.notesPriority.equals("3")) {
            holder.priority.setBackgroundResource(R.drawable.red);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNotes.class);

            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notesTitle);
            intent.putExtra("subtitle", note.notesSubtitle);
            intent.putExtra("notes", note.notes);
            intent.putExtra("priority", note.notesPriority);

            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, date;
        View priority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notes_title);
            subtitle = itemView.findViewById(R.id.notes_subtitle);
            date = itemView.findViewById(R.id.notes_date);
            priority = itemView.findViewById(R.id.notes_priority);

        }
    }

}
