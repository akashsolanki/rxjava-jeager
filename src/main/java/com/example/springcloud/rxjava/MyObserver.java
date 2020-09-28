package com.example.springcloud.rxjava;

import rx.Observer;

public class MyObserver {

	Observer<String> myObserver = new Observer<String>() {
		@Override
		public void onNext(String s) {
			System.out.println("MyObserver onNext(): " + s);
		}

		@Override
		public void onCompleted() {
			System.out.println("Observer completed");
		}

		@Override
		public void onError(Throwable e) {
		}
	};
}
