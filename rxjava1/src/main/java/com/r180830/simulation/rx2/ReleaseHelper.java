package com.r180830.simulation.rx2;

import java.util.concurrent.atomic.AtomicReference;

public enum ReleaseHelper implements Releasable{
	RELEASED;
	
	public static boolean isReleased(Releasable releasable) {
		return releasable == RELEASED;
	}
	public static boolean release(AtomicReference<Releasable> atomicReference) {
		Releasable current = atomicReference.get();
		if(current != RELEASED) {
			current = atomicReference.getAndSet(RELEASED);
			if(current != RELEASED) {
				if(current != null) {
					current.release();
				}
				return true;
			}
			return false;
		}else {
			return false;
		}
	}
	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRelease() {
		// TODO Auto-generated method stub
		return false;
	}
}
