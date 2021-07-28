package com.example.bhawook54545434.noteit.repository;

import android.app.Application;

import com.example.bhawook54545434.noteit.DB.NotesDatabase;
import com.example.bhawook54545434.noteit.dao.NotesDao;
import com.example.bhawook54545434.noteit.model.Notes;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        hightolow = notesDao.highToLow();
        lowtohigh = notesDao.lowToHigh();
    }

    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }

}
