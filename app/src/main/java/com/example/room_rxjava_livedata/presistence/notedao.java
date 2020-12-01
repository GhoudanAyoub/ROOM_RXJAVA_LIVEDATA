package com.example.room_rxjava_livedata.presistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.room_rxjava_livedata.models.notes;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface notedao {

    @Insert
    Completable addnote(notes notes);

    @Query("Select * from notes")
    Single<List<notes>> getnotes();

}
