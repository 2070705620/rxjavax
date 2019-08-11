package com.r180830.simulation.rx2;

public interface SlaveIORunning<T> {
	void onRegistry(Releasable releasable);
	void onSql(T t);
	void onComplete();
	void onError(Throwable throwable);
}
