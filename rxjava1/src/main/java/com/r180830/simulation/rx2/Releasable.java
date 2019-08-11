package com.r180830.simulation.rx2;

/**
 * 	是否还在同步，取消同步
 * */
public interface Releasable {
	void release();
	boolean isRelease();
}
