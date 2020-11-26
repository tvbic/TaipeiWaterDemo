package com.example.unitech.bmicalculator;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class SingleLiveEvent<T> extends MutableLiveData<T>
{
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer)
    {
        super.observe(owner, t -> {
            if (mPending.compareAndSet(true, false))
            {
                observer.onChanged(t);
            }
        });

    }

    @MainThread
    public void setValue(@Nullable T t)
    {
        mPending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call()
    {
        setValue(null);
    }
}
