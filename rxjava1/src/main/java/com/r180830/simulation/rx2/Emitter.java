package com.r180830.simulation.rx2;

import java.util.concurrent.atomic.AtomicReference;

public interface Emitter<T> extends Releasable {
	void onSql(T t);
	void onComplete();
	void onError(Throwable throwable);
	public static class RegistryEmitter<T> extends AtomicReference<Releasable> implements SlaveIORunning<T>, Emitter<T>{
		private static final long serialVersionUID = -5480442492263475755L;
		private boolean release;
		private SlaveIORunning<T> slaveIORunning;
		public RegistryEmitter(SlaveIORunning<T> slaveIORunning) {
			super(); //value默认null
			this.slaveIORunning = slaveIORunning;
		}
		@Override
		public boolean isRelease() {
			return release;
		}
		@Override
		public void release() {
			release = true;
		}
		@Override
		public void onRegistry(Releasable releasable) {
			slaveIORunning.onRegistry(releasable);
		}
		@Override
		public void onSql(T t) {
			if(!isRelease()) {
				slaveIORunning.onSql(t);
			}
		}
		@Override
		public void onComplete() {
			if(!isRelease()) {
				slaveIORunning.onComplete();
				release();
			}
			
		}
		@Override
		public void onError(Throwable throwable) {
			if(!isRelease()) {
				slaveIORunning.onError(throwable);
				release();
			}
		}
	}
}
