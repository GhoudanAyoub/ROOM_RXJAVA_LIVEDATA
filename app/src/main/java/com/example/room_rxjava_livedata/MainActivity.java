package com.example.room_rxjava_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_rxjava_livedata.UI.noteModelView;
import com.example.room_rxjava_livedata.adapters.AdapterNote;
import com.example.room_rxjava_livedata.models.notes;
import com.example.room_rxjava_livedata.presistence.noteDb;

import java.security.Provider;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private EditText ccdf,idtext;
    private Button addb,showb;
    private noteModelView noteModelView;
    private RecyclerView recyclerView;
    private AdapterNote adapterNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view();
        adapterNote = new AdapterNote();
        recyclerView.setAdapter(adapterNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteModelView = ViewModelProviders.of(this).get(noteModelView.class);
        noteModelView.ShowAllData(getApplicationContext());
        addb.setOnClickListener(v -> addnote(new notes(Integer.parseInt(ccdf.getText().toString()),idtext.getText().toString())));
        showb.setOnClickListener(v -> showall());

    }
    private void view(){
        ccdf = findViewById(R.id.idnumc22);
        idtext = findViewById(R.id.idtext);
        addb = findViewById(R.id.addbutton);
        showb = findViewById(R.id.showbutton);
        recyclerView = findViewById(R.id.rc22);
    }

    private void addnote(notes notes){
        noteModelView.addnote(getApplicationContext(),notes);
        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
        ccdf.getText().clear();
        idtext.getText().clear();
    }

    private void showall(){
        noteModelView.singleMutableLiveData
                .observe(this, notes -> {
                    adapterNote.setList(notes);
                    adapterNote.notifyDataSetChanged();
                });
    }
}