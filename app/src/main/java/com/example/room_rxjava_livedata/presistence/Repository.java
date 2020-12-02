package com.example.room_rxjava_livedata.presistence;

import android.content.Context;

import com.example.room_rxjava_livedata.models.notes;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class Repository {

    private noteDb note;

    public Repository(Context context){ note = noteDb.getInstance(context); }
    public Completable addnote(notes n){return note.notedao().addnote(n);}
    public Single<List<notes>> getnotes(){return note.notedao().getnotes();}

}
