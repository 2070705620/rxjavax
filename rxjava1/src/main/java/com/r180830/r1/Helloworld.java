package com.r180830.r1;

import java.util.stream.IntStream;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class Helloworld {
	public static void main(String[] args) {
		Observable<String> observable = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				System.out.println(t);
				Debugger.sleep(1000);
			}
		});
		IntStream.range(0, 10).forEach(i->{
			new Thread(()->{
				Observer<String> observer = new Observer<String>() {
					@Override
					public void onNext(String t) {
						System.out.println("NEXT" + t);
					}
					@Override
					public void onError(Throwable e) {
						System.out.println("ERROR");
					}
					@Override
					public void onCompleted() {
						System.out.println("COMPLETE");
					}
				};
				observable.subscribe(observer);
			}) .start();
		});
		System.out.println("好像是单线程");
		Debugger.set(1, observable);
		Debugger.sleep(10000000);
		
		
		
	}
}
