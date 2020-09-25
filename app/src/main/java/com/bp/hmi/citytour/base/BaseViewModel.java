package com.bp.hmi.citytour.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * BaseViewModel.
 */
public abstract class BaseViewModel extends AndroidViewModel implements IBaseViewModel,
        Consumer<Disposable> {
    private CompositeDisposable mCompositeDisposable;

    /**
     * Constructor.
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
        mCompositeDisposable = new CompositeDisposable();
    }

    private void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        // TODO: Base onAny
    }

    @Override
    public void onCreate() {
        // TODO: Base onCreate
    }

    @Override
    public void onDestroy() {
        // TODO: Base onDestroy
    }

    @Override
    public void onStart() {
        // TODO: Base onStart
    }

    @Override
    public void onStop() {
        // TODO: Base onStop
    }

    @Override
    public void onResume() {
        // TODO: Base onResume
    }

    @Override
    public void onPause() {
        // TODO: Base onPause
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void accept(Disposable disposable) throws Exception {
        addSubscribe(disposable);
    }
}
