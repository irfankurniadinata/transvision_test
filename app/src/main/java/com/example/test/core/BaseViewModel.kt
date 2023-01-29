package com.example.test.core

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    private fun unsubscribe(){
        disposables.dispose()
    }

    fun Disposable.toDisposables() {
        disposables.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        unsubscribe()
    }
}