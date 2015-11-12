package it.isac.client.testing;

import org.codehaus.jackson.map.ObjectMapper;

import it.isac.client.impl.device.Domain;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;
import it.isac.commons.model.sensors.SensorGPS;
import it.isac.commons.model.sensors.SensorType;

public class TestObjectMapper {

	public static void main(String[] args) {
		SensorGPS gpsMock = new SensorGPS("gpsMock");
		updateValue("sensor1",gpsMock.getValue());
	}

	public static void updateValue(String id, Object value) {
		// Handle update of the domain
		ISensorSnapshot sensorVal = (ISensorSnapshot) value;
		if (sensorVal.getType().contains(SensorType.GPS)) {
			System.out.println("GPS received");
			// special case for GPS sensor
			String posType = sensorVal.getType().replace(SensorType.GPS, "");
			System.out.println("Pos type: "+posType);
			// let jackson mapper do the conversion
			ObjectMapper mapper = new ObjectMapper();
			IPosition position = null;
			System.out.println("GPS VALUE: "+sensorVal.getValue());
			try {
				switch (posType) {
				case PositionType.LATLON:
					position = mapper.readValue(sensorVal.getValue(), LatLonPosition.class);
					break;
				case PositionType.XY:
					System.out.println("XY POS: "+sensorVal.getValue());
					position = mapper.readValue(sensorVal.getValue(), XYPosition.class);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
			if (position != null)
				Domain.getIstance().setPosition(position);
		}
		// note that sensorId is used in place of workerId
		System.out.println("ID: " + sensorVal.getSensorId() + " Val: "+ sensorVal);
	}
}
