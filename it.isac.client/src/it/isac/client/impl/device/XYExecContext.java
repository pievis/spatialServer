package it.isac.client.impl.device;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;

public class XYExecContext extends ExecutionContext<XYPosition> {

	@Override
	public double distanceTo(String target) {
		NodeState nbr = Domain.getIstance().getNbr(target);
		XYPosition myPos = getDevicePosition();
		IPosition nbrPos = nbr.getPosition(); 
		if(nbrPos.getPositionType() == PositionType.XY)
			return XYPosition.distance(myPos, (XYPosition) nbrPos);
		return Double.NaN;
	}

	@Override
	public XYPosition getDevicePosition() {
		Domain dInstance = Domain.getIstance();
		if(dInstance.getPosition().getPositionType() == PositionType.XY)
			return (XYPosition) dInstance.getPosition();
		return null;
	}

}
