package me.alexnitu.common.schedulers;

import io.reactivex.Scheduler;

public class TestSchedulers implements Schedulers{

    @Override
    public Scheduler io() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }
}
