package com.example.bhawook54545434.noteit.DB;

import android.content.Context;

import com.example.bhawook54545434.noteit.dao.NotesDao;
import com.example.bhawook54545434.noteit.model.Notes;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class, "Notes_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
