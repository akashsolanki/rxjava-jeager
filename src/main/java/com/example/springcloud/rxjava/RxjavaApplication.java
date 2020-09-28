package com.example.springcloud.rxjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import io.opentracing.rxjava.TracingObserverSubscriber;
import io.opentracing.rxjava.TracingRxJavaUtils;
import io.opentracing.rxjava.TracingSubscriber;
import io.opentracing.util.GlobalTracer;
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
				
				TracingObserverSubscriber<Integer> tracingObserverSubscriber = new TracingObserverSubscriber(observerSubscriber, 
				        "observer", getTracer());

				Subscriber subscriber = new MySubscriber().subscriber;
				
				Subscriber<Integer> tracingSubscriber = new TracingSubscriber<>(subscriber, "subscriber", getTracer());

				ActionSubscriber actionSubscriber = new ActionSubscriber(
						new MyAction().onNext,
						new MyAction().onError,
						new MyAction().onCompleted);

				observable.subscribe(tracingObserverSubscriber);
				observable.subscribe(tracingSubscriber);

				Observable.just(1, 2, 3)
						.map(number -> number * number)
						.subscribe(actionSubscriber);

				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@Bean
	public Tracer getTracer() {
		Configuration configuration = new Configuration("RXJava-Tracer");
			Tracer tracer = configuration.getTracer();
			TracingRxJavaUtils.enableTracing(tracer);
			return tracer;
	}
}
