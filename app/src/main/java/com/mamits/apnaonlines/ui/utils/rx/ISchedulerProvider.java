package com.mamits.apnaonlines.ui.utils.rx;

import io.reactivex.Scheduler;

public interface ISchedulerProvider {
    Scheduler io();

    Scheduler ui();

}
