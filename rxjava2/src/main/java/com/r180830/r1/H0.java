package com.r180830.r1;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class H0 {
	public static void main(String[] args) {
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				if(!emitter.isDisposed()) {
					emitter.onNext("test");
					emitter.onComplete();
				}
			}
		});
		
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("订阅:" +d);
			}
			@Override
			public void onNext(String t) {
				System.out.println("订阅者得到数据:"+t);
			}
			@Override
			public void onError(Throwable e) {
				
			}
			@Override
			public void onComplete() {
				System.out.println("订阅者完成");
			}
		};
		
		observable.subscribe(observer);
	}
}





