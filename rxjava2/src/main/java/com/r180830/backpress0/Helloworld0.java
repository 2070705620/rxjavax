package com.r180830.backpress0;

import java.lang.reflect.Field;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.operators.flowable.FlowableCreate;
import io.reactivex.internal.subscribers.StrictSubscriber;

public class Helloworld0 {
	public static void main(String[] args) {
		Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
			@Override
			public void subscribe(FlowableEmitter<String> emitter) throws Exception {
				if(!emitter.isCancelled()) {
					emitter.onNext("what");
					emitter.onComplete();
				}
			}
		}, BackpressureStrategy.DROP);
		
		FlowableSubscriber<String> flowableSubscriber = new FlowableSubscriber<String>() {
			@Override
			public void onSubscribe(Subscription s) {
				assert s.getClass().getSimpleName().equals("DropAsyncEmitter");
				System.out.println("onSubscribe:"+s);
			}
			@Override
			public void onNext(String t) {
				System.out.println("syn message: " + t);
			}
			@Override
			public void onError(Throwable t) {
			}
			@Override
			public void onComplete() {
			}
		};
		Subscriber<String> subscriber = new Subscriber<String>() {
			@Override
			public void onSubscribe(Subscription s) {
				assert s.getClass() == StrictSubscriber.class;
				//s.request(Long.MAX_VALUE);
				System.out.println("onSubscribe:"+s);
			}
			@Override
			public void onNext(String t) {
				System.out.println("syn message: " + t);
			}
			@Override
			public void onError(Throwable t) {
			}
			@Override
			public void onComplete() {
			}
		};
		
		flowable.subscribe(subscriber);
	}
}
