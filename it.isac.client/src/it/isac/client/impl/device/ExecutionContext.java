package it.isac.client.impl.device;

public abstract class ExecutionContext<PosType> {
	public abstract double distanceTo(String target);
	public abstract PosType getDevicePosition();
	public String getNodeId(){
		return Domain.getIstance().getNodeId();
	}
}
