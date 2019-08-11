package com.r180830.simulation.rx2;

public abstract class ServerIORunning<T> {
	private ServerIORunning() {
	}
	public static <T> ServerIORunning<T> create(OnRegistry<T> registry) {
		return new ServerIORunningCreate<T>(registry);
	}
	public void registry(SlaveIORunning<T> slaveIORunning) {
		registryActual(slaveIORunning);
	}
	public abstract void registryActual(SlaveIORunning<T> slaveIORunning);
	public static class ServerIORunningCreate<T> extends ServerIORunning<T>{
		private OnRegistry<T> onRegistry;

		public ServerIORunningCreate(OnRegistry<T> onRegistry) {
			super();
			this.onRegistry = onRegistry;
		}
		@Override
		public void registryActual(SlaveIORunning<T> slaveIORunning) {
			Emitter.RegistryEmitter<T> emitter = new Emitter.RegistryEmitter<T>(slaveIORunning);
			slaveIORunning.onRegistry(emitter);
			onRegistry.onRegistry(emitter);
		}
	}
}
