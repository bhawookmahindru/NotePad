package com.example.bhawook54545434.noteit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhawook54545434.noteit.ViewModel.NotesViewsModels;
import com.example.bhawook54545434.noteit.databinding.ActivityUpdateNotesBinding;
import com.example.bhawook54545434.noteit.model.Notes;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotes extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String sTitle, sSubtitle, sNotes, sPriority;
    NotesViewsModels notesViewsModels;
    int iID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iID = getIntent().getIntExtra("id", 0);
        sTitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subtitle");
        sNotes = getIntent().getStringExtra("notes");
        sPriority = getIntent().getStringExtra("priority");

        binding.updateTitle.setText(sTitle);
        binding.updateNotes.setText(sNotes);
        binding.updateSubtitle.setText(sSubtitle);

        if (sPriority.equals("1")) {
            binding.greenOval.setImageResource(R.drawable.ic_done);
        } else if (sPriority.equals("2")) {
            binding.yellowOval.setImageResource(R.drawable.ic_done);
        } else if (sPriority.equals("3")) {
            binding.redOval.setImageResource(R.drawable.ic_done);
        }

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

        binding.updateNotesBtn.setOnClickListener(v -> {

            String title = binding.updateTitle.getText().toString();
            String subtitle = binding.updateSubtitle.getText().toString();
            String notes = binding.updateNotes.getText().toString();

            if (title.equals("") || subtitle.equals("") || notes.equals("")) {
                Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_SHORT).show();
            } else
                updateNotes(title, subtitle, notes);

        });

    }

    private void updateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNotes = new Notes();

        updateNotes.id = iID;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();

        notesViewsModels.updateNote(updateNotes);

        Toast.makeText(this, "Updated ✌", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotes.this);
            View view = LayoutInflater.from(UpdateNotes.this).inflate(R.layout.delete_bottomsheet,
                    (LinearLayout) findViewById(R.id.bottom_sheet_linear_layout));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_btn);
            no = view.findViewById(R.id.stay_btn);

            yes.setOnClickListener(v -> {
                notesViewsModels.deleteNote(iID);
                finish();
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }
}
