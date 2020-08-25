package com.bp.hmi.citytour.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.bp.hmi.citytour.base.BaseViewModel;
import com.bp.hmi.citytour.ui.repository.HomeRepository;

public class HomeViewModel extends BaseViewModel<HomeRepository> {
    /**
     * Constructor.
     *
     * @param application
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void createRepository() {

    }
}
