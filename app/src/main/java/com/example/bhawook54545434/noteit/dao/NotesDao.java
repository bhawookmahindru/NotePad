package com.example.bhawook54545434.noteit.dao;

import com.example.bhawook54545434.noteit.model.Notes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_database")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Notes_database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowToHigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_database WHERE id =:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);

}
