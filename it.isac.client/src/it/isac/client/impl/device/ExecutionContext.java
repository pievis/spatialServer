package it.isac.client.impl.device;

import it.isac.commons.interfaces.IPosition;

public abstract class ExecutionContext<Distance> {
	public abstract Distance distanceTo(Long target);
	public abstract IPosition getDevicePosition();
}
