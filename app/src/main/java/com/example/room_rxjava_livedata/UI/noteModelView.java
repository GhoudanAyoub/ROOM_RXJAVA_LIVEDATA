package com.example.room_rxjava_livedata.UI;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.room_rxjava_livedata.models.notes;
import com.example.room_rxjava_livedata.presistence.noteDb;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class noteModelView  extends ViewModel {

    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<notes>> singleMutableLiveData = new MutableLiveData<List<notes>>();

    // private noteDb noteDb = noteDb.getInstance();
    public void addnote(Context c,notes n){
        (noteDb.getInstance(c))
                .notedao().addnote(n)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {compositeDisposable.add(d); }

                    @Override
                    public void onComplete() { }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }

    public void ShowAllData(Context c){
        (noteDb.getInstance(c))
        .notedao().getnotes()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<notes>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull List<notes> notes) {
                        singleMutableLiveData.setValue(notes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
}
