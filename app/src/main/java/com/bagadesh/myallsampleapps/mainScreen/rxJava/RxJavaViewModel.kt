package com.bagadesh.myallsampleapps.mainScreen.rxJava

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxrelay3.BehaviorRelay
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx3.asFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by bagadesh on 08/02/23.
 */


@HiltViewModel
class RxJavaViewModel @Inject constructor() : ViewModel() {

    private val period = 1L

    private val _observable: BehaviorRelay<Long> = BehaviorRelay.createDefault(0)
    private val observable: Observable<Long> = _observable.distinctUntilChanged().flatMap {
        error("ass")
    }

    private val _observable2: BehaviorRelay<String> = BehaviorRelay.createDefault("")
    private val observable2: Observable<String> = _observable2.distinctUntilChanged()

    private lateinit var flowable: Flowable<String>
    private val compositeDisposable = CompositeDisposable()
    val first = mutableStateOf("")
    val second = mutableStateOf("")
    val third = mutableStateOf("")
    val forth = mutableStateOf("")
    val addOneTimeSubscribe = mutableStateOf("")
    val withLatestFrom = mutableStateOf("")
    val testOutput = mutableStateOf("")
    val filter = mutableStateOf("")
    val doOnNext = mutableStateOf("")
    val defer = mutableStateOf("")
    val deferValue = mutableStateOf("")
    val doOnSubscribe = mutableStateOf("")
    val withLatestFromUsingSubscribeOn = mutableStateOf("")

    //NEXT
    val n1 = mutableStateOf("")
    val n2 = mutableStateOf("")

    init {
        Observable.interval(period, TimeUnit.SECONDS).subscribe {
            _observable.accept(it)
        }.addTo(compositeDisposable)
        Observable.interval(period, TimeUnit.SECONDS).subscribe {
            _observable2.accept("Bagadesh $it")
        }.addTo(compositeDisposable)
    }

    fun addFirst() {
        observable.subscribe {
            first.value = it.toString()
        }.addTo(compositeDisposable)
    }

    fun addSecond() {
        observable.take(1).subscribe {
            second.value = it.toString()
        }.addTo(compositeDisposable)
    }

    fun addThird() {
        third.value = observable.blockingFirst().toString()
    }

    fun addForth() {
        forth.value = observable.blockingLast().toString()
    }

    fun addOneTimeSubscribe() {
        var d = Disposable.disposed()
        d = observable.subscribe {
            addOneTimeSubscribe.value = it.toString()
            d.dispose()
        }
    }

    fun withLatestFrom() {
        observable.withLatestFrom(observable2) { first, second ->
            testOutput.value = "thread:${Thread.currentThread().name}"
            first to second
        }
            .subscribe {
                withLatestFrom.value = "thread:${Thread.currentThread().name}\nresult=$it"
            }.addTo(compositeDisposable)
    }

    fun withLatestFromUsingSubscribeOn() {
        observable
            .observeOn(Schedulers.io())
            .withLatestFrom(observable2) { first, second ->
            testOutput.value = "testOutput: thread:${Thread.currentThread().name}"
            first to second
        }
            .filter {
                filter.value = "filter: thread:${Thread.currentThread().name}"
                true
            }.doOnNext {
                doOnNext.value = "doOnNext: thread:${Thread.currentThread().name}"
            }
            .doOnSubscribe {
                doOnSubscribe.value = "doOnSubscribe: thread:${Thread.currentThread().name}"
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                withLatestFromUsingSubscribeOn.value = "thread:${Thread.currentThread().name}\nresult=$it"
            }.addTo(compositeDisposable)
    }

    fun defer() {
        Observable.defer {
            defer.value = "defer: thread:${Thread.currentThread().name}"
            observable
        }
            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            deferValue.value = "thread:${Thread.currentThread().name}\nresult=$it"
        }.addTo(compositeDisposable)

    }

    fun adNormalSub() {
        observable.subscribe(
            {
                n2.value = it.toString()
            },
            {
                n2.value = it.message.toString()
            }
        )
            .addTo(compositeDisposable)
    }
    fun addFlow1() {
        viewModelScope.launch {
            try {
                observable.asFlow().collectLatest {
                    n1.value = it.toString()
                }
            }catch (exception: Exception) {
                n1.value = exception.message.toString()
            }
        }
    }
}