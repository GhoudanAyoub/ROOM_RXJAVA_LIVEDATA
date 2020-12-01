package com.example.room_rxjava_livedata.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class notes {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userid")
    private  int  userid;
    @ColumnInfo(name = "note_txt")
    private String note_txt;

    @Ignore
    public notes() {
    }

    public notes(int userid, String note_txt) {
        this.userid = userid;
        this.note_txt = note_txt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getNote_txt() {
        return note_txt;
    }

}
