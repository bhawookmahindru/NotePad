package com.example.bhawook54545434.noteit.ViewModel;

import android.app.Application;

import com.example.bhawook54545434.noteit.model.Notes;
import com.example.bhawook54545434.noteit.repository.NotesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewsModels extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowtohigh;
    public LiveData<List<Notes>> hightolow;

    public NotesViewsModels(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;

    }

    public void insertNote(Notes notes) {
        repository.insertNotes(notes);
    }

    public void deleteNote(int id) {
        repository.deleteNotes(id);
    }

    public void updateNote(Notes notes) {
        repository.updateNotes(notes);
    }

}
