package it.isac.client.impl.device;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.Unit;

public class LatLonExecContext extends ExecutionContext<LatLonPosition> {
	
	public Unit unitType;
	
	@Override
	public double distanceTo(String target) {
		NodeState nbr = Domain.getIstance().getNbr(target);
		LatLonPosition myPos = getDevicePosition();
		IPosition nbrPos = nbr.getPosition(); 
		if(nbrPos.getPositionType() == PositionType.LATLON)
			return LatLonPosition.distance(myPos, (LatLonPosition) nbrPos, unitType);
		return Double.NaN;
	}

	@Override
	public LatLonPosition getDevicePosition() {
		Domain dInstance = Domain.getIstance();
		if(dInstance.getPosition().getPositionType() == PositionType.LATLON)
			return (LatLonPosition) dInstance.getPosition();
		return null;
	}
}
