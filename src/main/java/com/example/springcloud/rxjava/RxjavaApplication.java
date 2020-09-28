package com.example.springcloud.rxjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rx.Observable;
import rx.Subscriber;
import rx.internal.util.ActionSubscriber;
import rx.internal.util.ObserverSubscriber;

@SpringBootApplication
public class RxjavaApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RxjavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int i = 0;
		while (i < 100) {
			i++;
			try {
				Observable observable = new MyObservable().observable;

				ObserverSubscriber<Integer> observerSubscriber =
						new ObserverSubscriber(new MyObserver().myObserver);

				Subscriber subscriber = new MySubscriber().subscriber;

				ActionSubscriber actionSubscriber = new ActionSubscriber(
						new MyAction().onNext,
						new MyAction().onError,
						new MyAction().onCompleted);

				observable.subscribe(observerSubscriber);
				observable.subscribe(subscriber);

				Observable.just(1, 2, 3)
						.map(number -> number * number)
						.subscribe(actionSubscriber);

				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
