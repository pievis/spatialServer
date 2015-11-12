package it.isac.client.testing;


import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Position;
import it.isac.commons.model.sensors.SensorGPS;
import it.isac.commons.model.sensors.SensorType;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestObjectMapper {

	public static void main(String[] args) {
		SensorGPS gpsMock = new SensorGPS("gpsMock");
		updateValue("sensor1",gpsMock.getValue());
	}

	public static void updateValue(String id, Object value) {
		// Handle update of the domain
		ISensorSnapshot sensorVal = (ISensorSnapshot) value;
		if (sensorVal.getType().contains(SensorType.GPS)) {
			// special case for GPS sensor
			System.out.println("GPS received");
			// let jackson mapper do the conversion
			ObjectMapper mapper = new ObjectMapper();
			Position position = null;
			try {
				position = mapper.readValue(sensorVal.getValue(), Position.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			if (position != null)
				System.out.println("Position not null: "+position.toString());
		}
		// note that sensorId is used in place of workerId
		System.out.println("ID: " + sensorVal.getSensorId() + " Val: "+ sensorVal);
	}
}
