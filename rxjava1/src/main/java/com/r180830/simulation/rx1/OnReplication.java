package com.r180830.simulation.rx1;

/**
 * 	建立同步关系的时候触发这个方法
 * */
public interface OnReplication<T> {
	public void onRegistry(Replication<T> slaveSQLRunning);
}
