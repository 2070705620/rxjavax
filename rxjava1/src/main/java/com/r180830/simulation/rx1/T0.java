package com.r180830.simulation.rx1;

public class T0 {
	public static void main(String[] args) {
		Replicationing replicationing = MysqlServerSQLRunning.create(new OnReplication<String>() {
			@Override
			public void onRegistry(Replication<String> replication) {
				new Thread(()->{
					Debugger.sleep(1000);
					if(!replication.isStop()) {
						replication.onSql("select * from dual");
						replication.onComplete();
					}else {
						System.out.println("已经Stop了");
					}
				}).start();
			}
		}).registry(new MysqlSlaveSQLRunning<String>() {
			@Override
			public void onSql(String t) {
				System.out.println("sql: " + t);
			}
			@Override
			public void onComplete() {
				System.out.println("complete");
			}
			@Override
			public void onError(Throwable throwable) {
				
			}
		});
		replicationing.stop();
	}
}
