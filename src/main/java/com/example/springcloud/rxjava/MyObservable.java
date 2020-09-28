package com.example.springcloud.rxjava;

import rx.Observable;

public class MyObservable {
	Observable<String> observable = Observable.unsafeCreate(subscriber -> {
		subscriber.onNext("Hello World");
		subscriber.onCompleted();
	});
}
