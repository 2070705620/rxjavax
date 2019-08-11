package com.r180830.simulation.rx1;

public abstract class Replication<T> implements MysqlSlaveSQLRunning<T>, Replicationing {
	private volatile boolean stop;
	@Override
	public void stop() {
		stop = true;
	}
	@Override
	public boolean isStop() {
		return stop;
	}
}
