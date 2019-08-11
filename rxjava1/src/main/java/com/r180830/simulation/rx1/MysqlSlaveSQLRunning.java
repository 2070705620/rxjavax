package com.r180830.simulation.rx1;

public interface MysqlSlaveSQLRunning<T> {
	void onSql(T t);
	void onComplete();
	void onError(Throwable throwable);
}
