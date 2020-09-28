package com.example.springcloud.rxjava;

import rx.Subscriber;

public class MySubscriber {
	Subscriber<String> subscriber = new Subscriber<String>() {
		@Override
		public void onNext(String s) {
			System.out.println("MySubscriber onNext(): " + s);
		}

		@Override
		public void onCompleted() {
			System.out.println("Subscriber completed");
		}

		@Override
		public void onError(Throwable e) {
			System.out.println("OnError");
		}
	};
}
