package com.example.bhawook54545434.noteit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.bhawook54545434.noteit.ViewModel.NotesViewsModels;
import com.example.bhawook54545434.noteit.databinding.ActivityInsertNotesBinding;
import com.example.bhawook54545434.noteit.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class InsertNotes extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes, priority = "1";
    NotesViewsModels notesViewsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewsModels = ViewModelProviders.of(this).get(NotesViewsModels.class);


        binding.greenOval.setOnClickListener(v -> {
            binding.greenOval.setImageResource(R.drawable.ic_done);
            binding.yellowOval.setImageResource(0);
            binding.redOval.setImageResource(0);
            priority = "1";
        });

        binding.yellowOval.setOnClickListener(v -> {
            binding.greenOval.setImageResource(0);
            binding.yellowOval.setImageResource(R.drawable.ic_done);
            binding.redOval.setImageResource(0);
            priority = "2";
        });

        binding.redOval.setOnClickListener(v -> {
            binding.greenOval.setImageResource(0);
            binding.yellowOval.setImageResource(0);
            binding.redOval.setImageResource(R.drawable.ic_done);
            priority = "3";
        });


        binding.doneNotesBtn.setOnClickListener(v -> {

            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();

            if (title.equals("") || subtitle.equals("") || notes.equals("")) {
                Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_SHORT).show();
            } else
                createNotes(title, subtitle, notes);

        });

    }

    private void createNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;

        notesViewsModels.insertNote(notes1);

        Toast.makeText(this, "Created ✌", Toast.LENGTH_SHORT).show();
        finish();
    }
}