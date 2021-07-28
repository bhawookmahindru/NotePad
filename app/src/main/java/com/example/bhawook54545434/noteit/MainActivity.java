package com.example.bhawook54545434.noteit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bhawook54545434.noteit.Adapter.NotesAdapter;
import com.example.bhawook54545434.noteit.ViewModel.NotesViewsModels;
import com.example.bhawook54545434.noteit.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    NotesViewsModels notesViewsModels;
    RecyclerView recyclerView;
    NotesAdapter adapter;
    TextView nofilter, htol, ltoh;
    List<Notes> filterNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.new_notes_btn);
        recyclerView = findViewById(R.id.notes_recyclesview);

        nofilter = findViewById(R.id.no_filter);
        htol = findViewById(R.id.h_to_l);
        ltoh = findViewById(R.id.l_to_h);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            htol.setBackgroundResource(R.drawable.filter_shape);
            ltoh.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        htol.setOnClickListener(v -> {
            loadData(1);
            htol.setBackgroundResource(R.drawable.filter_selected_shape);
            ltoh.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });

        ltoh.setOnClickListener(v -> {
            loadData(2);
            htol.setBackgroundResource(R.drawable.filter_shape);
            ltoh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });


        notesViewsModels = ViewModelProviders.of(this).get(NotesViewsModels.class);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InsertNotes.class);
            startActivity(intent);
        });

        notesViewsModels.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNotesList = notes;
            }
        });
    }

    private void loadData(int i) {

        if (i == 0) {
            notesViewsModels.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesList = notes;
                }
            });
        } else if (i == 1) {
            notesViewsModels.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesList = notes;
                }
            });
        } else if (i == 2) {
            notesViewsModels.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesList = notes;
                }
            });
        }

    }

    public void setAdapter(List<Notes> notes) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> filterNames = new ArrayList<>();

        for (Notes notes : this.filterNotesList) {
            if (notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)) {
                filterNames.add(notes);
            }
        }
        this.adapter.searchNotes(filterNames);

    }
}

