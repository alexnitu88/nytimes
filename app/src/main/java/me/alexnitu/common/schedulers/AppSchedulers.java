package me.alexnitu.common.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AppSchedulers implements Schedulers {

    @Override
    public Scheduler io() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
