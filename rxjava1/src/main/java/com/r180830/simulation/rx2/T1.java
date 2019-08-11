package com.r180830.simulation.rx2;

public class T1 {
	public static void main(String[] args) {
		ServerIORunning.create(new OnRegistry<String>() {
			@Override
			public void onRegistry(Emitter<String> emitter) {
				if(!emitter.isRelease()) {
					emitter.onSql("select 1");
					emitter.onComplete();
				}
			}
		}).registry(new SlaveIORunning<String>() {
			@Override
			public void onSql(String t) {
				System.out.println("客户端sql: " + t);
			}
			@Override
			public void onRegistry(Releasable releasable) {
				System.out.println(releasable);
			}
			@Override
			public void onError(Throwable throwable) {
			}
			@Override
			public void onComplete() {
				System.out.println("Complete");
			}
		});
	}
}
