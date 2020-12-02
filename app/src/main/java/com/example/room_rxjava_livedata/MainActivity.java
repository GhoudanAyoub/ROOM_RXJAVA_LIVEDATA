package com.example.room_rxjava_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_rxjava_livedata.adapters.AdapterNote;
import com.example.room_rxjava_livedata.models.notes;
import com.example.room_rxjava_livedata.presistence.Repository;
import com.example.room_rxjava_livedata.presistence.noteDb;

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
    private Repository repository;
    private RecyclerView recyclerView;
    private AdapterNote adapterNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view();
        repository = new Repository(this);
        adapterNote = new AdapterNote();
        recyclerView.setAdapter(adapterNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
        repository.addnote(notes)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onComplete() { }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }

    private void showall(){
        repository.getnotes()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<notes>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull List<notes> notes) {
                        adapterNote.setList(notes);
                        adapterNote.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
}