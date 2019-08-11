package com.r180830.simulation.rx2;

public interface OnRegistry<T> {
	void onRegistry(Emitter<T> emitter);
}
