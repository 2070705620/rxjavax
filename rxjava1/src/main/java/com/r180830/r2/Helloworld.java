package com.r180830.r2;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

public class Helloworld {
	public static void main(String[] args) {
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onNext(String t) {
				System.err.println("收到消息:" + t);
			}
			@Override
			public void onError(Throwable e) {
			}
			@Override
			public void onCompleted() {
				System.out.println("complete");
			}
		};
		Observable<String> observable = Observable.unsafeCreate(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				Debugger.set(1,  t);
				SafeSubscriber<String> safeSubscriber = (SafeSubscriber)t;
				System.out.println("Subscriber:"+t);
			}
		});
		observable.subscribe(observer);
		System.out.println("observer:" + observer);
	}
}
