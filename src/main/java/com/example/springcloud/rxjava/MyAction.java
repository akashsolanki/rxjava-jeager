package com.example.springcloud.rxjava;

import rx.functions.Action0;
import rx.functions.Action1;

public class MyAction {
	Action1<Integer> onNext = integer -> System.out.println(integer);
	Action0 onCompleted = () -> System.out.println("Action completed");
	Action1<Integer> onError = integer -> System.out.println(integer);
}
