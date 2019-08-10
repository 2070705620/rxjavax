package com.r180830.r2;

import java.util.stream.IntStream;

import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;

public class Helloworld4 {
	public static void main(String[] args) {
		//创建被观察者
	    Observable mObservable = Observable.create(new Observable.OnSubscribe<String>() {
	        @Override
	        public void call(Subscriber<? super String> subscriber) {
	        	SafeSubscriber<String> safeSubscriber = (SafeSubscriber<String>) subscriber;
	        	Debugger.set(safeSubscriber.getActual().hashCode(), safeSubscriber);
	        	
	        	/*SafeSubscriber<String> safeSubscriber = (SafeSubscriber<String>) subscriber;
	        	System.out.println("0-subscriber: " + safeSubscriber.getActual());*/
//	            subscriber.onNext("hellowrod!");
//	            subscriber.onCompleted();
	        }
	    });

	    IntStream.range(0, 1000).forEach(i->{
	    	//创建观察者
		    Subscriber<String> subscriber = new Subscriber<String>() {
		        @Override
		        public void onCompleted() {
		            System.out.println("onCompleted");
		        }
		        @Override
		        public void onError(Throwable e) {
		            System.out.println("onError");
		        }
		        @Override
		        public void onNext(String s) {
		            System.out.println("onNext: "+ this.hashCode() +" - " + s);
		        }
		    };
		    System.out.println(subscriber.hashCode());
		    mObservable.subscribe(subscriber);
	    });
	    new Thread(()->{
	    	System.out.println();
	    }).start();
	    
	}
}
