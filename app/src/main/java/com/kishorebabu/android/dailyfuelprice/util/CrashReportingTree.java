package com.kishorebabu.android.dailyfuelprice.util;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Created by kishore on 03/07/17.
 */

public class CrashReportingTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return;
        }

        if (t == null)
            Crashlytics.log(priority, tag, message);
        else
            Crashlytics.logException(t);

    }
}
