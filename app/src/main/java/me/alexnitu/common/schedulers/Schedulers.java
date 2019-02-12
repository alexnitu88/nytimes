package me.alexnitu.common.schedulers;

import io.reactivex.Scheduler;

public interface Schedulers {

    Scheduler io();

    Scheduler mainThread();
}
