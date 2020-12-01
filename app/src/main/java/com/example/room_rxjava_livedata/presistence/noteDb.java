package com.example.room_rxjava_livedata.presistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room_rxjava_livedata.models.notes;

@Database(entities = {notes.class},version = 1)
abstract public class noteDb extends RoomDatabase {
    private static noteDb Instance;
    public abstract notedao notedao();

    public static synchronized noteDb getInstance(Context context){
        if (Instance ==null){
            Instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    noteDb.class,"notedb")
                    .build();
        }
        return Instance;
    }
}
