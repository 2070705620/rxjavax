package com.r180830.simulation.rx1;

public class MysqlServerSQLRunning<T> {
	private final OnReplication<T> onReplication;
	private MysqlServerSQLRunning(OnReplication<T> onReplication) {
		super();
		this.onReplication = onReplication;
	}
	public static <T> MysqlServerSQLRunning<T> create(OnReplication<T> onReplication) {
		return new MysqlServerSQLRunning<>(onReplication);
	}
	/**
	 *	注册同步
	 * */
	public Replicationing registry(MysqlSlaveSQLRunning<T> slaveSQLRunning) {
		Replication<T> replication = new Replication<T>() {
			@Override
			public void onComplete() {
				slaveSQLRunning.onComplete();
			}
			@Override
			public void onError(Throwable throwable) {
				slaveSQLRunning.onError(throwable);
			}
			@Override
			public void onSql(T t) {
				slaveSQLRunning.onSql(t);
			}
		};
		onReplication.onRegistry(replication);
		return replication;
	}
	
}
